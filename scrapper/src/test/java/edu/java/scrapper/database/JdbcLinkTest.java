package edu.java.scrapper.database;

import edu.java.scrapper.IntegrationTest;
import edu.java.scrapper.dao.jdbc.JdbcLinkDao;
import edu.java.scrapper.dto.jdbc.LinkDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import java.time.OffsetDateTime;

@SpringBootTest
class JdbcLinkTest extends IntegrationTest {
    @Autowired
    private JdbcLinkDao jdbcLinkDao;

    @Test
    @Transactional
    @Rollback
    void findAllTest() {
        System.out.println(jdbcLinkDao.findAll());
    }

    @Test
    @Transactional
    @Rollback
    void addTest() {
        jdbcLinkDao.add(new LinkDto(42, "http://foreach.com", System.currentTimeMillis(), OffsetDateTime.now()));
        jdbcLinkDao.add(new LinkDto(42, "http://foreach.com", System.currentTimeMillis(), OffsetDateTime.now()));
        System.out.println(jdbcLinkDao.findAll());
    }
}
