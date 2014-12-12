package com.wikia.api;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import com.wikia.api.resource.ArticleResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/articles")
public class ArticleController  {

    @RequestMapping(value = "/{title}", method = RequestMethod.GET)
    @ResponseBody
    public HttpEntity<ArticleResource> get(@PathVariable String title) {
        ArticleResource article = new ArticleResource("this is the content", 10);
        article.add(linkTo(methodOn(ArticleController.class).get(title)).withSelfRel());

        return new ResponseEntity<ArticleResource>(article, HttpStatus.OK);
    }

}
