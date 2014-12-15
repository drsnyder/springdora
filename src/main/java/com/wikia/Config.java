package com.wikia;

import com.codahale.metrics.MetricRegistry;

public class Config {

    private static Config instance = null;

    public final String mercuryBaseURL;
    public final MetricRegistry metricRegistry = new MetricRegistry();

    private Config(Builder builder) {
        mercuryBaseURL = builder.mercuryBaseURL;
    }

    public static Config getInstance() {
        if(instance == null) {
            instance = new Config.Builder().build();
        }

        return instance;
    }


    private static final class Builder {
        private String mercuryBaseURL = System.getenv("MERCURY_BASE_URL");

        public Builder() {
        }

        public Builder mercuryBaseURL(String mercuryBaseURL) {
            this.mercuryBaseURL = mercuryBaseURL;
            return this;
        }

        public Config build() {
            if (this.mercuryBaseURL == null) {
                this.mercuryBaseURL = "http://muppet.wikia.com/";
            }
            return new Config(this);
        }
    }
}
