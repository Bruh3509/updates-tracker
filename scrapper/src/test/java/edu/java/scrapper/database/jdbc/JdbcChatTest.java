package edu.java.scrapper.database.jdbc;

import edu.java.scrapper.IntegrationTest;
import edu.java.scrapper.dao.jdbc.JdbcChatDao;
import edu.java.scrapper.service.interfaces.ChatService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.transaction.annotation.Transactional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class JdbcChatTest extends IntegrationTest {
    @DynamicPropertySource
    static void setJdbcAccessType(DynamicPropertyRegistry registry) {
        registry.add("app.database-access-type", () -> "jdbc");
    }

    @Autowired
    private ChatService chatService;
    @Autowired
    private JdbcChatDao chatDao;

    @Test
    @DirtiesContext
    @Transactional
    @Rollback
    void testChatRemove() {
        chatService.register(42, "default");
        var allChats = chatDao.findAll();
        assertThat(allChats.size()).isEqualTo(1);
        assertThat(allChats.getFirst().id()).isEqualTo(42);
        assertThat(allChats.getFirst().name()).isEqualTo("default");

        chatService.unregister(42);
        var allChats1 = chatDao.findAll();
        assertTrue(allChats1.isEmpty());
    }

    @Test
    @DirtiesContext
    @Transactional
    @Rollback
    void testNoChatRemove() {
        chatService.register(42, "default");
        var allChats = chatDao.findAll();
        assertThat(allChats.size()).isEqualTo(1);
        assertThat(allChats.getFirst().id()).isEqualTo(42);
        assertThat(allChats.getFirst().name()).isEqualTo("default");

        chatService.unregister(41);
        var allChats1 = chatDao.findAll();
        assertThat(allChats1.getFirst().id()).isEqualTo(42);
        assertThat(allChats1.getFirst().name()).isEqualTo("default");
    }
}
