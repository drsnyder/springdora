package com.wikia.gateway;

import com.fasterxml.jackson.databind.JsonNode;
import junit.framework.TestCase;
import org.junit.Test;


import static org.hamcrest.Matchers.*;
import static org.springframework.test.util.MatcherAssertionErrors.assertThat;

public class MercuryGatewayTest extends TestCase {

    public void testGet() throws Exception {
        MercuryGateway muppet = new MercuryGateway.Builder().baseURL("http://muppet.wikia.com/").build();
        JsonNode response = muppet.get("Kermit the Frog");
        JsonNode data = response.path("data");

        assertThat(data.isMissingNode(), is(false));
    }
}