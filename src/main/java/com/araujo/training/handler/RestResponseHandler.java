package com.araujo.training.handler;

import org.apache.commons.io.IOUtils;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;

import java.io.IOException;

public class RestResponseHandler extends DefaultResponseErrorHandler {

    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        System.out.println("Inside hasError");
        return super.hasError(response);
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        System.out.println("Doing something with status " + response.getStatusCode());
        System.out.println("Doing something with body " + IOUtils.toString(response.getBody(), "UTF-8"));
        super.handleError(response);
    }
}
