package edu.java.scrapper.database.jpa;

import edu.java.scrapper.IntegrationTest;
import edu.java.scrapper.dto.scrapper.Link;
import edu.java.scrapper.service.interfaces.ChatService;
import edu.java.scrapper.service.interfaces.LinkService;
import edu.java.scrapper.service.jpa.JpaLinkService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;

import java.net.URI;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class JpaLinkTest extends IntegrationTest {
    @DynamicPropertySource
    static void setJpaAccessType(DynamicPropertyRegistry registry) {
        registry.add("app.database-access-type", () -> "jpa");
    }

    @Autowired
    private LinkService linkService;
    @Autowired
    private ChatService chatService;

    @Test
    @Transactional
    @Rollback
    void test() {
        var link = "http://foreach.com";
        chatService.register(42, "default");
        linkService.add(42, link.hashCode(), URI.create(link));
        assertThat(linkService.listAll(42)).containsExactly(new Link(
            (long) link.hashCode(),
            link
        ));
        linkService.remove(42, link.hashCode());
        assertThat(linkService.listAll(42)).isEmpty();
    }
}
