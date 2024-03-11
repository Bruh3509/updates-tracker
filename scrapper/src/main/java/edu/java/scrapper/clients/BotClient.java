package edu.java.scrapper.clients;

import edu.java.scrapper.dto.bot.PostRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.PostExchange;

public interface BotClient {
    // TODO String is just a stub for response
    @PostExchange(value = "/updates")
    ResponseEntity<String> sendUpdates(
        @RequestBody PostRequest request
    );
}
