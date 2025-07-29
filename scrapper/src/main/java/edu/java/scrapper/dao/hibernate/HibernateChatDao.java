package edu.java.scrapper.dao.hibernate;


import java.util.List;
import java.util.Optional;
import edu.java.scrapper.entity.Chat;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.SessionFactory;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class HibernateChatDao {
    SessionFactory sessionFactory;

    public void add(edu.java.scrapper.entity.Chat chat) {
        var session = sessionFactory.getCurrentSession();
        session.persist(chat);
    }

    public void remove(long  chatId) {
        var session = sessionFactory.getCurrentSession();
        // does not hit the db, returns just reference(proxy)
        var chatRef = session.getReference(edu.java.scrapper.entity.Chat.class, chatId);
        session.remove(chatRef);
    }

    public List<Chat> findAll() {
        var session = sessionFactory.getCurrentSession();

        return session.createQuery("FROM Chat", edu.java.scrapper.entity.Chat.class)
            .list()
            .stream()
            .map(entity -> new Chat(entity.getChatId(), entity.getName()))
            .toList();
    }

    public Optional<Chat> findById(long chatId) {
        var session = sessionFactory.getCurrentSession();

        return Optional.ofNullable(session.get(Chat.class, chatId));
    }
}
