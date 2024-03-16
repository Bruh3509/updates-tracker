package edu.java.scrapper.dao.jdbc;

import edu.java.scrapper.dto.jdbc.ChatDto;
import edu.java.scrapper.dto.jdbc.LinkDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Component
@Transactional
public class JdbcChatDao implements JdbcDao<ChatDto> {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcChatDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void add(ChatDto chatDto) {
        String sql = "INSERT INTO chat(user_name) VALUES(?)";
        jdbcTemplate.update(sql, chatDto.name());
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
}
