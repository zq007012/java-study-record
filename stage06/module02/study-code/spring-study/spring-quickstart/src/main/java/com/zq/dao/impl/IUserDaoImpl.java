package com.zq.dao.impl;

import com.zq.dao.IUserDao;

/**
 * TODO
 *
 * @author zq007
 * @version V1.0
 * @date 2022/4/3 9:55
 */
public class IUserDaoImpl implements IUserDao{

    @Override
    public String save() {
        return "dao被调用了";
    }
}
