package edu.java.scrapper.controller;

import edu.java.scrapper.dto.scrapper.ScrapperDeleteResponse;
import edu.java.scrapper.dto.scrapper.ScrapperGetResponse;
import edu.java.scrapper.dto.scrapper.ScrapperPostRequest;
import edu.java.scrapper.dto.scrapper.ScrapperPostResponse;
import edu.java.scrapper.service.interfaces.ChatService;
import edu.java.scrapper.service.interfaces.LinkService;
import java.net.URI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@Component
@RestController
public class ScrapperController {
    private final ChatService chatService;
    private final LinkService linkService;

    @Autowired
    public ScrapperController(
        ChatService chatService,
        LinkService linkService
    ) {
        this.chatService = chatService;
        this.linkService = linkService;
    }

    @PostMapping(value = "/tg-chat/{id}")
    public ResponseEntity<Void> regChat(@PathVariable Long id) {
        chatService.register(id, "default"); // stub for user name
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/tg-chat/{id}")
    public ResponseEntity<Void> deleteChat(@PathVariable Long id) {
        chatService.unregister(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/links", produces = "application/json")
    public ResponseEntity<ScrapperGetResponse> getAllLinks(
        @RequestHeader(name = "Tg-Chat-Id") Long id
    ) {
        var links = linkService.listAll(id);
        return new ResponseEntity<>(
            new ScrapperGetResponse(links, links.size()),
            HttpStatus.OK
        );
    }

    @PostMapping(value = "/links", consumes = "application/json", produces = "application/json")
    public ResponseEntity<ScrapperPostResponse> addLink(
        @RequestHeader(name = "Tg-Chat-Id") Long id,
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
        @RequestHeader(name = "Tg-Chat-Id") Long id,
        @RequestBody ScrapperPostRequest link
    ) {
        linkService.remove(id, link.link().hashCode());
        return new ResponseEntity<>(
            new ScrapperDeleteResponse(id, link.link()),
            HttpStatus.OK
        );
    }
}
