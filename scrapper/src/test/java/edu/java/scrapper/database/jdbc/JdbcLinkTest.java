package edu.java.scrapper.database.jdbc;

import edu.java.scrapper.IntegrationTest;
import java.net.URI;
import edu.java.scrapper.service.interfaces.LinkService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class JdbcLinkTest extends IntegrationTest {
    @Autowired
    private LinkService linkService;

    @Test
    @Transactional
    @Rollback
    void findAllTest() {
        System.out.println(linkService.listAll(1));
    }

    @Test
    @Transactional
    @Rollback
    void addTest() {
        linkService.add(42, "http://foreach.com".hashCode(), URI.create("http://foreach.com"));
        linkService.add(42, "http://foreach.com".hashCode(), URI.create("http://foreach.com"));
        System.out.println(linkService.listAll(42));
    }
}
