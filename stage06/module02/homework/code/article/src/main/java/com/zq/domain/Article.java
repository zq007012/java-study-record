package com.zq.domain;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.NoArgsConstructor;

/**
 * TODO
 * @author zq007
 * @version V1.0
 * @date 2022-04-19 
 */
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Article implements Serializable {

    private static final long serialVersionUID =  4187191408556730857L;

    /**
     * 文章id
     */
    @Setter
    @Getter
    private Integer id;

    /**
     * 文章标题
     */
    @Setter
    @Getter
    private String title;

    /**
     * 文章内容
     */
    @Setter
    @Getter
    private String content;

}
