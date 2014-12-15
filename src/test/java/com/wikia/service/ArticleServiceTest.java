package com.wikia.service;

import com.wikia.Config;
import com.wikia.api.resource.ArticleResource;
import junit.framework.TestCase;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration
public class ArticleServiceTest extends TestCase {

    @Test
    public void testGetArticle() throws java.io.IOException {
        ArticleService as = new ArticleService();
        ArticleResource a = as.getArticle("Kermit the Frog");
        assertTrue(a.getContent().length() > 0);
        assertTrue(a.getTitle().length() > 0);
        assertEquals((Integer)50, a.getArticleId());
    }

}