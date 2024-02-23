package edu.java.controller;

import edu.java.clients.StackOverflowClient;
import edu.java.dto.QuestionDto;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StackOverflowController {
    private final StackOverflowClient stackOverflowClient;

    @Autowired
    public StackOverflowController(StackOverflowClient stackOverflowClient) {
        this.stackOverflowClient = stackOverflowClient;
    }

    @GetMapping("/questions")
    public ResponseEntity<List<QuestionDto>> getQuestions() {
        return new ResponseEntity<>(stackOverflowClient.findAll(), HttpStatus.OK);
    }
}
