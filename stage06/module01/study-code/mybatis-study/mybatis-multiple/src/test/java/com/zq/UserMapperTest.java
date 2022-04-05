package com.zq;

import com.zq.domain.Orders;
import com.zq.domain.User;
import com.zq.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * TODO
 *
 * @author zq007
 * @version V1.0
 * @date 2022/3/29 8:51
 */
public class UserMapperTest {
    private SqlSession sqlSession;
    private Logger logger;

    @Before
    public void init() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        sqlSession = sqlSessionFactory.openSession();

        logger = LogManager.getLogger("com.zq");
    }

    /**
     * 测试{@link UserMapper#findAll}方法的功能
     */
    @Test
    public void findAllTest() {
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> userList = mapper.findAll();
        for (User user : userList) {
            System.out.println(user);
        }
    }

    /**
     * 测试{@link UserMapper#findAll2}方法的功能
     */
    @Test
    public void findAll2Test() {
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> userList = mapper.findAll2();
        for (User user : userList) {
            System.out.println(user);
        }

        System.out.println("---------------------------------------------");
        for (User user : userList) {
            List<Orders> ordersList = user.getOrdersList();
            System.out.println(user);
        }
    }

    /**
     *
     */
    @Test
    public void firstCacheTest() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession1 = sqlSessionFactory.openSession();

        UserMapper mapper = sqlSession1.getMapper(UserMapper.class);

        User user1 = mapper.findById(1);
        System.out.println(user1);
        System.out.println("sqlSession1第一次查询结束");
        User user2 = mapper.findById(1);
        System.out.println(user2);
        System.out.println("sqlSession1第二次查询结束");


    }

    /**
     *
     */
    @Test
    public void secondCacheTest() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession1 = sqlSessionFactory.openSession();
        logger.info("sqlSession1开启了");
        UserMapper mapper = sqlSession1.getMapper(UserMapper.class);
        User user = mapper.findById(1);
        logger.info(user);
        logger.info("userMapper对象使用了findById(1)");
        sqlSession1.close();
        logger.info("sqlSession1关闭了");

        SqlSession sqlSession2 = sqlSessionFactory.openSession();
        logger.info("sqlSession2开启了");
        UserMapper mapper2 = sqlSession2.getMapper(UserMapper.class);
        User user2 = mapper2.findById(1);
        logger.info(user2);
        logger.info("userMapper2对象使用了findById(1)");

    }
}
