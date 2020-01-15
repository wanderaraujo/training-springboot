package com.araujo.training.error;

public class FieldsErrorValidation {

    private String field;
    private String fieldMessage;

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getFieldMessage() {
        return fieldMessage;
    }

    public void setFieldMessage(String fieldMessage) {
        this.fieldMessage = fieldMessage;
    }

    public FieldsErrorValidation(String field, String fieldMessage) {
        this.field = field;
        this.fieldMessage = fieldMessage;
    }
}
