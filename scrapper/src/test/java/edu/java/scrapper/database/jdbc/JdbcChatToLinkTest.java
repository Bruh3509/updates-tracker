package edu.java.scrapper.database.jdbc;

import edu.java.scrapper.IntegrationTest;
import edu.java.scrapper.dao.jdbc.JdbcChatToLinkDao;
import edu.java.scrapper.dto.jdbc.ChatToLinkDto;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import java.util.stream.Collectors;

@SpringBootTest
public class JdbcChatToLinkTest extends IntegrationTest {
    @Autowired
    JdbcChatToLinkDao jdbcChatToLinkDao;

    @Test
    @Transactional
    @Rollback
    void testFindAll() {
        System.out.println(jdbcChatToLinkDao.findAll());
    }
}
