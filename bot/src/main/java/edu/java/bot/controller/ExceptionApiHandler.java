package edu.java.bot.controller;

import edu.java.bot.dto.BotPostErrorResponse;
import java.net.BindException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionApiHandler {
    @ExceptionHandler(BindException.class)
    public ResponseEntity<BotPostErrorResponse> badRequestException(BindException exception) {
        // TODO do smth
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // TODO stub for now
    }
}
