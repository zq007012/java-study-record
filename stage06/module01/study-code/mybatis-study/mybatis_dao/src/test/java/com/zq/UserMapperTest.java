package com.zq;

import com.zq.domain.User;
import com.zq.mapper.UserMapper;
import com.zq.mapper.UserMapperImpl;
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
 * @date 2022/3/25 10:29
 */
public class UserMapperTest{
    /**
     *
     */
    @Test
    public void findAllDaoTest(){
        UserMapper mapper = new UserMapperImpl();
        List<User> users = mapper.findAll();
        System.out.println(users);
    }

    @Test
    public void findAllProxyTest() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<User> users = userMapper.findAll();

        System.out.println(users);

        sqlSession.close();
    }
}
