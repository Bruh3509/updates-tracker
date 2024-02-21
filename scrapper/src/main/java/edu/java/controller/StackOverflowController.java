package edu.java.controller;

import edu.java.dto.QuestionDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Optional;

@RestController
@RequestMapping("/questions")
public class StackOverflowController {
    @GetMapping("/{id}")
    public Optional<QuestionDto> getQuestionById() {
        // TODO some logic
        return Optional.empty();
    }
}
