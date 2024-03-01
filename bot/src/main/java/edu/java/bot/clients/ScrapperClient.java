package edu.java.bot.clients;

import edu.java.bot.dto.scrapper.DeleteResponse;
import edu.java.bot.dto.scrapper.GetResponse;
import edu.java.bot.dto.scrapper.PostRequest;
import edu.java.bot.dto.scrapper.PostResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.DeleteExchange;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.PostExchange;

public interface ScrapperClient {
    @PostExchange("/tg-chat/{id}")
    ResponseEntity<String> regChat(@PathVariable(required = true) Integer id);

    @DeleteExchange("/tg-chat/{id}")
    ResponseEntity<String> deleteChat(@PathVariable(required = true) Integer id);

    @GetExchange("/links")
    ResponseEntity<GetResponse> getAllLinks(
        @Header(name = "Tg-Chat-Id", required = true) Integer id
    );

    @PostExchange("/links")
    ResponseEntity<PostResponse> addLink(
        @Header(name = "Tg-Chat-Id", required = true) Integer id,
        @RequestBody PostRequest link
    );

    @DeleteExchange("/links")
    ResponseEntity<DeleteResponse> deleteLink(
        @Header(name = "Tg-Chat-Id", required = true) Integer id,
        @RequestBody PostRequest link
    );
}
