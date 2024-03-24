package edu.java.scrapper.database.jpa;

import edu.java.scrapper.IntegrationTest;
import edu.java.scrapper.service.interfaces.LinkService;
import edu.java.scrapper.service.jpa.JpaLinkService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JpaLinkTest extends IntegrationTest {
    @Autowired
    private LinkService jpaLinkService;

    @Test
    void test(){
        System.out.println(jpaLinkService.hashCode());
    }
}
