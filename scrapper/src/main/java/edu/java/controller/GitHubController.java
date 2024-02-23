package edu.java.controller;

import edu.java.dto.RepositoryDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Optional;

@RestController
public class GitHubController {
    @GetMapping("/repository")
    public ResponseEntity<RepositoryDto> getRepository() {
        // TODO some logic
        return null;
    }
}
