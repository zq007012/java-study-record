package com.zq.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * TODO
 *
 * @author zq007
 * @version V1.0
 * @date 2022-03-20
 */
@Data
@NoArgsConstructor
public class User implements Serializable {

    private static final long serialVersionUID = 7325214212926664557L;

    /**
     * user的id, 是本表的主键
     */
    private int id;

    /**
     * 用户名称
     */
    private String username;

    /**
     * 生日
     */
    private String birthday;

    /**
     * 性别
     */
    private String gender;

    /**
     * 地址
     */
    private String address;

}
