package com.zq.factory;

import com.zq.dao.UserDao;
import com.zq.dao.impl.UserDaoImpl;

/**
 * TODO
 *
 * @author zq007
 * @version V1.0
 * @date 2022/4/7 10:48
 */
public class UserDaoFactory {
    public UserDao createUserDao(){
        return  new UserDaoImpl();
    }
}
