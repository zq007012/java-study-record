package com.zq.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * TODO
 *
 * @author zq007
 * @version V1.0
 * @date 2022-03-28
 */
@Data
@NoArgsConstructor
public class SysRole implements Serializable {

    private static final long serialVersionUID = 7900538075074816798L;

    private Integer id;

    private String roleName;

    private String roleDesc;


    /**
     * SysRole与{@link User}是多对多关系
     * SysRole是多, {@link User}是另一个多
     * {@link SysUserRole}是中间表
     * 'userList'是SysRole对象对应的所有另一个多{@link User}对象的集合
     */
    private List<User> userList;


}
