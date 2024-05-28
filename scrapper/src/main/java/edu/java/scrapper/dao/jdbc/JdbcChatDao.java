package edu.java.scrapper.dao.jdbc;

import edu.java.scrapper.domain.jdbc.ChatDto;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SuppressWarnings({"MultipleStringLiterals"})
public class JdbcChatDao implements JdbcDao<ChatDto> {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcChatDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public void add(ChatDto chatDto) {
        String sql = "INSERT INTO chat(chat_id, user_name) VALUES(?,?)";
        jdbcTemplate.update(sql, chatDto.id(), chatDto.name());
    }

    @Override
    @Transactional
    public void remove(long id) {
        String sql = "DELETE FROM chat WHERE chat_id=?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    @Transactional
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
    @Transactional
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
