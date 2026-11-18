package ai.intima.Exceptions;

import java.util.List;
import java.util.Map;

public class DoisFatoresException extends APIRequestException {

    public DoisFatoresException(String message, Integer status, Object corpo, Map<String, List<String>> headers) {
        super(message, status, corpo, headers);
    }
}
