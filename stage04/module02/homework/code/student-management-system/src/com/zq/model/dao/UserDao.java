package com.zq.model.dao;

import com.zq.interfaces.BaseDao;
import com.zq.model.javabean.Student;
import com.zq.model.javabean.User;
import com.zq.utils.EmptyUtils;
import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.GenerousBeanProcessor;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @ClassName: UserDao
 * @Description: TODO
 * @Author: zq007
 * @Date: 2021/5/30 22:21
 * @Version: V1.0
 */
public abstract class UserDao extends BaseDao<User> {
    public UserDao() {

    }

    public UserDao(DataSource dataSource) {
        super(dataSource);
    }

    /**
     * 更新用户标记登录成功时的sessionId, 同时还更新了date
     * @param user
     * @param sessionId
     * @return
     */
    public abstract boolean updateSessionIdAndDate(User user, String sessionId);

}
