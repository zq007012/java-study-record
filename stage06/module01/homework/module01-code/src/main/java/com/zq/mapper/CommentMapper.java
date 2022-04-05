package com.zq.mapper;

import com.zq.domain.Comment;

import java.util.List;

/**
 * TODO
 *
 * @author zq007
 * @version V1.0
 * @date 2022/4/1 15:48
 */
public interface CommentMapper {

    /**
     * 根据外键a_id获取所有匹配的评论信息
     * @param articleId
     * @return
     */
    List<Comment> findByArticleId(Integer articleId);
}
