package com.araujo.training.error;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class ValidationErrorDetails extends ErrorDetail{

    private List<FieldsErrorValidation> errorsValidation;

    public List<FieldsErrorValidation> getErrorsValidation() {
        return errorsValidation;
    }

    public void setErrorsValidation(List<FieldsErrorValidation> errorsValidation) {
        this.errorsValidation = errorsValidation;
    }

    public static final class Builder {
        private String title;
        private Integer status;
        private String  detail;
        private Date timestamp;
        private String developerMessage;
        private List<FieldsErrorValidation> errorsValidation;

        private Builder() {
        }

        public static Builder newBuilder() {
            return new Builder();
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder status(Integer status) {
            this.status = status;
            return this;
        }

        public Builder detail(String detail) {
            this.detail = detail;
            return this;
        }

        public Builder timestamp(Date timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public Builder developerMessage(String developerMessage) {
            this.developerMessage = developerMessage;
            return this;
        }

        public Builder errorsValidation(List errorsValidation) {
            this.errorsValidation = errorsValidation;
            return this;
        }

        public ValidationErrorDetails build() {
            ValidationErrorDetails validationErrorDetails = new ValidationErrorDetails();
            validationErrorDetails.setTitle(title);
            validationErrorDetails.setStatus(status);
            validationErrorDetails.setDetail(detail);
            validationErrorDetails.setTimestamp(timestamp);
            validationErrorDetails.setDeveloperMessage(developerMessage);
            validationErrorDetails.errorsValidation = errorsValidation;
            return validationErrorDetails;
        }
    }
}
