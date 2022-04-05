package com.zq;

import com.zq.domain.Orders;
import com.zq.mapper.OrdersMapper;
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
 * @date 2022/3/28 21:21
 */
public class OrdersMapperTest{
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
     * 测试{@link OrdersMapper#findAll()}方法的功能
     */
    @Test
    public void findAllTest(){
        OrdersMapper mapper = sqlSession.getMapper(OrdersMapper.class);
        List<Orders> ordersList = mapper.findAll();
        for (Orders orders : ordersList) {
            System.out.println(orders);
        }
    }

    /**
     * 测试{@link OrdersMapper#findAll2()}方法的功能
     */
    @Test
    public void findAll2Test(){
        OrdersMapper mapper = sqlSession.getMapper(OrdersMapper.class);
        List<Orders> ordersList = mapper.findAll2();
        for (Orders orders : ordersList) {
            System.out.println(orders);
        }
    }

}
