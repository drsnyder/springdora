package com.wikia.gateway;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.wikia.gateway.Mercury.MercuryAPIRequestException;
import com.wikia.gateway.Mercury.MercuryResponse;

import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class MercuryGateway {

    public final String siteUrl;

    public MercuryGateway(String siteUrl) {
        this.siteUrl = siteUrl;
    }

    public MercuryResponse get(String title) throws java.io.IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode json = objectMapper.readTree(this.getMercuryAPIResponse(title));
        ObjectReader reader = objectMapper.reader(MercuryResponse.class);

        if (json.path("data").isMissingNode()) {
            throw new MercuryAPIRequestException(String.format("Error: top level key \"data\" is missing from request for %s", this.getURL(title)));
        }
        return reader.readValue(json.path("data"));
    }

    public InputStream getMercuryAPIResponse(String title) throws java.io.IOException {
        // http://muppet.wikia.com/api/v1/Mercury/Article?title=Kermit%20the%20Frog
        String query = String.format("title=%s", URLEncoder.encode(title, "UTF-8"));
        URLConnection connection = this.getURL(title).openConnection();
        return connection.getInputStream();
    }

    public URL getURL(String title) throws java.io.IOException {
        // http://muppet.wikia.com/api/v1/Mercury/Article?title=Kermit%20the%20Frog
        String query = String.format("title=%s", URLEncoder.encode(title, "UTF-8"));
        return new URL(this.siteUrl + "api/v1/Mercury/Article?" + query);

    }
}

