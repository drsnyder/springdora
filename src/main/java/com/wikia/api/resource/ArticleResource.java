package com.wikia.api.resource;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.ResourceSupport;

public class ArticleResource extends ResourceSupport {

    private final String content;
    private final Integer id;
    private final String title;
    private final String thumbnail;

    @JsonCreator
    public ArticleResource(@JsonProperty("content") String content,
                           @JsonProperty("id") Integer id,
                           @JsonProperty("title") String title,
                           @JsonProperty("thumbnail") String thumbnail) {
        this.content = content;
        this.id = id;
        this.title = title;
        this.thumbnail = thumbnail;
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

    public String getTitle() {
        return title;
    }

    public String getThumbnail() {
        return thumbnail;
    }

}
