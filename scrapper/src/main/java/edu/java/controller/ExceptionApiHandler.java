package edu.java.controller;

import edu.java.dto.scrapper.ScrapperErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

@RestControllerAdvice
public class ExceptionApiHandler {
    @ExceptionHandler(BindException.class)
    public ResponseEntity<ScrapperErrorResponse> badRequestException(BindException exception) {
        // TODO do smth
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // TODO stub for now
    }

    @ExceptionHandler(HttpClientErrorException.NotFound.class)
    public ResponseEntity<ScrapperErrorResponse> chatNotFoundException(
        HttpClientErrorException.NotFound exception
    ) {
        // TODO do smth
        return new ResponseEntity<>(HttpStatus.NOT_FOUND); // TODO stub for now
    }
}
