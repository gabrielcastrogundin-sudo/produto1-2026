package br.ifmg.produto1_2026.resources.exception;

import java.util.List;

public class ValidationError extends StandartError{

    private List<FieldMessage>  fieldMessages;

    public List<FieldMessage> getFieldMessages() {
        return fieldMessages;
    }

    public void setFieldMessages(List<FieldMessage> fieldMessages) {
        this.fieldMessages = fieldMessages;
    }

    public void addFieldMessage(FieldMessage fieldMessage){
        fieldMessages.add(fieldMessage);
    }

}
