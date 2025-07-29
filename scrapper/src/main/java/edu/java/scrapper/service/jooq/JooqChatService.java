package edu.java.scrapper.service.jooq;

import edu.java.scrapper.dao.jooq.JooqChatDao;
import edu.java.scrapper.domain.jdbc.Chat;
import edu.java.scrapper.service.interfaces.ChatService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.transaction.annotation.Transactional;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Transactional
public class JooqChatService implements ChatService {
    JooqChatDao jooqChatDao;

    @Override
    public void register(long chatId, String userName) {
        jooqChatDao.add(new Chat(chatId, userName));
    }

    @Override
    public void unregister(long chatId) {
        jooqChatDao.remove(chatId);
    }
}
