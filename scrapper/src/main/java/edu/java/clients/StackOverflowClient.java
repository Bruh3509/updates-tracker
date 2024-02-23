package edu.java.clients;

import edu.java.dto.QuestionDto;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.service.annotation.GetExchange;
import java.util.List;

public interface StackOverflowClient {
    @GetExchange("/questions")
    List<QuestionDto> findAll();

    @GetExchange("/questions/{id}")
    List<QuestionDto> findById(@PathVariable Integer id);
}
