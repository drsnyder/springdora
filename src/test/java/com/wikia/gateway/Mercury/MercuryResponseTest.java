package com.wikia.gateway.Mercury;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import junit.framework.TestCase;

import java.io.StringReader;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.util.MatcherAssertionErrors.assertThat;

public class MercuryResponseTest extends TestCase {


    public void testResponseDecoding() throws java.io.IOException {
        StringReader sr = new StringReader("{\"data\":{" +
                "\"details\":" +
                    "{\"title\":\"Kermit the Frog\",\"id\":50,\"abstract\":\"Kermit the Frog, arguably Jim Henson's most famous Muppet creation, was the star and host of The...\",\"thumbnail\":\"http://img2.wikia.nocookie.net/__cb20101015153557/muppet/images/thumb/7/79/Kermit-the-frog.jpg/200px-0%2C377%2C0%2C377-Kermit-the-frog.jpg\"}," +
                "\"topContributors\":" +
                    "[{\"user_id\": 10370,\"title\":\"Toughpigs\"}]," +
                "\"article\":" +
                    "{\"content\":\"some content\"}," +
                "\"relatedPages\":" +
                    "[{\"title\":\"Pigs\"},{\"title\":\"Bats\"}]," +
                "\"adsContext\":" +
                    "{\"opts\":{},\"targeting\":{},\"providers\":{},\"slots\":[],\"forceProviders\":[]}" +
                "}}}");
        ObjectMapper mapper = new ObjectMapper();
        JsonNode data = mapper.readTree(sr);
        MercuryResponse response = mapper.treeToValue(data.path("data"), MercuryResponse.class);
        assertThat(response.details.isMissingNode(), is(false));
    }

}