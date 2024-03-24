package edu.java.scrapper.database.jdbc;

import edu.java.scrapper.IntegrationTest;
import edu.java.scrapper.service.interfaces.LinkService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JdbcLinkServiceTest extends IntegrationTest {
    @Autowired
    private LinkService linkService;

    @Test
    void test() {
        System.out.println(linkService.hashCode());
    }
}
