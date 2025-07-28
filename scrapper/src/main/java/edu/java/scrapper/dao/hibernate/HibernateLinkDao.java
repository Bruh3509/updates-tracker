package edu.java.scrapper.dao.hibernate;

import edu.java.scrapper.entity.Link;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class HibernateLinkDao {
    SessionFactory sessionFactory;

    public void add(Link link) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(link);
    }

    public void remove(long id) {
        Session session = sessionFactory.getCurrentSession();
        session.remove(session.get(Link.class, id));
    }

    public void update(Link link) {
        Session session = sessionFactory.getCurrentSession();
        session.merge(link);
    }
}
