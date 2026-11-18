package ai.intima.Exceptions;

import java.util.List;
import java.util.Map;

public class LimiteDeRequisicoesException extends APIRequestException {

    public LimiteDeRequisicoesException(String message, Integer status, Object corpo, Map<String, List<String>> headers) {
        super(message, status, corpo, headers);
    }

    public Integer getRetryAfter() {
        String valor = getHeader("Retry-After");
        return valor != null && valor.matches("\\d+") ? Integer.valueOf(valor) : null;
    }
}
