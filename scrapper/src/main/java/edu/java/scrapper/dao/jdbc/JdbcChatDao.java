package edu.java.scrapper.dao.jdbc;

import edu.java.scrapper.domain.jdbc.ChatDto;
import java.util.List;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

@SuppressWarnings({"MultipleStringLiterals"})
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class JdbcChatDao implements JdbcDao<ChatDto> {
    JdbcTemplate jdbcTemplate;

    @Override
    public void add(ChatDto chatDto) {
        String sql = "INSERT INTO chat(chat_id, user_name) VALUES(?,?) ON CONFLICT DO NOTHING";
        jdbcTemplate.update(sql, chatDto.id(), chatDto.name());
    }

    @Override
    public void remove(long id) {
        String sql = "DELETE FROM chat WHERE chat_id=?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public List<ChatDto> findAll() {
        String sql = "SELECT * FROM chat";
        return jdbcTemplate.query(
            sql,
            (resultSet, rowNum) ->
                new ChatDto(
                    resultSet.getLong("chat_id"),
                    resultSet.getString("user_name")
                )
        );
    }

    @Override
    public List<ChatDto> findAll(long id) {
        String sql = "SELECT * FROM chat WHERE chat_id=?";
        return jdbcTemplate.query(
            sql,
            (resultSet, rowNum) ->
                new ChatDto(
                    resultSet.getLong("chat_id"),
                    resultSet.getString("user_name")
                ),
            id
        );
    }
}
