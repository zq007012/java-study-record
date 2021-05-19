package com.zq.store.dao;

import com.zq.dbconnpool.DruidPool;
import com.zq.store.entity.Orders;
import com.zq.store.entity.User;
import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.BeanProcessor;
import org.apache.commons.dbutils.GenerousBeanProcessor;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;

import javax.sql.DataSource;
import java.sql.Date;
import java.sql.SQLException;

/**
 * @ClassName: UserDao
 * @Description: TODO
 * @Author: zq007
 * @Date: 2021/3/15 20:57
 * @Version: V1.0
 */
public class UserDao {
    private static UserDao instance;
    private DataSource dataSource;

    private UserDao() throws Exception {
        setDataSource(DruidPool.getInstance().getDataSource());
    }

    public static UserDao getInstance() throws Exception {
        if (null == instance) {
            synchronized (UserDao.class) {
                if (null == instance) {
                    instance = new UserDao();
                }
            }
        }
        return instance;
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * 根据Orders对象的外键userId获取User的对象, 返回值为null是说明没有这个对象
     *
     * @param orders
     * @return
     * @throws SQLException
     */
    public User hasUser(Orders orders) throws SQLException {
        return getUserByUserId(orders.getUserIdForeign());
    }

    /**
     * 根据userId获取User的对象, 返回值为null是说明没有这个对象
     *
     * @param userId
     * @return
     * @throws SQLException
     */
    public User getUserByUserId(String userId) throws SQLException {
        // 1. 编写SQL语句
        // language=MySQL
        String sql = "select * from user where user_id = ?";
        // 2. 创建QueryRunner对象
        QueryRunner queryRunner = new QueryRunner(dataSource);
        // 3. 创建一个可以下划线与驼峰命名转换的BeanHandler对象
        GenerousBeanProcessor gbp = new GenerousBeanProcessor();
        BasicRowProcessor brp = new BasicRowProcessor(gbp);
        BeanHandler<User> beanHandler = new BeanHandler<>(User.class, brp);
        //4. 执行查找并以JavaBean的形式获得结果
        return queryRunner.query(sql, beanHandler, userId);
    }

    public User signInUser(String userName, String password) throws SQLException {
        // 1. 编写SQL语句
        // language=MySQL
        String sql = "select * from user where username = ? and password = ?";
        // 2. 创建QueryRunner对象
        QueryRunner queryRunner = new QueryRunner(dataSource);
        // 3. 创建一个可以下划线与驼峰命名转换的BeanHandler对象
        GenerousBeanProcessor gbp = new GenerousBeanProcessor();
        BasicRowProcessor brp = new BasicRowProcessor(gbp);
        BeanHandler<User> beanHandler = new BeanHandler<>(User.class, brp);
        //4. 执行查找并以JavaBean的形式获得结果
        return queryRunner.query(sql, beanHandler, userName,password);
    }

    public boolean signUpUser(User user) throws SQLException {
        return signUpUser(user.getUserId(), user.getUsername(),
                user.getPassword(),
                user.getTelephone(), user.getBirthday(), user.getGender());
    }

    public boolean signUpUser(String userId, String username, String password,
                              String telephone, Date birthday, String gender) throws SQLException {
        if (hasUser(username)) {
            return false;
        }
        // language=MySQL
        String sql = "insert into user(user_id, username, password, " +
                "telephone, birthday, gender) values(?,?,?,?,?,?)";

        QueryRunner queryRunner = new QueryRunner(dataSource);
        return 1 == queryRunner.update(sql, userId, username, password,
                telephone, birthday,
                gender);
    }

    private boolean hasUser(String userName) throws SQLException {
        // language=MySQL
        String sql = "select username from user where username = ?";
        QueryRunner queryRunner = new QueryRunner(dataSource);
        return null != queryRunner.query(sql, new ArrayHandler(), userName);
    }

    public User hasUser(String username, String passworrd) throws SQLException {
        // language=MySQL
        String sql = "select * from user where username = ? and password = ?";
        QueryRunner queryRunner = new QueryRunner(dataSource);
        return queryRunner.query(sql, new BeanHandler<>(User.class), username,
                passworrd);
    }
}
