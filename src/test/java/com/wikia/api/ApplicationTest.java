package com.wikia.api;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.util.MatcherAssertionErrors.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest({"server.port = 9000"})
public class ApplicationTest {

    RestTemplate restTemplate = new TestRestTemplate();
    static final String baseURL = "http://localhost:9000/";

    @Test
    public void testArticlesResponse() {
        ResponseEntity<String> response = restTemplate.getForEntity(baseURL + "articles/Kermit the Frog", String.class);
        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
    }

    @Test
    public void testArticlesResponseContent() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        ResponseEntity<String> response = restTemplate.getForEntity(baseURL + "articles/Kermit the Frog", String.class);
        JsonNode json = objectMapper.readTree(response.getBody());
        JsonNode content = json.path("content");
        assertThat(content.isMissingNode(), is(false));
        assertThat(content.asText().length() > 0, is(true));
    }

}
