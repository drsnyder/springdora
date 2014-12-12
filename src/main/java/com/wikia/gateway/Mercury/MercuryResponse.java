package com.wikia.gateway.Mercury;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.net.URL;

public class MercuryResponse {

    public final Integer id;
    public final String title;
    public final String summary;
    public final URL thumbnail;

    public MercuryResponse(@JsonProperty("id") Integer id, @JsonProperty("title") String title,
                           @JsonProperty("abstract") String summary, @JsonProperty("thumbnail") String thumbnail) throws java.net.MalformedURLException {
        this.id = id;
        this.title = title;
        this.summary = summary;
        this.thumbnail = new URL(thumbnail);

    }

    private MercuryResponse(Builder builder) {
        id = builder.id;
        title = builder.title;
        summary = builder.summary;
        thumbnail = builder.thumbnail;
    }


    public static final class Builder {
        private Integer id;
        private String title;
        private String summary;
        private URL thumbnail;

        public Builder() {
        }

        public Builder id(Integer id) {
            this.id = id;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder summary(String summary) {
            this.summary = summary;
            return this;
        }

        public Builder thumbnail(URL thumbnail) {
            this.thumbnail = thumbnail;
            return this;
        }

        public MercuryResponse build() {
            return new MercuryResponse(this);
        }
    }
}
