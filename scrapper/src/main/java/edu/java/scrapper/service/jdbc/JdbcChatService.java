package edu.java.scrapper.service.jdbc;

import edu.java.scrapper.dao.jdbc.JdbcChatDao;
import edu.java.scrapper.dto.jdbc.ChatDto;
import edu.java.scrapper.service.interfaces.ChatService;

public class JdbcChatService implements ChatService {
    private final JdbcChatDao jdbcChatDao;

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
