package ai.intima.Exceptions;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class APIRequestException extends Exception {

    private final Integer status;
    private final Object corpo;
    private final Map<String, List<String>> headers;

    public APIRequestException() {
        this(null, null, null, Map.of());
    }

    public APIRequestException(String message) {
        this(message, null, null, Map.of());
    }

    public APIRequestException(String message, Integer status) {
        this(message, status, null, Map.of());
    }

    public APIRequestException(String message, Integer status, Object corpo, Map<String, List<String>> headers) {
        super(message);
        this.status = status;
        this.corpo = corpo;
        this.headers = headers != null ? headers : Map.of();
    }

    public static APIRequestException daResposta(int status, String texto, Object corpo, Map<String, List<String>> headers) {
        String mensagem = "Erro HTTP " + status + ": " + texto;
        Object mensagemApi = mensagemDoCorpo(corpo, true);

        if (status == 401 && mensagemApi instanceof String && ((String) mensagemApi).contains("2FA")) {
            return new DoisFatoresException(mensagem, status, corpo, headers);
        }

        if (status == 429) {
            return new LimiteDeRequisicoesException(mensagem, status, corpo, headers);
        }

        return new APIRequestException(mensagem, status, corpo, headers);
    }

    private static Object mensagemDoCorpo(Object corpo, boolean apenasMessage) {
        if (!(corpo instanceof Map)) {
            return null;
        }

        Map<?, ?> mapa = (Map<?, ?>) corpo;
        Object dados = mapa.get("data");

        if (dados instanceof Map && ((Map<?, ?>) dados).get("message") != null) {
            return ((Map<?, ?>) dados).get("message");
        }

        if (mapa.get("message") != null) {
            return mapa.get("message");
        }

        return apenasMessage ? null : mapa.get("error");
    }

    public Integer getStatus() {
        return status;
    }

    public Object getCorpo() {
        return corpo;
    }

    public String getMensagem() {
        Object mensagem = mensagemDoCorpo(corpo, false);
        return mensagem instanceof String ? (String) mensagem : getMessage();
    }

    public Map<String, List<?>> getErros() {
        Map<String, List<?>> erros = new LinkedHashMap<>();

        if (status != null && status == 422 && corpo instanceof Map) {
            ((Map<?, ?>) corpo).forEach((chave, valor) -> {
                if (valor instanceof List) {
                    erros.put(String.valueOf(chave), (List<?>) valor);
                }
            });
        }

        return erros;
    }

    public String getHeader(String nome) {
        for (Map.Entry<String, List<String>> entrada : headers.entrySet()) {
            if (entrada.getKey() != null && entrada.getKey().equalsIgnoreCase(nome) && !entrada.getValue().isEmpty()) {
                return entrada.getValue().get(0);
            }
        }

        return null;
    }

    public Map<String, Object> toJson() {
        Map<String, Object> json = new HashMap<>();
        json.put("code", status);
        json.put("error", corpo != null ? corpo : getMessage());
        return json;
    }
}
