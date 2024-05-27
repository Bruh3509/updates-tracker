package edu.java.scrapper.dao.jooq;

import edu.java.scrapper.domain.jdbc.ChatToLinkDto;
import edu.java.scrapper.domain.jooq.tables.ChatToLink;
import edu.java.scrapper.dto.scrapper.Link;
import jakarta.transaction.Transactional;
import java.net.URI;
import java.util.List;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JooqChatToLinkDao {
    private static final ChatToLink CHAT_TO_LINK = ChatToLink.CHAT_TO_LINK;
    private static final edu.java.scrapper.domain.jooq.tables.Link LINK
        = edu.java.scrapper.domain.jooq.tables.Link.LINK;
    private final DSLContext dslContext;

    @Autowired
    public JooqChatToLinkDao(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    @Transactional
    public void add(ChatToLinkDto chatToLinkDto) {
        dslContext
            .insertInto(CHAT_TO_LINK)
            .values(chatToLinkDto.chatId(), chatToLinkDto.linkId())
            .onConflictDoNothing()
            .execute();
    }

    @Transactional
    public void remove(long chatId, long linkId) {
        dslContext
            .deleteFrom(CHAT_TO_LINK)
            .where(CHAT_TO_LINK.CHAT_ID.eq(chatId)
                .and(CHAT_TO_LINK.LINK_ID.eq(linkId)))
            .execute();
    }

    @Transactional
    public List<Link> listAll(long chatId) {
        return dslContext
            .select(LINK.LINK_ID, LINK.LINK_NAME)
            .from(CHAT_TO_LINK.innerJoin(LINK)
                .on(CHAT_TO_LINK.LINK_ID.eq(LINK.LINK_ID)))
            .where(CHAT_TO_LINK.CHAT_ID.eq(chatId))
            .stream()
            .map(rec -> new Link((Long) rec.get(0), URI.create((String) rec.get(1))))
            .toList();
    }

    @Transactional
    public List<Long> findByLinkId(long linkId) {
        return dslContext
            .select(CHAT_TO_LINK.CHAT_ID)
            .from(CHAT_TO_LINK)
            .where(LINK.LINK_ID.eq(linkId))
            .stream()
            .map(rec -> (Long) rec.get(0))
            .toList();
    }
}
