package com.zq.mapper;

import com.zq.domain.SysRole;
import com.zq.domain.User;

import java.util.List;

/**
 * TODO
 *
 * @author zq007
 * @version V1.0
 * @date 2022/3/29 10:33
 */
public interface SysRoleMapper {
    /**
     * 嵌套查询所有的系统角色信息, 且每条系统角色都携带有对应的用户信息
     * @return
     */
    List<SysRole> findAll();

    /**
     * 嵌套查询所有的系统角色信息, 且每条系统角色都携带有对应的用户信息
     * @return
     */
    List<SysRole> findAll2();
}
