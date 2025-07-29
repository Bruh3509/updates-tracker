package edu.java.scrapper.dao.jooq;

import edu.java.scrapper.domain.jdbc.ChatToLink;
import edu.java.scrapper.dto.scrapper.LinkDto;
import java.util.List;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jooq.DSLContext;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class JooqChatToLinkDao {
    static edu.java.scrapper.domain.jooq.tables.ChatToLink
        CHAT_TO_LINK = edu.java.scrapper.domain.jooq.tables.ChatToLink.CHAT_TO_LINK;
    static edu.java.scrapper.domain.jooq.tables.Link LINK
        = edu.java.scrapper.domain.jooq.tables.Link.LINK;
    DSLContext dslContext;

    public void add(ChatToLink chatToLink) {
        dslContext
            .insertInto(CHAT_TO_LINK)
            .values(chatToLink.chatId(), chatToLink.linkId())
            .onConflictDoNothing()
            .execute();
    }

    public void remove(long chatId, long linkId) {
        dslContext
            .deleteFrom(CHAT_TO_LINK)
            .where(CHAT_TO_LINK.CHAT_ID.eq(chatId)
                .and(CHAT_TO_LINK.LINK_ID.eq(linkId)))
            .execute();
    }

    public List<LinkDto> listAll(long chatId) {
        return dslContext
            .select(LINK.LINK_ID, LINK.LINK_NAME)
            .from(CHAT_TO_LINK.innerJoin(LINK)
                .on(CHAT_TO_LINK.LINK_ID.eq(LINK.LINK_ID)))
            .where(CHAT_TO_LINK.CHAT_ID.eq(chatId))
            .fetchInto(LinkDto.class);
    }

    public List<Long> findByLinkId(long linkId) {
        return dslContext
            .select(CHAT_TO_LINK.CHAT_ID)
            .from(CHAT_TO_LINK)
            .where(LINK.LINK_ID.eq(linkId))
            .fetchInto(Long.class);
    }
}
