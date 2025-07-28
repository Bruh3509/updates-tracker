package edu.java.scrapper.dao.jooq;

import edu.java.scrapper.domain.jdbc.ChatToLinkDto;
import edu.java.scrapper.domain.jooq.tables.ChatToLink;
import edu.java.scrapper.dto.scrapper.Link;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class JooqChatToLinkDao {
    static ChatToLink CHAT_TO_LINK = ChatToLink.CHAT_TO_LINK;
    static edu.java.scrapper.domain.jooq.tables.Link LINK
        = edu.java.scrapper.domain.jooq.tables.Link.LINK;
    DSLContext dslContext;

    public void add(ChatToLinkDto chatToLinkDto) {
        dslContext
            .insertInto(CHAT_TO_LINK)
            .values(chatToLinkDto.chatId(), chatToLinkDto.linkId())
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

    public List<Link> listAll(long chatId) {
        return dslContext
            .select(LINK.LINK_ID, LINK.LINK_NAME)
            .from(CHAT_TO_LINK.innerJoin(LINK)
                .on(CHAT_TO_LINK.LINK_ID.eq(LINK.LINK_ID)))
            .where(CHAT_TO_LINK.CHAT_ID.eq(chatId))
            .fetchInto(Link.class);
    }

    public List<Long> findByLinkId(long linkId) {
        return dslContext
            .select(CHAT_TO_LINK.CHAT_ID)
            .from(CHAT_TO_LINK)
            .where(LINK.LINK_ID.eq(linkId))
            .fetchInto(Long.class);
    }
}
