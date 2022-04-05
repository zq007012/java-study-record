package com.zq;

import com.zq.domain.Article;
import com.zq.mapper.ArticleMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * TODO
 *
 * @author zq007
 * @version V1.0
 * @date 2022/4/1 15:50
 */
public class ArticleMapperTest{
    private SqlSession sqlSession;
    private Logger logger;

    @Before
    public void init() throws IOException {
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
        sqlSession = sqlSessionFactory.openSession();

        logger = LogManager.getLogger("com.zq");
    }

    @After
    public void ending(){
        sqlSession.close();
    }


    /**
     * 测试{@link ArticleMapper#findAll}方法的功能
     */
    @Test
    public void findAllTest(){
        ArticleMapper mapper = sqlSession.getMapper(ArticleMapper.class);
        List<Article> articleList = mapper.findAll();
        for (Article article : articleList) {
            logger.info(article);
        }
    }
    /**
     * 测试{@link ArticleMapper#findAllWithComments()}方法的功能
     */
    @Test
    public void findAllWithCommentsTest(){
        ArticleMapper mapper = sqlSession.getMapper(ArticleMapper.class);
        List<Article> articleList = mapper.findAllWithComments();
        for (Article article : articleList) {
            article.getId();
        }
    }


}
