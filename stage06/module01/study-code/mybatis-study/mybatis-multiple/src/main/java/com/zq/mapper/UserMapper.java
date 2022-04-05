package com.zq.mapper;

import com.zq.domain.User;
import lombok.NonNull;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

/**
 * TODO
 *
 * @author zq007
 * @version V1.0
 * @date 2022/3/29 8:38
 */
public interface UserMapper {
    /**
     * 查询所有的用户信息, 如果某个用户有订单信息的话,就把这些订单信息封装到用户对象中
     * @return
     */
    List<User> findAll();

    /**
     * 根据用户的id查到该用户的信息
     *
     * @param id
     * @return
     */
    User findById(Integer id);

    int deleteById(@NonNull Integer id);

    /**
     * 查询所有的用户信息, 如果某个用户有订单信息的话,就把这些订单信息封装到用户对象中
     * @return
     */
    List<User> findAll2();

    /**
     * 根据系统角色的id查询所有是该角色的用户
     * @param roleId
     * @return
     */
    List<User> findByRoleId(Integer roleId);
}
