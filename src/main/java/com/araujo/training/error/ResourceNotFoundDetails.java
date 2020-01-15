package com.araujo.training.error;

import java.util.Date;

public class ResourceNotFoundDetails extends ErrorDetails {

    public static final class Builder {
        private String title;
        private Integer status;
        private String  detail;
        private Date timestamp;
        private String developerMessage;

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

        public ResourceNotFoundDetails build() {
            ResourceNotFoundDetails resourceNotFoundDetails = new ResourceNotFoundDetails();
            resourceNotFoundDetails.setDetail(detail);
            resourceNotFoundDetails.setTimestamp(timestamp);
            resourceNotFoundDetails.setStatus(status);
            resourceNotFoundDetails.setTitle(title);
            resourceNotFoundDetails.setDeveloperMessage(developerMessage);
            return resourceNotFoundDetails;
        }
    }
}
