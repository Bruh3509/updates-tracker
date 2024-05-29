package edu.java.scrapper.service.jpa;

import edu.java.scrapper.dao.jpa.ChatRepository;
import edu.java.scrapper.entity.Chat;
import edu.java.scrapper.service.interfaces.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class JpaChatService implements ChatService {
    private final ChatRepository chatRepository;

    @Autowired
    public JpaChatService(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }

    @Override
    @Transactional
    public void register(long chatId, String userName) {
        chatRepository.save(new Chat(chatId, userName));
    }

    @Override
    @Transactional
    public void unregister(long chatId) {
        chatRepository.deleteById(chatId);
    }
}
