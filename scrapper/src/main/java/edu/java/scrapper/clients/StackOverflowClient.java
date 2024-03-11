package edu.java.scrapper.clients;

import edu.java.scrapper.dto.stackoverflow.Items;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;

public interface StackOverflowClient {
    @GetExchange("/questions/{id}?site={site}")
    ResponseEntity<Items> getQuestionById(
        @PathVariable Integer id,
        @PathVariable String site
    );
}
