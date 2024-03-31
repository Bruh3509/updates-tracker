package edu.java.scrapper.clients;

import edu.java.scrapper.dto.bot.PostRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.service.annotation.PostExchange;

public interface BotClient {
    @PostExchange(value = "/updates")
    @Retryable(maxAttemptsExpression = "${app.retry.max-attempts}",
               retryFor = {HttpServerErrorException.InternalServerError.class},
               backoff = @Backoff(delayExpression = "${app.retry.delay}",
                                  multiplierExpression = "${app.retry.exponential}"))
    ResponseEntity<Void> sendUpdates(
        @RequestBody PostRequest request
    );
}
