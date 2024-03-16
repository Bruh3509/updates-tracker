package edu.java.scrapper.dao.jdbc;

import edu.java.scrapper.dto.jdbc.LinkDto;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Component
@Slf4j
@Transactional
public class JdbcLinkDao implements JdbcDao<LinkDto> {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcLinkDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public void add(@NotNull LinkDto linkDto) {
        String sql = "INSERT INTO link(link_name) values(?)";
        jdbcTemplate.update(sql, linkDto.name());
    }

    @Override
    @Transactional
    public void remove(long id) {
        String sql = "DELETE FROM link WHERE link_id=?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    @Transactional
    public List<LinkDto> findAll() {
        String sql = "SELECT * FROM link";
        return jdbcTemplate.query(
            sql,
            (resultSet, rowNum) ->
                new LinkDto(
                    resultSet.getLong("link_id"),
                    resultSet.getString("link_name")
                )
        );
    }
}
