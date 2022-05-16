package com.zq;

import com.zq.dao.DeptDao;
import com.zq.domain.Dept;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
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
 * @date 2022/5/11 16:39
 */
public class DeptDaoTest {
    private SqlSession sqlSession;
    @Before
    public void init(){
        try {
            InputStream is = Resources.getResourceAsStream("sqlMapConfig.xml");
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
            sqlSession = sqlSessionFactory.openSession();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @After
    public void end(){
        if (sqlSession != null){
            sqlSession.close();
        }
    }
    /**
     * 测试{@link DeptDao#findAll}方法的功能
     */
    @Test
    public void findAllTest(){
        DeptDao deptDao = sqlSession.getMapper(DeptDao.class);
        List<Dept> deptList = deptDao.findAll();
        for (Dept dept : deptList) {
            System.out.println(dept);
        }
    }
}
