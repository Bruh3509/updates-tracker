package edu.java.scrapper.dao.jdbc;

import edu.java.scrapper.domain.jdbc.Link;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.List;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.springframework.jdbc.core.JdbcTemplate;

@SuppressWarnings({"MultipleStringLiterals"})
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class JdbcLinkDao implements JdbcDao<Link> {
    JdbcTemplate jdbcTemplate;

    @Override
    public void add(@NotNull Link link) {
        String sql =
            "INSERT INTO link(link_id, link_name, last_check, last_update) values(?,?,?,?) ON CONFLICT DO NOTHING";
        jdbcTemplate.update(sql, link.id(), link.name(), link.curTime(), link.lastUpdate());
    }

    @Override
    public void remove(long id) {
        String sql = "DELETE FROM link WHERE link_id=?";
        jdbcTemplate.update(sql, id);
    }

    public void updateModification(OffsetDateTime lastUpdate, Link link) {
        String sql = "UPDATE link SET last_update=? WHERE link_id=?";
        jdbcTemplate.update(sql, lastUpdate, link.id());
    }

    public void updateCheck(Link link) {
        String sql = "UPDATE link SET last_check=? WHERE link_id=?";
        jdbcTemplate.update(sql, System.currentTimeMillis(), link.id());
    }

    @Override
    public List<Link> findAll() {
        String sql = "SELECT * FROM link";
        return jdbcTemplate.query(
            sql,
            (resultSet, rowNum) ->
                new Link(
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
    public List<Link> findAll(long id) {
        String sql = "SELECT * FROM link WHERE link_id=?";
        return jdbcTemplate.query(
            sql,
            (resultSet, rowNum) ->
                new Link(
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
