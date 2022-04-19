package com.zq;

import com.zq.dao.ArticleDao;
import com.zq.domain.Article;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * TODO
 *
 * @author zq007
 * @version V1.0
 * @date 2022/4/19 10:54
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class ArticleDaoTest {
    @Autowired
    @Qualifier("articleDao")
    private ArticleDao articleDao;
    /**
     * 测试{@link ArticleDao#findAll}方法的功能
     */
    @Test
    public void findAllTest(){
        List<Article> articleList = articleDao.findAll();
        for (Article article : articleList) {
            System.out.println(article);
        }
    }

    /**
     * 测试{@link ArticleDao#findByArticleId(Integer)}方法的功能
     */
    @Test
    public void findByArticleIdTest(){
        System.out.println(articleDao.findByArticleId(1));
    }

    /**
     * 测试{@link ArticleDao#save(Article)}方法的功能
     */
    @Test
    public void saveTest(){
        Article article = new Article();
        article.setTitle("Mercy");
        article.setContent("Hero's never die.");
        System.out.println(articleDao.save(article));
    }

    /**
     * 测试{@link ArticleDao#update(Article)}方法的功能
     */
    @Test
    public void updateTest(){
        Article article = new Article();
        article.setId(3);
        article.setTitle("天使");
        article.setContent("英雄不死");
        System.out.println(articleDao.update(article));
    }

    /**
     * 测试{@link ArticleDao#delete(Integer)}方法的功能
     */
    @Test
    public void deleteTest(){
        System.out.println(articleDao.delete(3));
    }
}
