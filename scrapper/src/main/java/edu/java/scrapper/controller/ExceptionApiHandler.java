package edu.java.scrapper.controller;

import edu.java.scrapper.dto.scrapper.ScrapperErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

@RestControllerAdvice
public class ExceptionApiHandler {
    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ScrapperErrorResponse> badRequestException(BindException exception) {
        // TODO do smth
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // TODO stub for now
    }

    @ExceptionHandler(HttpClientErrorException.NotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ScrapperErrorResponse> chatNotFoundException(
        HttpClientErrorException.NotFound exception
    ) {
        // TODO do smth
        return new ResponseEntity<>(HttpStatus.NOT_FOUND); // TODO stub for now
    }
}
