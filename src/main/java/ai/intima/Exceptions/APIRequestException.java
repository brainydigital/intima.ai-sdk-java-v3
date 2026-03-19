package ai.intima.Exceptions;

import java.util.HashMap;
import java.util.Map;

public class APIRequestException extends Exception {

    private final Integer status;

    public APIRequestException() {
        super();
        this.status = null;
    }

    public APIRequestException(String message) {
        super(message);
        this.status = null;
    }

    public APIRequestException(String message, Integer status) {
        super(message);
        this.status = status;
    }

    public Integer getStatus() {
        return status;
    }

    public Map<String, Object> toJson() {
        Map<String, Object> json = new HashMap<>();
        json.put("code", status);
        String msg = this.getMessage();
        Object errorValue;
        try {
            errorValue = new com.fasterxml.jackson.databind.ObjectMapper().readValue(msg, Object.class);
        } catch (Exception e) {
            errorValue = msg;
        }
        json.put("error", errorValue);
        return json;
    }
}
