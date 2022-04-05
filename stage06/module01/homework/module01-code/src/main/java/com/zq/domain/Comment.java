package com.zq.domain;

import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TODO
 * @author zq007
 * @version V1.0
 * @date 2022-04-01 
 */
@Data
@NoArgsConstructor
public class Comment implements Serializable {

    private static final long serialVersionUID =  3767000368794647867L;

    /**
     * 评论id
     */
    private Integer id;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 评论作者
     */
    private String author;

    /**
     * 关联的文章id
     */
    private Integer aId;

    /**
     * ∞对1的数据库表关系中, 在 ∞ 的一方的实体类里, 会封装一个 1 的对象
     * ∞的类型是{@link Comment},
     * 1的类型是{@link Article}
     */
    private Article article;
}
