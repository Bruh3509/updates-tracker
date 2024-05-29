package edu.java.scrapper.dao.jdbc;

import edu.java.scrapper.domain.jdbc.LinkDto;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SuppressWarnings({"MultipleStringLiterals"})
public class JdbcLinkDao implements JdbcDao<LinkDto> {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcLinkDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public void add(@NotNull LinkDto linkDto) {
        String sql =
            "INSERT INTO link(link_id, link_name, last_check, last_update) values(?,?,?,?) ON CONFLICT DO NOTHING";
        jdbcTemplate.update(sql, linkDto.id(), linkDto.name(), linkDto.curTime(), linkDto.lastUpdate());
    }

    @Override
    @Transactional
    public void remove(long id) {
        String sql = "DELETE FROM link WHERE link_id=?";
        jdbcTemplate.update(sql, id);
    }

    @Transactional
    public void updateModification(OffsetDateTime lastUpdate, LinkDto linkDto) {
        String sql = "UPDATE link SET last_update=? WHERE link_id=?";
        jdbcTemplate.update(sql, lastUpdate, linkDto.id());
    }

    @Transactional
    public void updateCheck(LinkDto linkDto) {
        String sql = "UPDATE link SET last_check=? WHERE link_id=?";
        jdbcTemplate.update(sql, System.currentTimeMillis(), linkDto.id());
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
                    resultSet.getString("link_name"),
                    resultSet.getLong("last_check"),
                    OffsetDateTime.ofInstant(
                        resultSet.getTimestamp("last_update").toInstant(),
                        ZoneId.systemDefault()
                    )
                )
        );
    }

    @Override
    @Transactional
    public List<LinkDto> findAll(long id) {
        String sql = "SELECT * FROM link WHERE link_id=?";
        return jdbcTemplate.query(
            sql,
            (resultSet, rowNum) ->
                new LinkDto(
                    resultSet.getLong("link_id"),
                    resultSet.getString("link_name"),
                    resultSet.getLong("last_check"),
                    OffsetDateTime.ofInstant(
                        resultSet.getTimestamp("last_update").toInstant(),
                        ZoneId.systemDefault()
                    )
                ),
            id
        );
    }
}
