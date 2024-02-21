package edu.java.controller;

import edu.java.dto.RepositoryDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Optional;

@RestController
@RequestMapping
public class GitHubController {
    @GetMapping
    public Optional<RepositoryDto> getRepository() {
        // TODO some logic
        return Optional.empty();
    }
}
