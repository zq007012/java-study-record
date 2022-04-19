package com.zq.service.impl;

import com.zq.dao.UserDao;
import com.zq.dao.impl.UserDaoImpl;
import com.zq.service.UserService;
import com.zq.utils.BeanFactory;

/**
 * TODO
 *
 * @author zq007
 * @version V1.0
 * @date 2022/4/6 8:37
 */
public class UserServiceImpl implements UserService {
    @Override
    public void save() {
        UserDao userDao = (UserDao) BeanFactory.getBean("userDao");
        userDao.save();
    }
}
