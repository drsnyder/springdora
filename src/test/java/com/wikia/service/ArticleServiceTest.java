package com.wikia.service;

import com.wikia.api.resource.ArticleResource;
import com.wikia.gateway.Mercury.MercuryAPIRequestException;
import com.wikia.gateway.MercuryGateway;
import junit.framework.TestCase;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;

import java.io.InputStream;

@ContextConfiguration
public class ArticleServiceTest extends TestCase {


    private ApplicationContext appContext = new ClassPathXmlApplicationContext();

    private InputStream kermitInputStream;
    private InputStream bogusInputStream;

    public void setUp() throws java.io.IOException {
        this.kermitInputStream = this.appContext.getResource("classpath:KermitTheFrog.json").getInputStream();
        this.bogusInputStream  = this.appContext.getResource("classpath:bogus.json").getInputStream();
    }

    @Test
    public void testGetArticle() throws java.io.IOException {
        String title = "Kermit the Frog";
        ArticleService articleService = new ArticleService(getMercuryGatewayGetMercuryAPIResponseMock(title));
        ArticleResource articleResource = articleService.getArticle(title);
        assertTrue(articleResource.getContent().length() > 0);
        assertTrue(articleResource.getTitle().length() > 0);
        assertEquals((Integer) 50, articleResource.getArticleId());
    }

    @Test
    public void testGetArticleBogusData() throws java.io.IOException {
        String title = "Kermit the Frog";
        ArticleService articleService = new ArticleService(getBogusMercuryGatewayGetMercuryAPIResponseMock(title));

        try {
            ArticleResource articleResource = articleService.getArticle(title);
            fail("Failed to catch MercuryAPIRequestException");
        } catch (MercuryAPIRequestException e) {
        }
    }

    public MercuryGateway getMercuryGatewayGetMercuryAPIResponseMock(String title) throws java.io.IOException {
        MercuryGateway mercuryGateway = mock(MercuryGateway.class);
        when(mercuryGateway.getMercuryAPIResponse(title)).thenReturn(this.kermitInputStream);
        when(mercuryGateway.get(title)).thenCallRealMethod();
        return mercuryGateway;
    }

    public MercuryGateway getBogusMercuryGatewayGetMercuryAPIResponseMock(String title) throws java.io.IOException {
        MercuryGateway mercuryGateway = mock(MercuryGateway.class);
        when(mercuryGateway.getMercuryAPIResponse(title)).thenReturn(this.bogusInputStream);
        when(mercuryGateway.get(title)).thenCallRealMethod();
        return mercuryGateway;
    }

}