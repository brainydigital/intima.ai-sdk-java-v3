package ai.intima;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.sun.net.httpserver.HttpServer;

final class ServidorFalso implements AutoCloseable {

    record Resposta(int status, String corpo, Map<String, String> headers) {}

    record Recebida(String metodo, String caminho, Map<String, String> query, Map<String, List<String>> headers, byte[] corpo) {

        String header(String nome) {
            for (Map.Entry<String, List<String>> entrada : headers.entrySet()) {
                if (entrada.getKey().equalsIgnoreCase(nome)) {
                    return entrada.getValue().get(0);
                }
            }
            return null;
        }

        String texto() {
            return new String(corpo, StandardCharsets.UTF_8);
        }

        List<String[]> formulario() {
            List<String[]> pares = new ArrayList<>();
            if (corpo.length == 0) {
                return pares;
            }
            for (String par : texto().split("&")) {
                String[] partes = par.split("=", 2);
                pares.add(new String[] {
                    URLDecoder.decode(partes[0], StandardCharsets.UTF_8),
                    partes.length > 1 ? URLDecoder.decode(partes[1], StandardCharsets.UTF_8) : ""
                });
            }
            return pares;
        }

        Map<String, String> dados() {
            Map<String, String> dados = new LinkedHashMap<>();
            formulario().forEach(par -> dados.put(par[0], par[1]));
            return dados;
        }

        Map<String, String> campos() {
            Map<String, String> campos = new LinkedHashMap<>();
            Matcher m = Pattern.compile("name=\"([^\"]+)\"(?:; filename=\"[^\"]*\")?\r\n(?:Content-[^\r]+\r\n)*\r\n(.*?)\r\n--", Pattern.DOTALL).matcher(texto());
            while (m.find()) {
                campos.put(m.group(1), m.group(2));
            }
            return campos;
        }
    }

    private final HttpServer servidor;
    private final Deque<Resposta> respostas = new ArrayDeque<>();
    final List<Recebida> recebidas = new ArrayList<>();

    ServidorFalso() throws IOException {
        servidor = HttpServer.create(new InetSocketAddress("127.0.0.1", 0), 0);
        servidor.createContext("/", troca -> {
            byte[] corpo = troca.getRequestBody().readAllBytes();
            Map<String, String> query = new LinkedHashMap<>();
            String bruta = troca.getRequestURI().getRawQuery();
            if (bruta != null) {
                for (String par : bruta.split("&")) {
                    String[] partes = par.split("=", 2);
                    query.put(URLDecoder.decode(partes[0], StandardCharsets.UTF_8), partes.length > 1 ? URLDecoder.decode(partes[1], StandardCharsets.UTF_8) : "");
                }
            }
            synchronized (recebidas) {
                recebidas.add(new Recebida(troca.getRequestMethod(), troca.getRequestURI().getPath(), query, Map.copyOf(troca.getRequestHeaders()), corpo));
            }
            Resposta resposta = respostas.isEmpty() ? new Resposta(200, "{\"data\":[]}", Map.of()) : respostas.poll();
            resposta.headers().forEach((chave, valor) -> troca.getResponseHeaders().add(chave, valor));
            troca.getResponseHeaders().add("Content-Type", "application/json");
            byte[] saida = resposta.corpo().getBytes(StandardCharsets.UTF_8);
            troca.sendResponseHeaders(resposta.status(), saida.length == 0 ? -1 : saida.length);
            if (saida.length > 0) {
                troca.getResponseBody().write(saida);
            }
            troca.close();
        });
        servidor.start();
    }

    ServidorFalso responder(int status, String corpo) {
        return responder(status, corpo, Map.of());
    }

    ServidorFalso responder(int status, String corpo, Map<String, String> headers) {
        respostas.add(new Resposta(status, corpo, headers));
        return this;
    }

    Intimai intimai() {
        return intimai(new LinkedHashMap<>());
    }

    Intimai intimai(Map<String, Object> opcoes) {
        Map<String, Object> todas = new LinkedHashMap<>(opcoes);
        todas.put("url_base", "http://127.0.0.1:" + servidor.getAddress().getPort() + "/api/v3");
        return new Intimai("token-teste", todas);
    }

    Recebida ultima() {
        return recebidas.get(recebidas.size() - 1);
    }

    Recebida recebida(int indice) {
        return recebidas.get(indice);
    }

    @Override
    public void close() {
        servidor.stop(0);
    }
}
