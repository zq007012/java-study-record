package com.zq.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * TODO
 * @author zq007
 * @version V1.0
 * @date 2022-03-25 
 */
@Data
@NoArgsConstructor
@ToString
public class User implements Serializable {

    private static final long serialVersionUID =  5347681743777973980L;

    /**
     * 主键id
     */
    /*private int ida;*/
    private int id;

    /**
     * 用户名称
     */
    /*private String usernamea;*/
    private String username;

    /**
     * 年龄
     */
    /*private int agea;*/
    private int age;

    /**
     * 生日
     */
    /*private String birthdaya;*/
    private String birthday;

    /**
     * 性别
     */
    private String gender;

    /**
     * 登场作品
     */
    private String debutWorks;

}
