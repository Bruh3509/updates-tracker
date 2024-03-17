package edu.java.bot.controller;

import edu.java.bot.dto.bot.PostRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class BotController {
    @PostMapping(value = "/updates", consumes = "application/json")
    public ResponseEntity<Void> sendUpdate(@RequestBody PostRequest updateRequest) {
        var updates = updateRequest.tgChatIds();
        log.info(updateRequest.id().toString());
        // TODO send to all chats update notify
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
