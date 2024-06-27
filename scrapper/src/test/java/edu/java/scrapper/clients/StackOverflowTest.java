package edu.java.scrapper.clients;

import edu.java.scrapper.configuration.ClientConfig;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ClientConfig.class)
//@SpringBootTest
public class StackOverflowTest {
    @Autowired
    @Qualifier("stackoverflow")
    public StackOverflowClient client;

    @Test
    void testClient() {
        assertFalse(client
            .getQuestionById(78039352, "stackoverflow")
            .getBody()
            .items()
            .getFirst()
            .isAnswered());
    }
}
