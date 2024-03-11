package edu.java.bot.controller;

import edu.java.bot.dto.bot.PostRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BotController {
    // TODO ResponseEntity
    @PostMapping(value = "/updates", consumes = "application/json")
    public ResponseEntity<Void> sendUpdate(@RequestBody PostRequest updateRequest) {
        // TODO do smth
        return new ResponseEntity<>(HttpStatus.OK); // TODO stub for now
    }
}
