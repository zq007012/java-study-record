package com.zq.service.impl;

import com.zq.dao.ArticleDao;
import com.zq.domain.Article;
import com.zq.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * TODO
 *
 * @author zq007
 * @version V1.0
 * @date 2022/4/19 11:20
 */
@Service("articleService")
public class ArticleServiceImpl implements ArticleService{

    @Autowired
    @Qualifier("articleDao")
    private ArticleDao articleDao;

    @Override
    public List<Article> findAll() {
        return articleDao.findAll();
    }

    @Override
    public Article findByAritcleId(Integer articleId) {
        return articleDao.findByArticleId(articleId);
    }

    @Override
    public void save(Article article) {
        articleDao.save(article);
    }

    @Override
    public void update(Article article) {
        articleDao.update(article);
    }

    @Override
    public void delete(Integer articleId) {
        articleDao.delete(articleId);
    }
}
