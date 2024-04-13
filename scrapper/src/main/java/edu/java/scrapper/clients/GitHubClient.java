package edu.java.scrapper.clients;

import edu.java.scrapper.dto.github.RepositoryDto;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.service.annotation.GetExchange;

public interface GitHubClient {
    @GetExchange("/repos/{owner}/{repo}")
    @Retryable(maxAttemptsExpression = "${app.retry.max-attempts}",
               retryFor = {HttpServerErrorException.InternalServerError.class},
               backoff = @Backoff(delayExpression = "${app.retry.delay}",
                                  multiplierExpression = "${app.retry.exponential}"))
    ResponseEntity<RepositoryDto> getRepository(
        @PathVariable String owner,
        @PathVariable String repo
    );
}
