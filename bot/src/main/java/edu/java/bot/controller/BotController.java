package edu.java.bot.controller;

import edu.java.bot.dto.BotPostRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/updates")
public class BotController {
    @PostMapping
    public ResponseEntity<String> sendUpdate(@RequestBody BotPostRequest updateRequest) {
        // TODO do smth
        return new ResponseEntity<>(HttpStatus.OK); // TODO stub for now
    }
}
