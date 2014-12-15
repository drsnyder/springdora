package com.wikia.gateway;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.wikia.gateway.Mercury.MercuryResponse;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class MercuryGateway {

    public final String siteUrl;

    private MercuryGateway(Builder builder) {
        siteUrl = builder.siteUrl;
    }

    public MercuryResponse get(String title) throws java.io.IOException {
        // http://muppet.wikia.com/api/v1/Mercury/Article?title=Kermit%20the%20Frog
        String query = String.format("title=%s", URLEncoder.encode(title, "UTF-8"));
        URLConnection connection = new URL(this.siteUrl + "api/v1/Mercury/Article?" + query).openConnection();
        InputStream response = connection.getInputStream();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode json = objectMapper.readTree(response);
        ObjectReader reader = objectMapper.reader(MercuryResponse.class);

        return reader.readValue(json.path("data"));
    }

    public static final class Builder {
        private String siteUrl;

        public Builder() {
        }

        public Builder baseURL(String siteUrl) {
            this.siteUrl = siteUrl;
            return this;
        }

        public MercuryGateway build() {
            return new MercuryGateway(this);
        }
    }
}
