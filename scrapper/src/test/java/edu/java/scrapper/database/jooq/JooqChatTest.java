package edu.java.scrapper.database.jooq;

import edu.java.scrapper.IntegrationTest;
import edu.java.scrapper.dao.jooq.JooqChatDao;
import edu.java.scrapper.service.interfaces.ChatService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import static org.assertj.core.api.Assertions.assertThat;

@Disabled
@SpringBootTest
public class JooqChatTest extends IntegrationTest {
    @DynamicPropertySource
    static void setJooqAccessType(DynamicPropertyRegistry registry) {
        registry.add("app.database-access-type", () -> "jooq");
    }

    @Autowired
    private ChatService chatService;
    @Autowired
    private JooqChatDao chatDao;

    @Test
    @Transactional
    @Rollback
    void testChatRemove() {
        chatService.register(42, "default");
        var allChats = chatDao.findAll();
        assertThat(allChats.size()).isEqualTo(1);
        assertThat(allChats.getFirst().id()).isEqualTo(42);
        assertThat(allChats.getFirst().name()).isEqualTo("default");
    }
}
