package edu.java.scrapper.database.jdbc;

import edu.java.scrapper.IntegrationTest;

import java.net.URI;

import edu.java.scrapper.dto.scrapper.Link;
import edu.java.scrapper.service.interfaces.ChatService;
import edu.java.scrapper.service.interfaces.LinkService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@Disabled
@SpringBootTest
class JdbcLinkTest extends IntegrationTest {
    @DynamicPropertySource
    static void setJdbcAccessType(DynamicPropertyRegistry registry) {
        registry.add("app.database-access-type", () -> "jdbc");
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
                URI.create(link)
        ));
        linkService.remove(42, link.hashCode());
        assertThat(linkService.listAll(42)).isEmpty();
    }
}
