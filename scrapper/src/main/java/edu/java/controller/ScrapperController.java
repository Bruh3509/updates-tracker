package edu.java.controller;

import edu.java.dto.api.ScrapperDeleteResponse;
import edu.java.dto.api.ScrapperGetResponse;
import edu.java.dto.api.ScrapperPostRequest;
import edu.java.dto.api.ScrapperPostResponse;
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
    @PostMapping("/tg-chat/{id}")
    public ResponseEntity<String> regChat(@PathVariable(required = true) Integer id) {
        // TODO do smth
        return new ResponseEntity<>(HttpStatus.OK); // TODO stub for now
    }

    @DeleteMapping("/tg-chat/{id}")
    public ResponseEntity<String> deleteChat(@PathVariable(required = true) Integer id) {
        // TODO do smth
        return new ResponseEntity<>(HttpStatus.OK); // TODO stub for now
    }

    @GetMapping("/links")
    public ResponseEntity<ScrapperGetResponse> getLinks(
        @Header(name = "Tg-Chat-Id", required = true) Integer id
    ) { // not sure about header
        // TODO do smth
        return new ResponseEntity<>(HttpStatus.OK); // TODO stub for now
    }

    @PostMapping("/links")
    public ResponseEntity<ScrapperPostResponse> addLink(
        @Header(name = "Tg-Chat-Id", required = true) Integer id,
        @RequestBody ScrapperPostRequest request
    ) {
        // TODO do smth
        return new ResponseEntity<>(HttpStatus.OK); // TODO stub for now
    }

    @DeleteMapping("/links")
    public ResponseEntity<ScrapperDeleteResponse> deleteLink(
        @Header(name = "Tg-Chat-Id", required = true) Integer id
    ) {
        // TODO do smth
        return new ResponseEntity<>(HttpStatus.OK); // TODO stub for now
    }
}
