package edu.java.scrapper.clients;

import edu.java.scrapper.dto.QuestionDto;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.GetExchange;
import java.util.List;

public interface StackOverflowClient {
    /*
    @GetExchange("/questions")
    List<QuestionDto> findAll();
     */

    @GetExchange("/questions/{id}")
    List<QuestionDto> findById(@PathVariable Integer id);
}
