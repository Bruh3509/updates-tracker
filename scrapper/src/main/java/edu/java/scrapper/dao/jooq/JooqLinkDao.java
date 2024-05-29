package edu.java.scrapper.dao.jooq;

import edu.java.scrapper.domain.jdbc.LinkDto;
import edu.java.scrapper.domain.jooq.tables.Link;
import jakarta.transaction.Transactional;
import java.time.OffsetDateTime;
import java.util.List;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;

@SuppressWarnings({"MagicNumber"})
public class JooqLinkDao {
    private static final Link LINK = Link.LINK;
    private final DSLContext dslContext;

    @Autowired
    public JooqLinkDao(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    @Transactional
    public void add(LinkDto linkDto) {
        dslContext
            .insertInto(LINK)
            .values(
                linkDto.id(),
                linkDto.name(),
                linkDto.curTime(),
                linkDto.lastUpdate()
            )
            .onConflictDoNothing()
            .execute();
    }

    @Transactional
    public void remove(long id) {
        dslContext
            .deleteFrom(LINK)
            .where(LINK.LINK_ID.eq(id))
            .execute();
    }

    @Transactional
    public List<LinkDto> findAll() {
        return dslContext
            .select()
            .from(LINK)
            .fetchInto(LinkDto.class);
    }

    @Transactional
    public List<LinkDto> findAll(long id) {
        return dslContext
            .select()
            .from(LINK)
            .where(LINK.LINK_ID.eq(id))
            .fetchInto(LinkDto.class);
    }

    @Transactional
    public void updateCheck(LinkDto linkDto) {
        dslContext
            .update(LINK)
            .set(LINK.LAST_CHECK, System.currentTimeMillis())
            .where(LINK.LINK_ID.eq(linkDto.id()))
            .execute();
    }

    @Transactional
    public void updateModification(OffsetDateTime lastUpdate, LinkDto linkDto) {
        dslContext
            .update(LINK)
            .set(LINK.LAST_UPDATE, lastUpdate)
            .where(LINK.LINK_ID.eq(linkDto.id()))
            .execute();
    }
}
