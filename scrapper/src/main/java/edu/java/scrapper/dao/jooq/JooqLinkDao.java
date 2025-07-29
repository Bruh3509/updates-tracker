package edu.java.scrapper.dao.jooq;

import edu.java.scrapper.domain.jdbc.Link;
import java.time.OffsetDateTime;
import java.util.List;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jooq.DSLContext;

@SuppressWarnings({"MagicNumber"})
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class JooqLinkDao {
    static edu.java.scrapper.domain.jooq.tables.Link LINK = edu.java.scrapper.domain.jooq.tables.Link.LINK;
    DSLContext dslContext;

    public void add(Link link) {
        dslContext
            .insertInto(LINK)
            .values(
                link.id(),
                link.name(),
                link.curTime(),
                link.lastUpdate()
            )
            .onConflictDoNothing()
            .execute();
    }

    public void remove(long id) {
        dslContext
            .deleteFrom(LINK)
            .where(LINK.LINK_ID.eq(id))
            .execute();
    }

    public List<Link> findAll() {
        return dslContext
            .select()
            .from(LINK)
            .fetchInto(Link.class);
    }

    public List<Link> findAll(long id) {
        return dslContext
            .select()
            .from(LINK)
            .where(LINK.LINK_ID.eq(id))
            .fetchInto(Link.class);
    }

    public void updateCheck(Link link) {
        dslContext
            .update(LINK)
            .set(LINK.LAST_CHECK, System.currentTimeMillis())
            .where(LINK.LINK_ID.eq(link.id()))
            .execute();
    }

    public void updateModification(OffsetDateTime lastUpdate, Link link) {
        dslContext
            .update(LINK)
            .set(LINK.LAST_UPDATE, lastUpdate)
            .where(LINK.LINK_ID.eq(link.id()))
            .execute();
    }
}
