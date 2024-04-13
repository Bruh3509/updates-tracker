package edu.java.scrapper.database.jpa;

import edu.java.scrapper.IntegrationTest;
import edu.java.scrapper.repository.ChatRepository;
import edu.java.scrapper.service.interfaces.ChatService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.transaction.annotation.Transactional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Disabled
@SpringBootTest
public class JpaChatTest extends IntegrationTest {
    @DynamicPropertySource
    static void setJpaAccessType(DynamicPropertyRegistry registry) {
        registry.add("app.database-access-type", () -> "jpa");
    }

    @Autowired
    private ChatService chatService;
    @Autowired
    private ChatRepository chatRepository;

    @Test
    @Transactional
    @Rollback
    void testChatRemove() {
        chatService.register(42, "default");
        var allChats = chatRepository.findAll();
        assertThat(allChats.size()).isEqualTo(1);
        assertThat(allChats.getFirst().getChatId()).isEqualTo(42);
        assertThat(allChats.getFirst().getName()).isEqualTo("default");

        chatService.unregister(42);
        var allChats1 = chatRepository.findAll();
        assertTrue(allChats1.isEmpty());
    }

    @Test
    @Transactional
    @Rollback
    void testNoChatRemove() {
        chatService.register(42, "default");
        var allChats = chatRepository.findAll();
        assertThat(allChats.size()).isEqualTo(1);
        assertThat(allChats.getFirst().getChatId()).isEqualTo(42);
        assertThat(allChats.getFirst().getName()).isEqualTo("default");

        chatService.unregister(41);
        var allChats1 = chatRepository.findAll();
        assertThat(allChats1.getFirst().getChatId()).isEqualTo(42);
        assertThat(allChats1.getFirst().getName()).isEqualTo("default");
    }
}
