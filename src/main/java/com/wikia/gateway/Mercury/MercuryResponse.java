package com.wikia.gateway.Mercury;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;

public class MercuryResponse {

    public final JsonNode details;
    public final JsonNode topContributors;
    public final JsonNode article;
    public final JsonNode relatedPages;
    public final JsonNode adsContext;

    public MercuryResponse(@JsonProperty("details") JsonNode details,
                           @JsonProperty("topContibutors") JsonNode topContributors,
                           @JsonProperty("article") JsonNode article,
                           @JsonProperty("relatedPages") JsonNode relatedPages,
                           @JsonProperty("adsContext") JsonNode adsContext) {
        this.details = details;
        this.topContributors = topContributors;
        this.article = article;
        this.relatedPages = relatedPages;
        this.adsContext = adsContext;
    }
}

