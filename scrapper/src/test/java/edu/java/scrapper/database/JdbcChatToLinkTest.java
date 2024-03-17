package edu.java.scrapper.database;

import edu.java.scrapper.dao.jdbc.JdbcChatToLinkDao;
import edu.java.scrapper.dto.jdbc.ChatToLinkDto;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import java.util.stream.Collectors;

@Disabled
@SpringBootTest
public class JdbcChatToLinkTest {
    @Autowired
    JdbcChatToLinkDao jdbcChatToLinkDao;

    @Test
    @Transactional
    @Rollback
    void testFindAll() {
        String str = "1";
        var a  = str.codePoints().mapToObj(String::valueOf).collect(Collectors.joining());
        System.out.println(jdbcChatToLinkDao.findAll());
    }
}
