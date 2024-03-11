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
    ResponseEntity<Void> regChat(@PathVariable(required = true) Integer id);

    @DeleteExchange("/tg-chat/{id}")
    ResponseEntity<Void> deleteChat(@PathVariable(required = true) Integer id);

    @GetExchange(value = "/links", accept = "application/json")
    ResponseEntity<GetResponse> getAllLinks(
        @Header(name = "Tg-Chat-Id", required = true) Integer id
    );

    @PostExchange(value = "/links", contentType = "application/json", accept = "application/json")
    ResponseEntity<PostResponse> addLink(
        @Header(name = "Tg-Chat-Id", required = true) Integer id,
        @RequestBody PostRequest link
    );

    @DeleteExchange(value = "/links", contentType = "application/json", accept = "application/json")
    ResponseEntity<DeleteResponse> deleteLink(
        @Header(name = "Tg-Chat-Id", required = true) Integer id,
        @RequestBody PostRequest link
    );
}
