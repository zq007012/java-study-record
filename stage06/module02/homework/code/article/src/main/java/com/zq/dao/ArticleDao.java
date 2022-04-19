package com.zq.dao;

import com.zq.domain.Article;

import java.util.List;

/**
 * TODO
 *
 * @author zq007
 * @version V1.0
 * @date 2022/4/19 10:36
 */
public interface ArticleDao {
    List<Article> findAll();

    Article findByArticleId(Integer articleId);

    int save(Article article);

    int update(Article article);

    int delete(Integer articleId);
}
