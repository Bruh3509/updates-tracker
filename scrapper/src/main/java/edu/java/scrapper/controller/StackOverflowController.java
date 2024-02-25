package edu.java.scrapper.controller;

import edu.java.scrapper.clients.StackOverflowClient;
import edu.java.scrapper.dto.stackoverflow.Item;
import edu.java.scrapper.dto.stackoverflow.Items;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class StackOverflowController {
    private final StackOverflowClient stackOverflowClient;

    @Autowired
    public StackOverflowController(@Qualifier("stackoverflow") StackOverflowClient stackOverflowClient) {
        this.stackOverflowClient = stackOverflowClient;
    }

    @GetMapping("/questions/{id}")
    public ResponseEntity<Item> getQuestions(
        @PathVariable Integer id,
        @RequestParam(name = "site", required = true) String site
    ) {
        ResponseEntity<Items> responseEntity = stackOverflowClient.getQuestionById(id, site);
        var question = responseEntity.getBody().items().getFirst();
        log.info(question.toString());
        return new ResponseEntity<>(question, HttpStatus.OK);
    }
}
