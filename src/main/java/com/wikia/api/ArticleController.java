package com.wikia.api;

import com.wikia.Config;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import com.codahale.metrics.Meter;
import com.wikia.api.resource.ArticleResource;
import com.wikia.service.ArticleService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/articles")
public class ArticleController  {

    private final Meter requests = Config.getInstance().metricRegistry.meter("requests.articles");

    private ArticleService articleService;

    @RequestMapping(value = "/{title}", method = RequestMethod.GET)
    @ResponseBody
    public HttpEntity<ArticleResource> get(@PathVariable String title) throws java.io.IOException {
        requests.mark();
        ArticleResource article = this.getArticleService().getArticle(title);
        article.add(linkTo(methodOn(ArticleController.class).get(title)).withSelfRel());

        return new ResponseEntity<>(article, HttpStatus.OK);
    }


    @RequestMapping(value = "/default", method = RequestMethod.GET)
    @ResponseBody
    public HttpEntity<ArticleResource> getDefault() throws java.io.IOException {
        requests.mark();
        ArticleResource article = this.getArticleService().getDefaultArticle();
        article.add(linkTo(methodOn(ArticleController.class).getDefault()).withSelfRel());

        return new ResponseEntity<>(article, HttpStatus.OK);
    }

    public ArticleController setArticleService(ArticleService articleService) {
        this.articleService = articleService;
        return this;
    }

    public ArticleService getArticleService() {
        if (this.articleService == null) {
           this.articleService = new ArticleService();
        }

        return this.articleService;
    }
}
