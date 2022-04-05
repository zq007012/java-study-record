package com.zq;

import com.zq.domain.User;
import com.zq.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
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
 * @date 2022/4/4 14:02
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

    @After
    public void ending() {
        sqlSession.close();
    }

    /**
     * 测试{@link UserMapper#findAll}方法的功能
     */
    @Test
    public void findAllTest() {
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> userList = mapper.findAll();
        for (User user : userList) {
            logger.info(user);
        }
    }

    /**
     * 测试{@link UserMapper#addUser(User)}方法的功能
     */
    @Test
    public void addUserTest() {
        User user = new User();
        user.setUsername("萨姆斯");
        user.setAge(33);
        user.setBirthday("1989-12-20");
        user.setGender("女");
        user.setDebutWorks("银河战士");
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        int row = mapper.addUser(user);
        sqlSession.commit();
        logger.info("添加数据" + (row == 1 ? "成功" : "失败"));
    }

    /**
     * 测试{@link UserMapper#updateUser(User)}方法的功能
     */
    @Test
    public void updateUserTest() {
        User user = new User();
        user.setId(108);
        user.setUsername("萨姆斯");
        user.setAge(35);
        user.setBirthday("1987-11-21");
        user.setGender("女");
        user.setDebutWorks("银河战士");
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        int row = mapper.updateUser(user);
        sqlSession.commit();
        logger.info("更新数据" + (row == 1 ? "成功" : "失败"));
    }

    /**
     * 测试{@link UserMapper#deleteUserById(Integer)}方法的功能
     */
    @Test
    public void deleteUserByIdTest() {
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        int row = mapper.deleteUserById(108);
        sqlSession.commit();
        logger.info("删除数据" + (row == 1 ? "成功" : "失败"));
    }

    /**
     * 测试{@link UserMapper#findAllWithOrders()}方法的功能
     */
    @Test
    public void findAllWithOrdersTest() {
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> userList = mapper.findAllWithOrders();
        for (User user : userList) {
            logger.info(user);
        }
    }

    /**
     * 测试{@link UserMapper#findAllWithSysRoles()}方法的功能
     */
    @Test
    public void findAllWithSysRolesTest() {
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> userList = mapper.findAllWithSysRoles();
        for (User user : userList) {
            logger.info(user);
        }
    }
}
