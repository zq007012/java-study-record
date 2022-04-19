package com.zq.service;

import com.zq.domain.Article;

import java.util.List;

/**
 * TODO
 *
 * @author zq007
 * @version V1.0
 * @date 2022/4/19 11:18
 */
public interface ArticleService {
    List<Article> findAll();

    Article findByAritcleId(Integer articleId);

    void save(Article article);

    void update(Article article);

    void delete(Integer articleId);
}
