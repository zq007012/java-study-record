package com.zq;

import com.zq.domain.SysRole;
import com.zq.mapper.SysRoleMapper;
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
 * @date 2022/3/29 10:44
 */
public class SysRoleMapperTest {
    private SqlSession sqlSession;

    {
        try {
            InputStream inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            sqlSession = sqlSessionFactory.openSession();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试{@link SysRoleMapper#findAll}方法的功能
     */
    @Test
    public void findAllTest() {
        SysRoleMapper mapper = sqlSession.getMapper(SysRoleMapper.class);
        List<SysRole> sysRoles = mapper.findAll();
        for (SysRole sysRole : sysRoles) {
            System.out.println(sysRole);
        }
    }

    /**
     * 测试{@link SysRoleMapper#findAll2}方法的功能
     */
    @Test
    public void findAll2Test() {
        SysRoleMapper mapper = sqlSession.getMapper(SysRoleMapper.class);
        List<SysRole> sysRoles = mapper.findAll2();
        for (SysRole sysRole : sysRoles) {
            System.out.println(sysRole);
        }
    }
}
