package edu.java.scrapper.dao.jdbc;

import edu.java.scrapper.dto.jdbc.ChatToLinkDto;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
@SuppressWarnings({"MultipleStringLiterals"})
public class JdbcChatToLinkDao implements JdbcDao<ChatToLinkDto> {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcChatToLinkDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public void add(ChatToLinkDto chatToLinkDto) {
        String sql = "INSERT INTO chat_to_link(chat_id, link_id) VALUES(?,?)";
        jdbcTemplate.update(sql, chatToLinkDto.chatId(), chatToLinkDto.linkId());
    }

    @Override
    @Transactional
    public void remove(long id) {
        String sql = "DELETE FROM chat_to_link WHERE chat_id=?";
        jdbcTemplate.update(sql, id);
    }

    @Transactional
    public void remove(long chatId, long linkId) {
        String sql = "DELETE FROM chat_to_link WHERE chat_id=? AND link_id=?";
        jdbcTemplate.update(sql, chatId, linkId);
    }

    @Transactional
    public List<ChatToLinkDto> findByLinkId(long linkId) {
        String sql = "SELECT * FROM chat_to_link WHERE link_id=?";
        return jdbcTemplate.query(
            sql,
            (resultSet, rowNum) ->
                new ChatToLinkDto(
                    resultSet.getLong("chat_id"),
                    resultSet.getLong("link_id")
                ),
            linkId
        );
    }

    @Override
    @Transactional
    public List<ChatToLinkDto> findAll() {
        String sql = "SELECT * FROM chat_to_link";
        return jdbcTemplate.query(
            sql,
            (resultSet, rowNum) ->
                new ChatToLinkDto(
                    resultSet.getLong("chat_id"),
                    resultSet.getLong("link_id")
                )
        );
    }

    @Override
    public List<ChatToLinkDto> findAll(long id) {
        String sql = "SELECT * FROM chat_to_link WHERE chat_id=?";
        return jdbcTemplate.query(
            sql,
            (resultSet, rowNum) ->
                new ChatToLinkDto(
                    resultSet.getLong("chat_id"),
                    resultSet.getLong("link_id")
                ),
            id
        );
    }
}
