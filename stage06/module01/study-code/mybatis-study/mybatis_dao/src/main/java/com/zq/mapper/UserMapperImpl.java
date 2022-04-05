package com.zq.mapper;

import com.zq.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * TODO
 *
 * @author zq007
 * @version V1.0
 * @date 2022/3/25 10:24
 */
public class UserMapperImpl implements UserMapper {

    @Override
    public List<User> findAll() {
        List<User> users = null;
        try {
            InputStream inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            SqlSession sqlSession = sqlSessionFactory.openSession();
            users = sqlSession.selectList("userMapper.findAll");
            sqlSession.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return users;
    }
}
