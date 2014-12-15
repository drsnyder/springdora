package com.wikia.gateway;

import com.fasterxml.jackson.databind.JsonNode;
import com.wikia.gateway.Mercury.MercuryResponse;
import junit.framework.TestCase;
import org.junit.Test;


import static org.hamcrest.Matchers.*;
import static org.springframework.test.util.MatcherAssertionErrors.assertThat;

public class MercuryGatewayTest extends TestCase {

    public void testGet() throws Exception {
        MercuryGateway muppet = new MercuryGateway("http://muppet.wikia.com/");
        MercuryResponse response = muppet.get("Kermit the Frog");
        assertThat(response.details.isMissingNode(), is(false));
    }
}