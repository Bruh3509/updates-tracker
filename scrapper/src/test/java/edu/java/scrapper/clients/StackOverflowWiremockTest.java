package edu.java.scrapper.clients;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import edu.java.scrapper.configuration.ClientConfig;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ClientConfig.class)
public class StackOverflowWiremockTest {
    @Autowired
    @Qualifier("stackoverflowLocalhost")
    public StackOverflowClient client;

    private static WireMockServer wireMockServer;

    @BeforeAll
    static void startWireMock() {
        wireMockServer = new WireMockServer(8080);
        wireMockServer.start();
        wireMockServer.stubFor(WireMock.get("/questions/1?site=stackoverflow")
            .willReturn(WireMock.aResponse()
                .withStatus(200)
                .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .withBody("""
                    {
                    	"items": [
                    		{
                    			"tags": [
                    				"python",
                    				"email",
                    				"mime"
                    			],
                    			"owner": {
                    				"account_id": 5431361,
                    				"reputation": 453,
                    				"user_id": 10470463,
                    				"user_type": "registered",
                    				"profile_image": "https://i.stack.imgur.com/qBjZ6.jpg?s=256&g=1",
                    				"display_name": "Pedroski",
                    				"link": "https://stackoverflow.com/users/10470463/pedroski"
                    			},
                    			"post_state": "Published",
                    			"is_answered": true,
                    			"view_count": 43,
                    			"accepted_answer_id": 78052662,
                    			"answer_count": 1,
                    			"score": -2,
                    			"last_activity_date": 1708858682,
                    			"creation_date": 1708781460,
                    			"last_edit_date": 1708858682,
                    			"question_id": 78052611,
                    			"content_license": "CC BY-SA 4.0",
                    			"link": "https://stackoverflow.com/questions/78052611/how-to-get-the-text-of-the-email-body",
                    			"title": "How to get the text of the email body?"
                    		}
                    	],
                    	"has_more": false,
                    	"quota_max": 300,
                    	"quota_remaining": 251
                    }
                     """)
            ));
    }

    @AfterAll
    static void stopWireMock() {
        wireMockServer.stop();
    }

    @Test
    void testWireMock() {
        Assertions.assertTrue(wireMockServer.isRunning());
        var items = client.getQuestionById(1, "stackoverflow").getBody().items();
        Assertions.assertTrue(items.getFirst().isAnswered());
        assertThat(items.getFirst().viewCount()).isEqualTo(43);
    }
}
