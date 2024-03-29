package edu.java.scrapper.database.jdbc;

import edu.java.scrapper.IntegrationTest;
import edu.java.scrapper.dao.jdbc.JdbcChatToLinkDao;
import edu.java.scrapper.dao.jdbc.JdbcLinkDao;
import edu.java.scrapper.dto.jdbc.LinkDto;
import java.net.URI;
import java.time.OffsetDateTime;
import edu.java.scrapper.service.jdbc.JdbcChatService;
import edu.java.scrapper.service.jdbc.JdbcLinkService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import static org.assertj.core.api.Assertions.assertThat;

@Disabled
@SpringBootTest
class JdbcLinkTest extends IntegrationTest {
    @Autowired
    private JdbcLinkService jdbcLinkService;

    @Autowired
    private JdbcChatService jdbcChatService;

    @Test
    @Transactional
    @Rollback
    void test() {
        jdbcChatService.register(42, "default");
        var link = "http://foreach.com";
        jdbcLinkService.add(42, URI.create(link));
        assertThat(jdbcLinkService.listAll(42).size()).isEqualTo(1);
        assertThat(jdbcLinkService.listAll(42).getFirst().url()).isEqualTo(link);
        jdbcLinkService.remove(42, URI.create(link));
        assertThat(jdbcLinkService.listAll(42)).isEmpty();
    }
}
