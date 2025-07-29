package edu.java.scrapper.dao.jdbc;

import edu.java.scrapper.domain.jdbc.Chat;
import java.util.List;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.jdbc.core.JdbcTemplate;

@SuppressWarnings({"MultipleStringLiterals"})
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class JdbcChatDao implements JdbcDao<Chat> {
    JdbcTemplate jdbcTemplate;

    @Override
    public void add(Chat chat) {
        String sql = "INSERT INTO chat(chat_id, user_name) VALUES(?,?) ON CONFLICT DO NOTHING";
        jdbcTemplate.update(sql, chat.id(), chat.name());
    }

    @Override
    public void remove(long id) {
        String sql = "DELETE FROM chat WHERE chat_id=?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public List<Chat> findAll() {
        String sql = "SELECT * FROM chat";
        return jdbcTemplate.query(
            sql,
            (resultSet, rowNum) ->
                new Chat(
                    resultSet.getLong("chat_id"),
                    resultSet.getString("user_name")
                )
        );
    }

    @Override
    public List<Chat> findAll(long id) {
        String sql = "SELECT * FROM chat WHERE chat_id=?";
        return jdbcTemplate.query(
            sql,
            (resultSet, rowNum) ->
                new Chat(
                    resultSet.getLong("chat_id"),
                    resultSet.getString("user_name")
                ),
            id
        );
    }
}
