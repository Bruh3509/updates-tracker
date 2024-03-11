package edu.java.scrapper.clients;

import edu.java.scrapper.dto.github.RepositoryDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;

public interface GitHubClient {
    @GetExchange("/repos/{owner}/{repo}")
    ResponseEntity<RepositoryDto> getRepository(
        @PathVariable String owner,
        @PathVariable String repo
    );
}
