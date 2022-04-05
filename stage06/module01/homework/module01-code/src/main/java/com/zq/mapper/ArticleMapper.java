package com.zq.mapper;

import com.zq.domain.Article;

import java.util.List;

/**
 * TODO
 *
 * @author zq007
 * @version V1.0
 * @date 2022/4/1 15:47
 */
public interface ArticleMapper {

    /**
     * 获取所有的文章信息
     * @return
     */
    List<Article> findAll();

    /**
     * 获取所有的文章信息, 每个文章对象都携带评论信息
     * @return
     */
    List<Article> findAllWithComments();
}
