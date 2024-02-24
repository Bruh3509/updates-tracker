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
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ClientConfig.class)
public class StackOverflowWiremockTest {
    @Autowired
    public StackOverflowClient client;

    private static WireMockServer wireMockServer;

    @BeforeAll
    static void startWireMock() {
        wireMockServer = new WireMockServer(8080);
        wireMockServer.start();
        wireMockServer.stubFor(WireMock.get("/questions/1/test")
            .willReturn(WireMock.aResponse()
                .withStatus(200)
                .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .withBody("""
                    [
                        {
                            "id" : 1,
                            "title" : "test",
                            "body" : "A simple test of the client 1"
                        }
                    ]
                    """)
            ));
    }

    @AfterAll
    static void stopWireMock() {
        wireMockServer.stop();
    }

    @Test
    void testWireMock() {
        System.out.println(wireMockServer.baseUrl());
        System.out.println(client.findById(1, "test"));
        Assertions.assertTrue(wireMockServer.isRunning());
    }
}
