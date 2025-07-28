package edu.java.scrapper.dao.jooq;

import edu.java.scrapper.domain.jdbc.ChatDto;
import edu.java.scrapper.domain.jooq.tables.Chat;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class JooqChatDao {
    static Chat CHAT = Chat.CHAT;
    DSLContext dslContext;

    public void add(ChatDto chatDto) {
        dslContext
            .insertInto(CHAT)
            .values(chatDto.id(), chatDto.name())
            .execute();
    }

    public void remove(long chatId) {
        dslContext
            .deleteFrom(CHAT)
            .where(CHAT.CHAT_ID.eq(chatId))
            .execute();
    }

    public List<ChatDto> findAll() {
        return dslContext
            .select()
            .from(CHAT)
            .fetchInto(ChatDto.class);
    }
}
