package edu.java.scrapper.dao.jooq;

import edu.java.scrapper.domain.jdbc.LinkDto;
import edu.java.scrapper.domain.jooq.tables.Link;
import jakarta.transaction.Transactional;
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
    static Link LINK = Link.LINK;
    DSLContext dslContext;

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

    public void remove(long id) {
        dslContext
            .deleteFrom(LINK)
            .where(LINK.LINK_ID.eq(id))
            .execute();
    }

    public List<LinkDto> findAll() {
        return dslContext
            .select()
            .from(LINK)
            .fetchInto(LinkDto.class);
    }

    public List<LinkDto> findAll(long id) {
        return dslContext
            .select()
            .from(LINK)
            .where(LINK.LINK_ID.eq(id))
            .fetchInto(LinkDto.class);
    }

    public void updateCheck(LinkDto linkDto) {
        dslContext
            .update(LINK)
            .set(LINK.LAST_CHECK, System.currentTimeMillis())
            .where(LINK.LINK_ID.eq(linkDto.id()))
            .execute();
    }

    public void updateModification(OffsetDateTime lastUpdate, LinkDto linkDto) {
        dslContext
            .update(LINK)
            .set(LINK.LAST_UPDATE, lastUpdate)
            .where(LINK.LINK_ID.eq(linkDto.id()))
            .execute();
    }
}
