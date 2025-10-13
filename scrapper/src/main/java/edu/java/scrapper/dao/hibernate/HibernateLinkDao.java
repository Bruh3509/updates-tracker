package edu.java.scrapper.dao.hibernate;

import edu.java.scrapper.entity.Link;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
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

    public void updateModification(OffsetDateTime lastUpdate, Long id) {
        Session session = sessionFactory.getCurrentSession();
        Link link = session.get(Link.class, id);
        link.setLastUpdate(lastUpdate);
        session.merge(link);
    }

    public void updateCheck(Long time, Long id) {
        Session session = sessionFactory.getCurrentSession();
        Link link = session.get(Link.class, id);
        link.setCurTime(time);
        session.merge(link);
    }

    public Optional<Link> findById(long linkId) {
        Session session = sessionFactory.getCurrentSession();
        return Optional.ofNullable(session.get(Link.class, linkId));
    }

    public List<Link> findAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("FROM Link", Link.class).list();
    }
}
