package com.zq.domain;

import java.io.Serializable;
import java.util.List;

/**
 * TODO
 *
 * @author zq007
 * @version V1.0
 * @date 2022-04-01
 */
/*@Data
@NoArgsConstructor*/
public class Article implements Serializable {

    private static final long serialVersionUID = 3847875324824633233L;

    /**
     * 文章id
     */
    private Integer id;

    /**
     * 文章标题
     */
    private String title;

    /**
     * 文章内容
     */
    private String content;

    /**
     * 1对∞的数据库表关系中, 在 1 的一方的实体类里, 会封装一个 ∞ 的对象的集合
     * 1的类型是{@link Article},
     * ∞的类型是{@link Comment}
     */
    private List<Comment> commentList;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Article)) return false;

        Article article = (Article) o;

        if (!getId().equals(article.getId())) return false;
        if (!getTitle().equals(article.getTitle())) return false;
        if (!getContent().equals(article.getContent())) return false;
        return getCommentList().equals(article.getCommentList());
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getTitle().hashCode();
        result = 31 * result + getContent().hashCode();
        result = 31 * result + getCommentList().hashCode();
        return result;
    }

    public Article() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", commentList=" + commentList +
                '}';
    }
}
