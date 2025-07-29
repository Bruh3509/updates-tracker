package edu.java.scrapper.dao.hibernate;

import edu.java.scrapper.dto.scrapper.LinkDto;
import java.net.URI;
import java.util.Optional;
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

    public void add(edu.java.scrapper.entity.Link link) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(link);
    }

    public void remove(long id) {
        Session session = sessionFactory.getCurrentSession();
        session.remove(session.get(edu.java.scrapper.entity.Link.class, id));
    }

    public void update(edu.java.scrapper.entity.Link link) {
        Session session = sessionFactory.getCurrentSession();
        session.merge(link);
    }

    public Optional<Link> findById(long linkId) {
        Session session = sessionFactory.getCurrentSession();
        return Optional.ofNullable(session.get(Link.class, linkId));
    }
}
