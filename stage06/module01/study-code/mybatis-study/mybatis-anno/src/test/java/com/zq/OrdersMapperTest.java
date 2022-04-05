package com.zq;

import com.zq.domain.Orders;
import com.zq.mapper.OrdersMapper;
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
 * @date 2022/4/4 15:57
 */
public class OrdersMapperTest {
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
     * 测试{@link OrdersMapper#findAllWithUser}方法的功能
     */
    @Test
    public void findAllWithUserTest(){
        OrdersMapper mapper = sqlSession.getMapper(OrdersMapper.class);
        List<Orders> ordersList = mapper.findAllWithUser();
        for (Orders orders : ordersList) {
            logger.info(orders);
        }
    }
}
