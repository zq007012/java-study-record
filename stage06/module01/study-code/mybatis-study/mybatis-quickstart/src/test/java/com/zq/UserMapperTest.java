package com.zq;

import com.zq.domain.User;
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
 * @date 2022/3/24 15:48
 */
public class UserMapperTest {
    /**
     *
     */
    @Test
    public void selectTest() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        List<User> users = sqlSession.selectList("userMapper.findAll");

        System.out.println(users);
        sqlSession.close();
    }

    /**
     *
     */
    @Test
    public void insertTest() throws IOException {
        // 1. 加载MyBatis的核心配置文件'SqlMapConfig.xml'
        InputStream inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        // 2. 获取Sql会话工厂对象'SqlSessionFactory'
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        // 3. 利用Sql会话工厂开启sql会话'SqlSession', 这个'sqlSession'默认是关闭事务的自动提交功能的(也就是开启了事务), 所以进行'增删改'的时候要注意事务提交的问题
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 4. 执行sql,
        User burin = new User();
        burin.setUsername("布琳");
        burin.setAge(20);
        burin.setBirthday("2002-11-11");
        burin.setGender("女");
        System.out.println(burin.toString());
        // 4.1 执行sql
        int row = sqlSession.insert("userMapper.addUser", burin);
        // 4.2 sqlSession是默认关闭事务的自动提交功能的, 所以sql语句在执行后, 必须执行提交事务的操作才能成功修改数据库
        sqlSession.commit();

        // 5. 关闭sqlSession, 释放资源
        sqlSession.close();
    }

    /**
     *
     */
    @Test
    public void updateTest() throws IOException {
        // 1. 加载核心配置文件
        InputStream inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        // 2. 创建sqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        // 3. 开启sqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 4. 执行sql
        User burin = new User();
        burin.setId(16);
        burin.setUsername("布琳");
        burin.setAge(23);
        burin.setBirthday("1999-11-11");
        burin.setGender("女");
        burin.setDebutWorks("海贼王");
        // 4.1 执行修改记录的sql
        sqlSession.update("userMapper.updateUser", burin);
        // 4.2 提交事务, 因为修改数据改变了数据库, 所以必须提交事务才能修改成功
        sqlSession.commit();

        // 5. 关闭sqlSession, 释放资源
        sqlSession.close();
    }

    /**
     *
     */
    @Test
    public void deleteTest() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        int row = sqlSession.delete("userMapper.deleteUser", 1);
        sqlSession.commit();

        sqlSession.close();
    }
}
