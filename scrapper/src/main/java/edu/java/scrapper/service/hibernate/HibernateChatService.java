package edu.java.scrapper.service.hibernate;

import edu.java.scrapper.dao.hibernate.HibernateChatDao;
import edu.java.scrapper.entity.Chat;
import edu.java.scrapper.service.interfaces.ChatService;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Transactional
public class HibernateChatService implements ChatService {
    HibernateChatDao chatDao;

    @Override
    public void register(long chatId, String userName) {
        chatDao.add(new Chat(chatId, userName));
    }

    @Override
    public void unregister(long chatId) {
        chatDao.remove(chatId);
    }
}
