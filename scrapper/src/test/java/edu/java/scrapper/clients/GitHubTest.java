package edu.java.scrapper.clients;

import edu.java.scrapper.configuration.ClientConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ClientConfig.class)

public class GitHubTest {
    @Autowired
    @Qualifier("github")
    public GitHubClient gitHubClient;


    @Test
    void testGitHubClient() {
        System.out.println(gitHubClient.getRepository("Bruh3509", "tinkoff").getBody());
    }
}
