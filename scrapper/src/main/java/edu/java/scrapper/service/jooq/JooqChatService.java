package edu.java.scrapper.service.jooq;

import edu.java.scrapper.dao.jooq.JooqChatDao;
import edu.java.scrapper.domain.jdbc.ChatDto;
import edu.java.scrapper.service.interfaces.ChatService;
import org.springframework.beans.factory.annotation.Autowired;

public class JooqChatService implements ChatService {
    private final JooqChatDao jooqChatDao;

    @Autowired
    public JooqChatService(JooqChatDao jooqChatDao) {
        this.jooqChatDao = jooqChatDao;
    }

    @Override
    public void register(long chatId, String userName) {
        jooqChatDao.add(new ChatDto(chatId, userName));
    }

    @Override
    public void unregister(long chatId) {
        jooqChatDao.remove(chatId);
    }
}
