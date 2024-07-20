package edu.java.bot.controller;

import edu.java.bot.bot.BotSendUpdate;
import edu.java.bot.dto.bot.PostRequest;
import edu.java.bot.dto.bot.SendUpdateDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class BotController {
    private final BotSendUpdate sendUpdate;

    @Autowired
    public BotController(BotSendUpdate sendUpdate) {
        this.sendUpdate = sendUpdate;
    }

    @PostMapping(value = "/updates", consumes = "application/json")
    public ResponseEntity<Void> sendUpdate(@RequestBody PostRequest updateRequest) {
        sendUpdate.sendUpdate(new SendUpdateDto(updateRequest.tgChatIds(), updateRequest.url()));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
