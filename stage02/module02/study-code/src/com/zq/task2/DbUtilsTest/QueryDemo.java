package com.zq.task2.DbUtilsTest;


import com.zq.task2.entity.Pirate;
import com.zq.dbconnpool.DruidPool;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.*;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: QueryDemo
 * @Description: TODO
 * @Author: zq007
 * @Date: 2021/3/8 14:16
 * @Version: V1.0
 */
public class QueryDemo {
    @Test
    public void arrayHandlerTest() throws Exception {
        QueryRunner queryRunner =
                new QueryRunner(DruidPool.getInstance().getDataSource());
        String sql = "select * from pirate where age > 20";
        Object[] result = queryRunner.query(DruidPool.getInstance().getDbConnection(), sql,
                new ArrayHandler());
        System.out.println(Arrays.toString(result));
    }

    @Test
    public void arrayListHandlerTest() throws Exception {
        QueryRunner queryRunner = new QueryRunner(DruidPool.getInstance().getDataSource());
        String sql = "select * from pirate where age > ?";
        List<Object[]> result = queryRunner.query(sql, new ArrayListHandler(),
                20);
        for (Object[] objects :
                result) {
            System.out.println(Arrays.toString(objects));
        }
    }

    @Test
    public void beanHandlerTest() throws Exception {
        QueryRunner queryRunner = new QueryRunner(DruidPool.getInstance().getDataSource());
        String sql = "select * from pirate where age > ?";
        Pirate result = queryRunner.query(sql, new BeanHandler<>(Pirate.class)
                , 20);

        System.out.println(result.toString());
    }

    @Test
    public void beanListHandlerTest() throws Exception {
        QueryRunner queryRunner = new QueryRunner(DruidPool.getInstance().getDataSource());
        String sql = "select * from pirate where age > ?";
        List<Pirate> result = queryRunner.query(sql,
                new BeanListHandler<>(Pirate.class)
                , 20);

        for (Pirate pirate :
                result) {
            System.out.println(pirate.toString());
        }
    }

    @Test
    public void mapHandler() throws Exception {
        QueryRunner queryRunner = new QueryRunner(DruidPool.getInstance().getDataSource());
        String sql = "select * from pirate where age > ?";
        Map<String, Object> result = queryRunner.query(sql, new MapHandler()
                , 20);

        System.out.println(result.toString());
    }

    @Test
    public void mapListHandler() throws Exception {
        QueryRunner queryRunner = new QueryRunner(DruidPool.getInstance().getDataSource());
        String sql = "select * from pirate where age > ?";
        List<Map<String, Object>> result = queryRunner.query(sql,
                new MapListHandler()
                , 20);

        for (Map<String, Object> map :
                result) {
            System.out.println(map.toString());
        }
    }

    @Test
    public void keyedHandler() throws Exception {
        QueryRunner queryRunner = new QueryRunner(DruidPool.getInstance().getDataSource());
        String sql = "select * from pirate where age > ?";
        Map<String, Map<String, Object>> result = queryRunner.query(sql,
                new KeyedHandler<>(2)
                , 20);

        System.out.println(result.toString());
    }
    
    @Test
    public void scalarHandlerTest() throws Exception {
        QueryRunner queryRunner = new QueryRunner(DruidPool.getInstance().getDataSource());
        String sql = "select count(pid) from pirate where age > ?";
        Long result = queryRunner.query(sql,
                new ScalarHandler<>(), 20);

        System.out.println("pirate表中有" + 14 + "个20岁以上的记录");
    }
}
