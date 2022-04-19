package com.zq.dao.impl;

import com.zq.dao.ArticleDao;
import com.zq.domain.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * TODO
 *
 * @author zq007
 * @version V1.0
 * @date 2022/4/19 10:38
 */
@Repository("articleDao")
public class ArticleDaoImpl implements ArticleDao {
    @Autowired
    @Qualifier("jdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Article> findAll() {
        // language=MySQL
        String sql = "select * from t_article";

        return jdbcTemplate.query(sql,
                new BeanPropertyRowMapper<>(Article.class));
    }

    @Override
    public Article findByArticleId(Integer articleId) {
        // language=MySQL
        String sql = "select * from t_article where id = ?";

        return jdbcTemplate.queryForObject(sql,
                new BeanPropertyRowMapper<>(Article.class),
                articleId);
    }

    @Override
    public int save(Article article) {
        //language=MySQL
        String sql = "insert into t_article(title,content) values(?,?)";
        return jdbcTemplate.update(sql, article.getTitle(), article.getContent());
    }

    @Override
    public int update(Article article) {
        //language=MySQL
        String sql = "update t_article set title=?, content=? where id = ?";
        return jdbcTemplate.update(
                sql,
                article.getTitle(),
                article.getContent(),
                article.getId());
    }

    @Override
    public int delete(Integer articleId) {
        //language=MySQL
        String sql = "delete from t_article where id = ?";
        return jdbcTemplate.update(sql,
                articleId);
    }
}
