package edu.java.scrapper.dao.hibernate;

import edu.java.scrapper.domain.jdbc.ChatDto;
import edu.java.scrapper.entity.Chat;
import java.util.List;
import java.util.Optional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.SessionFactory;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class HibernateChatDao {
    SessionFactory sessionFactory;

    public void add(Chat chat) {
        var session = sessionFactory.getCurrentSession();
        session.persist(chat);
    }

    public void remove(Chat chat) {
        var session = sessionFactory.getCurrentSession();
        session.remove(chat);
    }

    public List<ChatDto> findAll() {
        var session = sessionFactory.getCurrentSession();

        return session.createQuery("FROM Chat", Chat.class)
            .list()
            .stream()
            .map(entity -> new ChatDto(entity.getChatId(), entity.getName()))
            .toList();
    }

    public ChatDto findAll(long id) {
        var session = sessionFactory.getCurrentSession();

        return Optional.of(session.get(Chat.class, id))
            .map(entity -> new ChatDto(entity.getChatId(), entity.getName()))
            .get();
    }
}
