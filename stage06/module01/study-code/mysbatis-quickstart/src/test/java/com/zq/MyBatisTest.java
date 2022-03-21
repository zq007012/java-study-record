package com.zq;

import com.zq.domain.User;
import com.zq.utils.DateTimeUtils;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * TODO
 *
 * @author zq007
 * @version V1.0
 * @date 2022/3/20 20:17
 */
public class MyBatisTest{
    /**
     * 
     */
    @Test
    public void testMyBatisQuickStart() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 4. 执行sql
        List<User> users = sqlSession.selectList("user.findAll");
        for (User user :
                users) {
            System.out.println(user.toString());
        }
        // 5. 关闭sqlSession, 释放资源
        sqlSession.close();

    }

    /**
     *
     */
    @Test
    public void addUserTest() throws IOException {
        // 1. 加载MyBatis的核心配置文件'SqlMapConfig.xml'
        InputStream inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        // 2. 获取Sql会话工厂对象'SqlSessionFactory'
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        // 3. 利用Sql会话工厂开启sql会话'SqlSession', 这个'sqlSession'是默认开启事务的, 所以进行'增删改'的时候要注意事务提交的问题
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 4. 执行sql,
        User burin = new User();
        burin.setUsername("布琳");
        burin.setBirthday(DateTimeUtils.getDateTime());
        burin.setGender("女");
        burin.setAddress("蛋糕岛");
        System.out.println(burin.toString());
        // 4.1 执行sql
        int row = sqlSession.insert("userMapper.addUser", burin);
        // 4.2 sqlSession是默认开始事务的, 必须提交事务后才能成功修改数据库
        sqlSession.commit();

        // 5. 关闭sqlSession, 释放资源
        sqlSession.close();

    }

    /**
     *
     */
    @Test
    public void updateTest() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        User burinToTifa = new User();
        burinToTifa.setId(4);
        burinToTifa.setUsername("蒂法");
        burinToTifa.setBirthday(DateTimeUtils.getDateTime());
        burinToTifa.setGender("女");
        burinToTifa.setAddress("第7天堂");

        sqlSession.update("userMapper.updateUser",burinToTifa);
        sqlSession.commit();

        sqlSession.close();
    }

    /**
     *
     */
    @Test
    public void deleteUserTest() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        User user = new User();
        user.setId(4);

        int row = sqlSession.delete("userMapper.deleteUser", user);
        sqlSession.commit();

        sqlSession.close();
    }
}
