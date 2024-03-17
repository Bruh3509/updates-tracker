package edu.java.scrapper.service.jdbc;

import edu.java.scrapper.dao.jdbc.JdbcChatDao;
import edu.java.scrapper.dto.jdbc.ChatDto;
import edu.java.scrapper.service.interfaces.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JdbcChatService implements ChatService {
    JdbcChatDao jdbcChatDao;

    @Autowired
    public JdbcChatService(JdbcChatDao jdbcChatDao) {
        this.jdbcChatDao = jdbcChatDao;
    }

    @Override
    public void register(long chatId, String userName) {
        jdbcChatDao.add(new ChatDto(chatId, userName));
    }

    @Override
    public void unregister(long chatId) {
        jdbcChatDao.remove(chatId);
    }
}
