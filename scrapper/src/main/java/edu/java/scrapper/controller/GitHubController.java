package edu.java.scrapper.controller;

import edu.java.scrapper.dto.RepositoryDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GitHubController {
    @GetMapping("/repository")
    public ResponseEntity<RepositoryDto> getRepository() {
        // TODO some logic
        return null;
    }
}
