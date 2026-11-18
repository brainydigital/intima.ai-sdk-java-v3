package ai.intima.API;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Supplier;

import com.fasterxml.jackson.databind.ObjectMapper;
import ai.intima.DadosTipo.Oab;
import ai.intima.Exceptions.APIRequestException;

public class API {
    public static final String URL_BASE = "https://app.intima.ai/api/v3";
    public static final String USER_AGENT = "Intimai_SDK/3.1.0";
    public static final String HEADER_CODIGO_2FA = "X-Codigo-2FA";

    private final String apiToken;
    private final String urlBase;
    private final Map<String, String> headersOpcoes = new LinkedHashMap<>();
    private final Map<String, String> paramsOpcoes = new LinkedHashMap<>();
    private final HttpClient cliente = HttpClient.newHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();
    private Object codigo2fa;

    public API(String apiToken) {
        this(apiToken, null);
    }

    public API(String apiToken, Object opcoes) {
        this.apiToken = apiToken;
        Map<?, ?> mapa = opcoes instanceof Map ? (Map<?, ?>) opcoes : Map.of();
        this.urlBase = mapa.get("url_base") != null ? String.valueOf(mapa.get("url_base")) : URL_BASE;
        this.codigo2fa = mapa.get("codigo_2fa");
        copiar(mapa.get("headers"), headersOpcoes);
        copiar(mapa.get("params"), paramsOpcoes);
    }

    public void definirCodigo2fa(String codigo) {
        this.codigo2fa = codigo;
    }

    public void definirCodigo2fa(Supplier<String> codigo) {
        this.codigo2fa = codigo;
    }

    private static void copiar(Object origem, Map<String, String> destino) {
        if (origem instanceof Map) {
            ((Map<?, ?>) origem).forEach((chave, valor) -> {
                if (valor != null) {
                    destino.put(String.valueOf(chave), String.valueOf(valor));
                }
            });
        }
    }

    private static String codificar(String valor) {
        return URLEncoder.encode(valor, StandardCharsets.UTF_8);
    }

    public static String formulario(Map<String, Object> dados) {
        List<String> pares = new ArrayList<>();
        dados.forEach((chave, valor) -> adicionar(pares, chave, valor));
        return String.join("&", pares);
    }

    private static void adicionar(List<String> pares, String chave, Object valor) {
        if (valor == null) {
            return;
        }

        if (valor instanceof Oab) {
            Oab oab = (Oab) valor;
            Map<String, Object> mapa = new LinkedHashMap<>();
            mapa.put("numero", oab.getNumero());
            mapa.put("letra", oab.getLetra());
            mapa.put("uf", oab.getUf());
            valor = mapa;
        }

        if (valor instanceof Map) {
            ((Map<?, ?>) valor).forEach((subchave, item) -> adicionar(pares, chave + "[" + subchave + "]", item));
        } else if (valor instanceof Iterable) {
            int indice = 0;
            for (Object item : (Iterable<?>) valor) {
                adicionar(pares, chave + "[" + indice++ + "]", item);
            }
        } else if (valor.getClass().isArray()) {
            for (int indice = 0; indice < Array.getLength(valor); indice++) {
                adicionar(pares, chave + "[" + indice + "]", Array.get(valor, indice));
            }
        } else if (valor instanceof Boolean) {
            pares.add(codificar(chave) + "=" + ((Boolean) valor ? "1" : "0"));
        } else {
            pares.add(codificar(chave) + "=" + codificar(String.valueOf(valor)));
        }
    }

    private static byte[] multipart(List<Map<String, Object>> partes, String boundary) throws IOException {
        ByteArrayOutputStream corpo = new ByteArrayOutputStream();

        for (Map<String, Object> parte : partes) {
            Object conteudo = parte.get("contents");

            if (conteudo == null) {
                continue;
            }

            corpo.write(("--" + boundary + "\r\n").getBytes(StandardCharsets.UTF_8));

            if (conteudo instanceof Path) {
                Path caminho = (Path) conteudo;
                Object nome = parte.get("filename") != null ? parte.get("filename") : caminho.getFileName();
                corpo.write(("Content-Disposition: form-data; name=\"" + parte.get("name") + "\"; filename=\"" + nome + "\"\r\n").getBytes(StandardCharsets.UTF_8));
                corpo.write("Content-Type: application/octet-stream\r\n\r\n".getBytes(StandardCharsets.UTF_8));
                corpo.write(Files.readAllBytes(caminho));
            } else {
                corpo.write(("Content-Disposition: form-data; name=\"" + parte.get("name") + "\"\r\n\r\n").getBytes(StandardCharsets.UTF_8));
                corpo.write(String.valueOf(conteudo).getBytes(StandardCharsets.UTF_8));
            }

            corpo.write("\r\n".getBytes(StandardCharsets.UTF_8));
        }

        corpo.write(("--" + boundary + "--\r\n").getBytes(StandardCharsets.UTF_8));

        return corpo.toByteArray();
    }

    private String url(String endpoint, int pagina, Map<String, String> filtros) {
        Map<String, String> query = new LinkedHashMap<>();
        query.put("api_token", apiToken);
        query.put("page", String.valueOf(pagina));
        query.putAll(paramsOpcoes);

        if (filtros != null) {
            filtros.forEach((chave, valor) -> {
                if (valor != null) {
                    query.put(chave, valor);
                }
            });
        }

        List<String> pares = new ArrayList<>();
        query.forEach((chave, valor) -> pares.add(codificar(chave) + "=" + codificar(valor)));

        return urlBase + endpoint + "?" + String.join("&", pares);
    }

    @SuppressWarnings("unchecked")
    private HttpRequest.Builder requisicao(String url) {
        HttpRequest.Builder builder = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Accept", "application/json")
                .header("User-Agent", USER_AGENT);

        Object codigo = codigo2fa instanceof Supplier ? ((Supplier<String>) codigo2fa).get() : codigo2fa;

        if (codigo != null && !String.valueOf(codigo).isEmpty()) {
            builder.header(HEADER_CODIGO_2FA, String.valueOf(codigo));
        }

        headersOpcoes.forEach(builder::setHeader);

        return builder;
    }

    @SuppressWarnings("unchecked")
    private HttpRequest comCorpo(String metodo, String endpoint, Map<String, Object> dados) throws APIRequestException {
        HttpRequest.Builder builder = requisicao(url(endpoint, 1, null));
        Map<String, Object> request = dados != null ? dados : Map.of();

        if (request.containsKey("multipart")) {
            String boundary = UUID.randomUUID().toString();
            byte[] corpo;

            try {
                corpo = multipart((List<Map<String, Object>>) request.get("multipart"), boundary);
            } catch (IOException e) {
                throw new APIRequestException("O caminho do arquivo informado é inválido!");
            }

            return builder.header("Content-Type", "multipart/form-data; boundary=" + boundary)
                    .method(metodo, HttpRequest.BodyPublishers.ofByteArray(corpo))
                    .build();
        }

        Map<String, Object> campos = request.containsKey("form_params")
                ? (Map<String, Object>) request.get("form_params")
                : request;

        return builder.header("Content-Type", "application/x-www-form-urlencoded")
                .method(metodo, HttpRequest.BodyPublishers.ofString(formulario(campos)))
                .build();
    }

    @SuppressWarnings("unchecked")
    private Map<String, Object> enviar(HttpRequest request) throws APIRequestException {
        HttpResponse<String> response;

        try {
            response = cliente.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            throw new APIRequestException(e.getMessage());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new APIRequestException(e.getMessage());
        }

        Object corpo = lerCorpo(response.body());

        if (response.statusCode() >= 400) {
            throw APIRequestException.daResposta(response.statusCode(), response.body(), corpo, response.headers().map());
        }

        if (corpo instanceof Map) {
            return (Map<String, Object>) corpo;
        }

        Map<String, Object> resultado = new LinkedHashMap<>();

        if (corpo != null) {
            resultado.put("data", corpo);
        }

        return resultado;
    }

    private Object lerCorpo(String corpo) {
        if (corpo == null || corpo.isBlank()) {
            return null;
        }

        try {
            return objectMapper.readValue(corpo, Object.class);
        } catch (IOException e) {
            return corpo;
        }
    }

    public Map<String, Object> get(String endpoint) throws APIRequestException {
        return get(endpoint, 1, null);
    }

    public Map<String, Object> get(String endpoint, int pagina, Map<String, String> filtros) throws APIRequestException {
        return enviar(requisicao(url(endpoint, pagina, filtros)).GET().build());
    }

    public Map<String, Object> post(String endpoint, Map<String, Object> requestData) throws APIRequestException {
        return enviar(comCorpo("POST", endpoint, requestData));
    }

    public Map<String, Object> put(String endpoint, Map<String, Object> requestData) throws APIRequestException {
        return enviar(comCorpo("PUT", endpoint, requestData));
    }

    public Map<String, Object> delete(String endpoint) throws APIRequestException {
        return enviar(requisicao(url(endpoint, 1, null)).DELETE().build());
    }
}
