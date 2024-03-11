package edu.java.bot.controller;

import edu.java.bot.dto.bot.PostErrorResponse;
import java.net.BindException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionApiHandler {
    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<PostErrorResponse> badRequestException(BindException exception) {
        // TODO do smth
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // TODO stub for now
    }
}
