package com.araujo.training.handler;

import com.araujo.training.error.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.lang.Nullable;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.util.WebUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException rfnException){
        ResourceNotFoundDetails rfnDetails = ResourceNotFoundDetails.Builder
                .newBuilder()
                .timestamp(new Date())
                .status(HttpStatus.NOT_FOUND.value())
                .title("Resource not found")
                .detail(rfnException.getMessage())
                .developerMessage(rfnException.getClass().getName())
                .build();

        return new ResponseEntity<>(rfnDetails, HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException manvException,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request){

        List<FieldsErrorValidation> listError = new ArrayList<>();
        for(FieldError ee: manvException.getBindingResult().getFieldErrors()){
            listError.add(new FieldsErrorValidation(ee.getField(), ee.getDefaultMessage()));
        }

        ValidationErrorDetails rfnDetails = ValidationErrorDetails.Builder
                .newBuilder()
                .timestamp(new Date())
                .status(HttpStatus.BAD_REQUEST.value())
                .title("Field validation error")
                .detail("Field validation error")
                .developerMessage(manvException.getClass().getName())
                .errorsValidation(listError)
                .build();

        return new ResponseEntity<>(rfnDetails, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {

        ErrorDetails errorDetails = ErrorDetails.Builder
                .newBuilder()
                .timestamp(new Date())
                .status(HttpStatus.BAD_REQUEST.value())
                .title("Resource not found")
                .detail(ex.getMessage())
                .developerMessage(ex.getClass().getName())
                .build();

        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(
            Exception ex,
            Object body,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {

        if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
            request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, WebRequest.SCOPE_REQUEST);
        }

        ErrorDetails errorDetails = ErrorDetails.Builder
                .newBuilder()
                .timestamp(new Date())
                .status(status.value())
                .title("Internal Exception")
                .detail(ex.getMessage())
                .developerMessage(ex.getClass().getName())
                .build();

        return new ResponseEntity<>(errorDetails,headers, status);
    }
}
