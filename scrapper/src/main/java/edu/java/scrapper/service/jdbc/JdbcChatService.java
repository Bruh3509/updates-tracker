package edu.java.scrapper.service.jdbc;

import edu.java.scrapper.dao.jdbc.JdbcChatDao;
import edu.java.scrapper.domain.jdbc.ChatDto;
import edu.java.scrapper.service.interfaces.ChatService;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Transactional
public class JdbcChatService implements ChatService {
    JdbcChatDao jdbcChatDao;

    @Override
    public void register(long chatId, String userName) {
        jdbcChatDao.add(new ChatDto(chatId, userName));
    }

    @Override
    public void unregister(long chatId) {
        jdbcChatDao.remove(chatId);
    }
}
