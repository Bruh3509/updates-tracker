package edu.java.scrapper.controller;

import edu.java.scrapper.clients.StackOverflowClient;
import edu.java.scrapper.dto.QuestionDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class StackOverflowController {
    private final StackOverflowClient stackOverflowClient;

    @Autowired
    public StackOverflowController(StackOverflowClient stackOverflowClient) {
        this.stackOverflowClient = stackOverflowClient;
    }

    @GetMapping("/questions/{id}/{question}")
    public ResponseEntity<QuestionDto> getQuestions(@PathVariable Integer id) {
        QuestionDto question = stackOverflowClient.findById(id).getFirst();
        log.info(question.toString());
        return new ResponseEntity<>(question, HttpStatus.OK);
    }
}
