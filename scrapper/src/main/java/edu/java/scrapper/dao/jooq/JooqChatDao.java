package edu.java.scrapper.dao.jooq;

import edu.java.scrapper.domain.jdbc.Chat;
import java.util.List;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jooq.DSLContext;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class JooqChatDao {
    static edu.java.scrapper.domain.jooq.tables.Chat CHAT = edu.java.scrapper.domain.jooq.tables.Chat.CHAT;
    DSLContext dslContext;

    public void add(Chat chat) {
        dslContext
            .insertInto(CHAT)
            .values(chat.id(), chat.name())
            .execute();
    }

    public void remove(long chatId) {
        dslContext
            .deleteFrom(CHAT)
            .where(CHAT.CHAT_ID.eq(chatId))
            .execute();
    }

    public List<Chat> findAll() {
        return dslContext
            .select()
            .from(CHAT)
            .fetchInto(Chat.class);
    }
}
