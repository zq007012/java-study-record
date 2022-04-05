package com.zq.domain;

import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TODO
 * @author zq007
 * @version V1.0
 * @date 2022-03-28 
 */
@Data
@NoArgsConstructor
public class Orders implements Serializable {

    private static final long serialVersionUID =  6749556648710680210L;

    private Integer id;

    private String ordertime;

    private Long total;

    private Integer uid;

    /**
     * ∞对1的数据库表关系中, 在 ∞ 的一方的实体类里, 会封装一个 1 的对象
     * ∞的类型是{@link Orders},
     * 1的类型是{@link User}
     */
    private User user;

}
