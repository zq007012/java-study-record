package com.zq.model.dao;

import com.zq.interfaces.BaseDao;
import com.zq.model.javabean.User;
import com.zq.utils.DateTimeUtils;
import com.zq.utils.EmptyUtils;
import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.GenerousBeanProcessor;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @ClassName: UserDaoImp
 * @Description: TODO
 * @Author: zq007
 * @Date: 2021/5/26 16:30
 * @Version: V1.0
 */
public class UserDaoImp extends UserDao {
    public UserDaoImp() {
    }

    public UserDaoImp(DataSource dataSource) {
        super(dataSource);
    }

    /**
     * 更新用户标记登录成功时的sessionId, 同时还更新了date
     *
     * @param user
     * @param sessionId
     * @return
     */
    @Override
    public boolean updateSessionIdAndDate(User user, String sessionId) {
        //1. 编写SQL语句
        // language=MySQL
        String sql = "update user set session_id_of_last_mark_login_successfully = ?, date_of_last_mark_login_successfully = ? where user_id = ?";
        // 2. 创建QueryRunner
        //QueryRunner queryRunner = new QueryRunner(dataSource);
        // 3. 执行增删改操作
        int result = 0;
        String date = DateTimeUtils.getDate();
        try {
            result = getQueryRunner().update(sql,
                    sessionId,
                    date,
                    user.getUserId());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(result > 0){
            user.setSessionIdOfLastMarkLoginSuccessfully(sessionId);
            user.setDateOfLastMarkLoginSuccessfully(date);
            return true;
        }else {
            return false;
        }

    }

    /**
     * 用来向表中插入一条记录, 在插入之前应该先通过retrieve方法判断表中是否有相同的记录
     *
     * @param user 要插入到表中的JavaBean对象
     * @return 返回true, 表明插入成功, 返回false, 表明插入失败
     */
    @Override
    public boolean create(User user) {
        //1. 编写SQL语句
        // language=MySQL
        String sql = "insert into user values(?,?,?,?,?)";
        // 2. 创建QueryRunner

        // 3. 执行增删改操作
        int result = 0;
        try {
            result = getQueryRunner().update(sql, user.getUserId(), user.getUserName(),
                    user.getPassword(), user.getSessionIdOfLastMarkLoginSuccessfully(),
                    user.getDateOfLastMarkLoginSuccessfully());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result > 0;
    }

    /**
     * 用来根据某列字段的值来查找到所有匹配的数据
     *
     * @param fieldName 字段的名字, 可以到{@link User}的常量中获取
     * @param value     需要查找的值
     * @return 返回值是个集合, 如果返回值为null或者集合的长度为0, 则说明没有匹配的数据
     */
    @Override
    public List<User> retrieve(String fieldName, String value) {
        // 1. 编写SQL语句
        // language=MySQL
        String sql = "select * from user where " + fieldName + " = ?";
        // 2. 创建QueryRunner对象

        // 3. 创建一个可以下划线与驼峰命名转换的BeanHandler对象
        GenerousBeanProcessor gbp = new GenerousBeanProcessor();
        BasicRowProcessor brp = new BasicRowProcessor(gbp);
        BeanListHandler<User> beanListHandler = new BeanListHandler<>(User.class, brp);
        //4. 执行查找并以JavaBean集合的形式获得结果
        List<User> query = null;
        try {
            query = getQueryRunner().query(sql, beanListHandler, value);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return query;
    }

    /**
     * 用来根据某列字段的值来模糊查询到所有匹配的数据, where fileName like concat('%',?,'%')
     *
     * @param fieldName 字段的名字, 可以到{@link User}的常量中获取
     * @param keyword   需要模糊查找的关键字
     * @return 返回值是个集合, 如果返回值为null, 或者集合的长度为0, 则说明没有匹配的数据
     */
    @Override
    public List<User> retrieveFuzzily(String fieldName, String keyword) {
        // 1. 编写SQL语句
        // language=MySQL
        String sql = "select * from user where " + fieldName + " like concat('%',?,'%')";
        // 2. 创建QueryRunner对象

        // 3. 创建一个可以下划线与驼峰命名转换的BeanHandler对象
        GenerousBeanProcessor gbp = new GenerousBeanProcessor();
        BasicRowProcessor brp = new BasicRowProcessor(gbp);
        BeanListHandler<User> beanListHandler = new BeanListHandler<>(User.class, brp);
        //4. 执行查找并以JavaBean集合的形式获得结果
        List<User> query = null;
        try {
            query = getQueryRunner().query(sql, beanListHandler, keyword);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return query;
    }

    /**
     * 获取表上的所有记录
     *
     * @return 返回表上的记录所组成的集合
     */
    @Override
    public List<User> retrieveAll() {
        // 1. 编写SQL语句
        // language=MySQL
        String sql = "select * from user";
        // 2. 创建QueryRunner对象

        // 3. 创建一个可以下划线与驼峰命名转换的BeanHandler对象
        GenerousBeanProcessor gbp = new GenerousBeanProcessor();
        BasicRowProcessor brp = new BasicRowProcessor(gbp);
        BeanListHandler<User> beanListHandler = new BeanListHandler<>(User.class, brp);
        //4. 执行查找并以JavaBean集合的形式获得结果
        List<User> query = null;
        try {
            query = getQueryRunner().query(sql, beanListHandler);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return query;
    }

    /**
     * 修改一条JavaBean对象中的除了javaBeanId属性之外的其它属性中的一个属性,
     *
     * @param primaryId 需要进行修改的记录的主键id
     * @param fieldName 需要修改的字段
     * @param value     需要改成的值
     * @return 返回true表示修改成功, 返回false表示修改失败
     */
    @Override
    public boolean update(String primaryId, String fieldName, String value) {
        if (User.USER_ID.equals(fieldName)){
            return false;
        }
        //1. 编写SQL语句
        // language=MySQL
        String sql = "update user set " + fieldName + " = ? where user_id = ?";
        // 2. 创建QueryRunner

        // 3. 执行增删改操作
        int result = 0;
        try {
            result = getQueryRunner().update(sql, value, primaryId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result > 0;
    }

    /**
     * 修改一条JavaBean对象中的除了javaBeanId属性之外的其它属性,
     *
     * @param user 需要修改的JavaBean对象, 注意代表这个JavaBean对象的'id'是不可以改的
     * @return 返回true表示修改成功, 返回false表示修改失败
     */
    @Override
    public boolean updateExcludePrimaryId(String primaryId, User user) {
        //1. 编写SQL语句
        // language=MySQL
        String sql = "update user set user_name = ?, password=?,session_id_of_last_mark_login_successfully = ?, date_of_last_mark_login_successfully=? where user_id=?";
        // 2. 创建QueryRunner

        // 3. 执行增删改操作
        int result = 0;
        try {
            result = getQueryRunner().update(sql, user.getUserName(),
                    user.getPassword(),
                    user.getSessionIdOfLastMarkLoginSuccessfully(),
                    user.getDateOfLastMarkLoginSuccessfully(),
                    primaryId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result > 0;


    }

    /**
     * 删除符合条件的记录
     *
     * @param fieldName
     * @param value
     * @return 用来表示删除了几条信息, 返回值小于1, 则说明删除失败
     */
    @Override
    public int delete(String fieldName, String value) {
        //1. 编写SQL语句
        // language=MySQL
        String sql = "delete from user where " + fieldName + "=?";
        // 2. 创建QueryRunner

        // 3. 执行增删改操作
        int result = 0;
        try {
            result = getQueryRunner().update(sql, value);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

}
