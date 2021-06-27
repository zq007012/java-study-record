package com.zq.model.factory;

import com.zq.dbconnpool.DruidPool;
import com.zq.model.dao.UserDao;
import com.zq.model.dao.UserDaoImp;

/**
 * @ClassName: UserDaoFactory
 * @Description: TODO
 * @Author: zq007
 * @Date: 2021/5/30 22:31
 * @Version: V1.0
 */
public class UserDaoFactory{
    public static UserDao newUserDao() throws Exception {
        return new UserDaoImp(DruidPool.getInstance().getDataSource());
    }
}
