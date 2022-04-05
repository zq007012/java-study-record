package com.zq.domain;

import java.io.Serializable;
import java.util.List;

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
public class User implements Serializable {

    private static final long serialVersionUID =  5289981467392964020L;

    /**
     * 主键id
     */
    private Integer id;

    /**
     * 用户名称
     */
    private String username;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 生日
     */
    private String birthday;

    /**
     * 性别
     */
    private String gender;

    /**
     * 登场作品
     */
    private String debutWorks;

    /**
     * 1对∞的数据库表关系中, 在 1 的一方的实体类里, 会封装一个 ∞ 的对象的集合
     * 1的类型是{@link User},
     * ∞的类型是{@link Orders}
     */
    private List<Orders> ordersList;

    /**
     * User与{@link SysRole}是多对多关系
     * User是多, {@link SysRole}是另一个多
     * {@link SysUserRole}是中间表
     * 'sysRoleList'是User对象对应的所有另一个多{@link SysRole}对象的集合
     */
    private List<SysRole> sysRoleList;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", age=" + age +
                ", birthday='" + birthday + '\'' +
                ", gender='" + gender + '\'' +
                ", debutWorks='" + debutWorks + '\'' +
                ", ordersList=" + ordersList +
                ", sysRoleList=" + sysRoleList +
                '}';
    }
}
