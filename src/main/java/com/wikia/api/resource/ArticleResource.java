package com.wikia.api.resource;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.ResourceSupport;

public class ArticleResource extends ResourceSupport {

    private final String content;
    private final Integer id;

    @JsonCreator
    public ArticleResource(@JsonProperty("content") String content, @JsonProperty("id") Integer id) {
        this.content = content;
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    /*
     * We need to add the @Json
     * https://github.com/spring-projects/spring-hateoas/issues/66
     */
    @JsonProperty("id")
    public Integer getArticleId() {
        return id;
    }

}
