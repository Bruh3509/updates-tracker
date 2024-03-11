package edu.java.scrapper.controller;

import edu.java.scrapper.dto.scrapper.Link;
import edu.java.scrapper.dto.scrapper.ScrapperDeleteResponse;
import edu.java.scrapper.dto.scrapper.ScrapperGetResponse;
import edu.java.scrapper.dto.scrapper.ScrapperPostRequest;
import edu.java.scrapper.dto.scrapper.ScrapperPostResponse;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ScrapperController {
    @PostMapping(value = "/tg-chat/{id}")
    public ResponseEntity<Void> regChat(@PathVariable(required = true) Integer id) {
        // TODO do smth
        return new ResponseEntity<>(HttpStatus.OK); // TODO stub for now
    }

    @DeleteMapping(value = "/tg-chat/{id}")
    public ResponseEntity<Void> deleteChat(@PathVariable(required = true) Integer id) {
        // TODO do smth
        return new ResponseEntity<>(HttpStatus.OK); // TODO stub for now
    }

    @GetMapping(value = "/links", produces = "application/json")
    public ResponseEntity<ScrapperGetResponse> getLinks(
        @Header(name = "Tg-Chat-Id", required = true) Integer id
    ) { // not sure about header
        // TODO do smth
        return new ResponseEntity<>(
            new ScrapperGetResponse(
                List.of(new Link(id, "stackoverflow")), 1),
            HttpStatus.OK
        ); // TODO stub for now
    }

    @PostMapping(value = "/links", consumes = "application/json", produces = "application/json")
    public ResponseEntity<ScrapperPostResponse> addLink(
        @Header(name = "Tg-Chat-Id", required = true) Integer id,
        @RequestBody ScrapperPostRequest request
    ) {
        // TODO do smth
        return new ResponseEntity<>(
            new ScrapperPostResponse(id, request.link()),
            HttpStatus.OK
        ); // TODO stub for now
    }

    @DeleteMapping(value = "/links", consumes = "application/json", produces = "application/json")
    public ResponseEntity<ScrapperDeleteResponse> deleteLink(
        @Header(name = "Tg-Chat-Id", required = true) Integer id,
        @RequestBody ScrapperPostRequest link
    ) {
        // TODO do smth
        return new ResponseEntity<>(
            new ScrapperDeleteResponse(id, link.link()),
            HttpStatus.OK
        ); // TODO stub for now
    }
}
