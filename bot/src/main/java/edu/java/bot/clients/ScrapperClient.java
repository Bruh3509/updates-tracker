package edu.java.bot.clients;

import edu.java.bot.dto.scrapper.DeleteResponse;
import edu.java.bot.dto.scrapper.GetResponse;
import edu.java.bot.dto.scrapper.PostRequest;
import edu.java.bot.dto.scrapper.PostResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.service.annotation.DeleteExchange;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.PostExchange;

@Component
public interface ScrapperClient {
    @PostExchange("/tg-chat/{id}")
    @Retryable(maxAttemptsExpression = "${app.retry.max-attempts}",
               retryFor = {HttpServerErrorException.InternalServerError.class},
               backoff = @Backoff(delayExpression = "${app.retry.delay}",
                                  multiplierExpression = "${app.retry.exponential}"))
    ResponseEntity<Void> regChat(@PathVariable(required = true) Long id);

    @DeleteExchange("/tg-chat/{id}")
    @Retryable(maxAttemptsExpression = "${app.retry.max-attempts}",
               retryFor = {HttpServerErrorException.InternalServerError.class},
               backoff = @Backoff(delayExpression = "${app.retry.delay}",
                                  multiplierExpression = "${app.retry.exponential}"))
    ResponseEntity<Void> deleteChat(@PathVariable(required = true) Long id);

    @GetExchange(value = "/links", accept = "application/json")
    @Retryable(maxAttemptsExpression = "${app.retry.max-attempts}",
               retryFor = {HttpServerErrorException.InternalServerError.class},
               backoff = @Backoff(delayExpression = "${app.retry.delay}",
                                  multiplierExpression = "${app.retry.exponential}"))
    ResponseEntity<GetResponse> getAllLinks(
        @RequestHeader(name = "Tg-Chat-Id", required = true) Long id
    );

    @PostExchange(value = "/links", contentType = "application/json", accept = "application/json")
    @Retryable(maxAttemptsExpression = "${app.retry.max-attempts}",
               retryFor = {HttpServerErrorException.InternalServerError.class},
               backoff = @Backoff(delayExpression = "${app.retry.delay}",
                                  multiplierExpression = "${app.retry.exponential}"))
    ResponseEntity<PostResponse> addLink(
        @RequestHeader(name = "Tg-Chat-Id", required = true) Long id,
        @RequestBody PostRequest link
    );

    @DeleteExchange(value = "/links", contentType = "application/json", accept = "application/json")
    @Retryable(maxAttemptsExpression = "${app.retry.max-attempts}",
               retryFor = {HttpServerErrorException.InternalServerError.class},
               backoff = @Backoff(delayExpression = "${app.retry.delay}",
                                  multiplierExpression = "${app.retry.exponential}"))
    ResponseEntity<DeleteResponse> deleteLink(
        @RequestHeader(name = "Tg-Chat-Id", required = true) Long id,
        @RequestBody PostRequest link
    );
}
