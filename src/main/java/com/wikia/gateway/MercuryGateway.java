package com.wikia.gateway;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.wikia.gateway.Mercury.MercuryAPIRequestException;
import com.wikia.gateway.Mercury.MercuryResponse;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class MercuryGateway {

    public final String siteUrl;
    private ApplicationContext appContext = new ClassPathXmlApplicationContext();

    public MercuryGateway(String siteUrl) {
        this.siteUrl = siteUrl;
    }

    public MercuryResponse get(String title) throws java.io.IOException {
        return inputStreamToMercuryResponse(this.getMercuryAPIResponse(title));
    }

    public InputStream getMercuryAPIResponse(String title) throws java.io.IOException {
        // http://muppet.wikia.com/api/v1/Mercury/Article?title=Kermit%20the%20Frog
        String query = String.format("title=%s", URLEncoder.encode(title, "UTF-8"));
        URLConnection connection = this.getURL(title).openConnection();
        return connection.getInputStream();
    }

    public MercuryResponse getDefaultMercuryAPIResponse() throws java.io.IOException {
        InputStream file = this.appContext.getResource("classpath:KermitTheFrog.json").getInputStream();
        return inputStreamToMercuryResponse(file);
    }

    public MercuryResponse inputStreamToMercuryResponse(InputStream stream) throws java.io.IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode json = objectMapper.readTree(stream);
        ObjectReader reader = objectMapper.reader(MercuryResponse.class);

        if (json.path("data").isMissingNode()) {
            throw new MercuryAPIRequestException("Error: top level key \"data\" is missing from request for stream");
        }

        return reader.readValue(json.path("data"));
    }

    public URL getURL(String title) throws java.io.IOException {
        // http://muppet.wikia.com/api/v1/Mercury/Article?title=Kermit%20the%20Frog
        String query = String.format("title=%s", URLEncoder.encode(title, "UTF-8"));
        return new URL(this.siteUrl + "api/v1/Mercury/Article?" + query);

    }
}

