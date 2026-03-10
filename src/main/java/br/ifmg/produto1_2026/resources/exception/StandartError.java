package br.ifmg.produto1_2026.resources.exception;

import java.time.Instant;

public class StandartError {
    private Instant timestamp;
    private String message;
    private Integer status;
    private String error;
    private String path;

    public StandartError(Instant timestamp, String message, Integer status, String error, String path) {
        this.timestamp = timestamp;
        this.message = message;
        this.status = status;
        this.error = error;
        this.path = path;
    }

    public StandartError() {}

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
