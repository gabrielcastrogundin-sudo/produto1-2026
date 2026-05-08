package br.ifmg.produto1_2026.resources.exception;

public class FieldMessage {

    private String message;
    public String field;

    public FieldMessage(String message, String field) {
        this.message = message;
        this.field = field;
    }

    public FieldMessage() {}

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }
}
