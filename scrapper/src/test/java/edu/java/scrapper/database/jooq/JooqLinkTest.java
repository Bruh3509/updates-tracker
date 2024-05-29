package edu.java.scrapper.database.jooq;

import edu.java.scrapper.IntegrationTest;
import edu.java.scrapper.dao.jooq.JooqChatDao;
import edu.java.scrapper.dto.scrapper.Link;
import edu.java.scrapper.service.interfaces.ChatService;
import edu.java.scrapper.service.interfaces.LinkService;
import jakarta.transaction.Transactional;
import java.net.URI;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class JooqLinkTest extends IntegrationTest {
    @DynamicPropertySource
    static void setJooqAccessType(DynamicPropertyRegistry registry) {
        registry.add("app.database-access-type", () -> "jooq");
    }

    @Autowired
    private LinkService linkService;
    @Autowired
    private ChatService chatService;
    @Autowired
    private JooqChatDao chatDao;

    @Test
    @DirtiesContext
    @Transactional
    @Rollback
    void testLinkAddRemove() {
        var link = "https://foreach.com";
        chatService.register(42, "default");
        linkService.add(42, link.hashCode(), URI.create(link));
        assertThat(linkService.listAll(42))
            .containsExactly(new Link((long) link.hashCode(), URI.create(link)));

        linkService.remove(42, link.hashCode());
        assertThat(linkService.listAll(42)).isEmpty();
    }

    @Test
    @DirtiesContext
    @Transactional
    @Rollback
    void testNoChatLinkRemove() {
        var link = "https://foreach.com";
        chatService.register(41, "default");
        linkService.add(41, link.hashCode(), URI.create(link));
        linkService.remove(42, link.hashCode());
        assertThat(linkService.listAll(41))
            .containsExactly(new Link((long) link.hashCode(), URI.create(link)));
    }

    @Test
    @DirtiesContext
    @Transactional
    @Rollback
    void testNoLinkRemove() {
        var git = "https://github.com";
        var stack = "https://stackoverflow.com";
        chatService.register(42, "default");
        linkService.add(42, git.hashCode(), URI.create(git));
        linkService.remove(42, stack.hashCode());
        assertThat(linkService.listAll(42))
            .containsExactly(new Link((long) git.hashCode(), URI.create(git)));
    }
}
