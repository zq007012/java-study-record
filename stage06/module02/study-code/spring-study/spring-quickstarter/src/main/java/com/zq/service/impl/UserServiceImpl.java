package com.zq.service.impl;

import com.zq.dao.UserDao;
import com.zq.service.UserService;

/**
 * TODO
 *
 * @author zq007
 * @version V1.0
 * @date 2022/4/7 14:29
 */
public class UserServiceImpl implements UserService{
    private UserDao userDao;

    public UserServiceImpl() {
    }

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    @Override
    public String toString() {
        return "UserServiceImpl{" +
                "userDao=" + userDao +
                '}';
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void save() {
        userDao.save();
    }
}
