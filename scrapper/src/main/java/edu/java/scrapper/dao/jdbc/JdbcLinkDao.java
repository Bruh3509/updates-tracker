package edu.java.scrapper.dao.jdbc;

import edu.java.scrapper.domain.jdbc.LinkDto;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.List;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

@SuppressWarnings({"MultipleStringLiterals"})
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class JdbcLinkDao implements JdbcDao<LinkDto> {
    JdbcTemplate jdbcTemplate;

    @Override
    public void add(@NotNull LinkDto linkDto) {
        String sql =
            "INSERT INTO link(link_id, link_name, last_check, last_update) values(?,?,?,?) ON CONFLICT DO NOTHING";
        jdbcTemplate.update(sql, linkDto.id(), linkDto.name(), linkDto.curTime(), linkDto.lastUpdate());
    }

    @Override
    public void remove(long id) {
        String sql = "DELETE FROM link WHERE link_id=?";
        jdbcTemplate.update(sql, id);
    }

    public void updateModification(OffsetDateTime lastUpdate, LinkDto linkDto) {
        String sql = "UPDATE link SET last_update=? WHERE link_id=?";
        jdbcTemplate.update(sql, lastUpdate, linkDto.id());
    }

    public void updateCheck(LinkDto linkDto) {
        String sql = "UPDATE link SET last_check=? WHERE link_id=?";
        jdbcTemplate.update(sql, System.currentTimeMillis(), linkDto.id());
    }

    @Override
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
