package edu.java.scrapper.controller;

import edu.java.scrapper.clients.GitHubClient;
import edu.java.scrapper.dto.github.RepositoryDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class GitHubController {
    private final GitHubClient gitHubClient;

    @Autowired
    public GitHubController(@Qualifier("github") GitHubClient gitHubClient) {
        this.gitHubClient = gitHubClient;
    }

    @GetMapping("/repos/{owner}/{repo}")
    public ResponseEntity<RepositoryDto> getRepository(
        @PathVariable String owner,
        @PathVariable String repo
    ) {
        return gitHubClient.getRepository(owner, repo);
    }
}
