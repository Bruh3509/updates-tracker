package edu.java.scrapper.dao.jooq;

import edu.java.scrapper.domain.jdbc.ChatDto;
import edu.java.scrapper.domain.jooq.tables.Chat;
import jakarta.transaction.Transactional;
import java.util.List;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;

public class JooqChatDao {
    private static final Chat CHAT = Chat.CHAT;
    private final DSLContext dslContext;

    @Autowired
    public JooqChatDao(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    @Transactional
    public void add(ChatDto chatDto) {
        dslContext
            .insertInto(CHAT)
            .values(chatDto.id(), chatDto.name())
            .execute();
    }

    @Transactional
    public void remove(long chatId) {
        dslContext
            .deleteFrom(CHAT)
            .where(CHAT.CHAT_ID.eq(chatId))
            .execute();
    }

    @Transactional
    public List<ChatDto> findAll() {
        return dslContext
            .select()
            .from(CHAT)
            .fetchInto(ChatDto.class);
    }
}
