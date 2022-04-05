package com.zq.mapper;

import com.zq.domain.User;

import java.util.List;

/**
 * TODO
 *
 * @author zq007
 * @version V1.0
 * @date 2022/3/25 10:23
 */
public interface UserMapper {
    List<User> findAll();
}
