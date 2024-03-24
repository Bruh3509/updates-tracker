package edu.java.scrapper.controller;

import edu.java.scrapper.dto.scrapper.ScrapperDeleteResponse;
import edu.java.scrapper.dto.scrapper.ScrapperGetResponse;
import edu.java.scrapper.dto.scrapper.ScrapperPostRequest;
import edu.java.scrapper.dto.scrapper.ScrapperPostResponse;
import edu.java.scrapper.service.interfaces.ChatService;
import edu.java.scrapper.service.interfaces.LinkService;
import edu.java.scrapper.service.interfaces.LinkUpdater;
import java.net.URI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Component
@RestController
public class ScrapperController {
    private final ChatService chatService;
    private final LinkService linkService;
    private final LinkUpdater linkUpdater;

    @Autowired
    public ScrapperController(
        ChatService chatService,
        LinkService linkService,
        LinkUpdater linkUpdater
    ) {
        this.chatService = chatService;
        this.linkService = linkService;
        this.linkUpdater = linkUpdater;
    }

    @PostMapping(value = "/tg-chat/{id}")
    public ResponseEntity<Void> regChat(@PathVariable(required = true) Long id) {
        chatService.register(id, "default"); // TODO stub for user name
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/tg-chat/{id}")
    public ResponseEntity<Void> deleteChat(@PathVariable(required = true) Long id) {
        chatService.unregister(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/links", produces = "application/json")
    public ResponseEntity<ScrapperGetResponse> getAllLinks(
        @Header(name = "Tg-Chat-Id", required = true) Long id
    ) { // not sure about header
        var links = linkService.listAll(id);
        return new ResponseEntity<>(
            new ScrapperGetResponse(links, links.size()),
            HttpStatus.OK
        );
    }

    @PostMapping(value = "/links", consumes = "application/json", produces = "application/json")
    public ResponseEntity<ScrapperPostResponse> addLink(
        @Header(name = "Tg-Chat-Id", required = true) Long id,
        @RequestBody ScrapperPostRequest request
    ) {
        var link = request.link();
        linkService.add(id, link.hashCode(), URI.create(link));
        return new ResponseEntity<>(
            new ScrapperPostResponse(id, request.link()),
            HttpStatus.OK
        );
    }

    @DeleteMapping(value = "/links", consumes = "application/json", produces = "application/json")
    public ResponseEntity<ScrapperDeleteResponse> deleteLink(
        @Header(name = "Tg-Chat-Id", required = true) Long id,
        @RequestBody ScrapperPostRequest link
    ) {
        linkService.remove(id, link.link().hashCode());
        return new ResponseEntity<>(
            new ScrapperDeleteResponse(id, link.link()),
            HttpStatus.OK
        );
    }
}
