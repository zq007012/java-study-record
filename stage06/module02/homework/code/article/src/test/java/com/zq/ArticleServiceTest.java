package com.zq;

import com.zq.domain.Article;
import com.zq.service.ArticleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * TODO
 *
 * @author zq007
 * @version V1.0
 * @date 2022/4/19 11:38
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class ArticleServiceTest {

    @Autowired
    @Qualifier("articleService")
    private ArticleService articleService;

    /**
     * 测试{@link ArticleService#save}方法的功能
     */
    @Test
    public void saveTest(){
        Article article = new Article();
        article.setTitle("黑百合");
        article.setContent("一枪一个");

        articleService.save(article);
    }
}
