package ai.intima.API;

import java.io.IOException;
import java.net.http.*;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ai.intima.Exceptions.APIRequestException;

public class API {
    public static final String URL_BASE = "https://app.intima.ai/api/v3";
    private final String apiToken;
    private final HttpClient cliente = HttpClient.newHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public API(String apiToken) {
        this.apiToken = apiToken;
    }

    public API(String apiToken, Object opcoes) {
        this.apiToken = apiToken;
    }

    private String buildQueryString(int pagina, Map<String, String> filtros) {
        Map<String, String> query = new LinkedHashMap<>();
        query.put("api_token", apiToken);
        query.put("page", String.valueOf(pagina));
        if (filtros != null) {
            query.putAll(filtros);
        }
        return query.entrySet().stream()
                .map(e -> URLEncoder.encode(e.getKey(), StandardCharsets.UTF_8) + "=" + URLEncoder.encode(e.getValue(), StandardCharsets.UTF_8))
                .reduce((a, b) -> a + "&" + b)
                .orElse("");
    }

    private HttpRequest.BodyPublisher buildFormBody(Map<String, Object> params) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            if (sb.length() > 0) sb.append("&");
            sb.append(URLEncoder.encode(entry.getKey(), StandardCharsets.UTF_8));
            sb.append("=");
            sb.append(URLEncoder.encode(String.valueOf(entry.getValue()), StandardCharsets.UTF_8));
        }
        return HttpRequest.BodyPublishers.ofString(sb.toString());
    }

    @SuppressWarnings("unchecked")
    private HttpRequest.BodyPublisher buildMultipartBody(List<Map<String, Object>> parts, String boundary) throws IOException {
        List<byte[]> byteArrays = new ArrayList<>();
        byte[] crlf = "\r\n".getBytes(StandardCharsets.UTF_8);

        for (Map<String, Object> part : parts) {
            String name = (String) part.get("name");
            Object contents = part.get("contents");
            String filename = (String) part.getOrDefault("filename", null);

            byteArrays.add(("--" + boundary + "\r\n").getBytes(StandardCharsets.UTF_8));
            if (filename != null && contents instanceof Path) {
                byteArrays.add(("Content-Disposition: form-data; name=\"" + name + "\"; filename=\"" + filename + "\"\r\n").getBytes(StandardCharsets.UTF_8));
                byteArrays.add("Content-Type: application/octet-stream\r\n\r\n".getBytes(StandardCharsets.UTF_8));
                byteArrays.add(Files.readAllBytes((Path) contents));
            } else {
                byteArrays.add(("Content-Disposition: form-data; name=\"" + name + "\"\r\n\r\n").getBytes(StandardCharsets.UTF_8));
                byteArrays.add(String.valueOf(contents).getBytes(StandardCharsets.UTF_8));
            }
            byteArrays.add(crlf);
        }
        byteArrays.add(("--" + boundary + "--").getBytes(StandardCharsets.UTF_8));

        return HttpRequest.BodyPublishers.ofByteArrays(byteArrays);
    }

    @SuppressWarnings("unchecked")
    private Map<String, Object> sendRequest(HttpRequest request) throws APIRequestException {
        try {
            HttpResponse<String> response = cliente.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() >= 400) {
                throw new APIRequestException("Erro HTTP " + response.statusCode() + ": " + response.body(), response.statusCode());
            }
            return objectMapper.readValue(response.body(), new TypeReference<Map<String, Object>>() {});
        } catch (APIRequestException e) {
            throw e;
        } catch (Exception e) {
            throw new APIRequestException(e.getMessage());
        }
    }

    public Map<String, Object> get(String endpoint) throws APIRequestException {
        return get(endpoint, 1, new HashMap<>());
    }

    public Map<String, Object> get(String endpoint, int pagina, Map<String, String> filtros) throws APIRequestException {
        String url = URL_BASE + endpoint + "?" + buildQueryString(pagina, filtros);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Accept", "application/json")
                .header("User-Agent", "Intimai_SDK/3.0.0")
                .GET()
                .build();
        return sendRequest(request);
    }

    @SuppressWarnings("unchecked")
    public Map<String, Object> post(String endpoint, Map<String, Object> requestData) throws APIRequestException {
        try {
            String url = URL_BASE + endpoint + "?" + buildQueryString(1, new HashMap<>());
            HttpRequest.Builder builder = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Accept", "application/json")
                    .header("User-Agent", "Intimai_SDK/3.0.0");

            if (requestData.containsKey("multipart")) {
                String boundary = UUID.randomUUID().toString();
                List<Map<String, Object>> parts = (List<Map<String, Object>>) requestData.get("multipart");
                builder.header("Content-Type", "multipart/form-data; boundary=" + boundary)
                        .POST(buildMultipartBody(parts, boundary));
            } else {
                Map<String, Object> params = requestData.containsKey("form_params")
                        ? (Map<String, Object>) requestData.get("form_params")
                        : requestData;
                builder.header("Content-Type", "application/x-www-form-urlencoded")
                        .POST(buildFormBody(params));
            }
            return sendRequest(builder.build());
        } catch (APIRequestException e) {
            throw e;
        } catch (Exception e) {
            throw new APIRequestException(e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public Map<String, Object> put(String endpoint, Map<String, Object> requestData) throws APIRequestException {
        try {
            String url = URL_BASE + endpoint + "?" + buildQueryString(1, new HashMap<>());
            HttpRequest.Builder builder = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Accept", "application/json")
                    .header("User-Agent", "Intimai_SDK/3.0.0");

            if (requestData.containsKey("multipart")) {
                String boundary = UUID.randomUUID().toString();
                List<Map<String, Object>> parts = (List<Map<String, Object>>) requestData.get("multipart");
                builder.header("Content-Type", "multipart/form-data; boundary=" + boundary)
                        .PUT(buildMultipartBody(parts, boundary));
            } else {
                Map<String, Object> params = requestData.containsKey("form_params")
                        ? (Map<String, Object>) requestData.get("form_params")
                        : requestData;
                builder.header("Content-Type", "application/x-www-form-urlencoded")
                        .PUT(buildFormBody(params));
            }
            return sendRequest(builder.build());
        } catch (APIRequestException e) {
            throw e;
        } catch (Exception e) {
            throw new APIRequestException(e.getMessage());
        }
    }

    public Map<String, Object> delete(String endpoint) throws APIRequestException {
        String url = URL_BASE + endpoint + "?" + buildQueryString(1, new HashMap<>());
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Accept", "application/json")
                .header("User-Agent", "Intimai_SDK/3.0.0")
                .DELETE()
                .build();
        return sendRequest(request);
    }
}
