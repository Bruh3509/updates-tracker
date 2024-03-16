package edu.java.scrapper.database;

import edu.java.scrapper.dao.jdbc.JdbcChatDao;
import edu.java.scrapper.dto.jdbc.ChatDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class JdbcChatTest {
    @Autowired
    private JdbcChatDao jdbcChatDao;

    @Test
    @Transactional
    @Rollback
    void findAllTest() {
        System.out.println(jdbcChatDao.findAll());
    }

    @Test
    @Transactional
    @Rollback
    void addTest() {
        jdbcChatDao.add(new ChatDto(42, "@anastasya"));
        System.out.println(jdbcChatDao.findAll());
    }
}
