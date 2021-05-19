package com.zq.task2.DbUtilsTest;

import com.zq.dbconnpool.DruidPool;
import org.apache.commons.dbutils.QueryRunner;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.LinkedList;

/**
 * @ClassName: BatchDemo
 * @Description: TODO
 * @Author: zq007
 * @Date: 2021/3/8 21:20
 * @Version: V1.0
 */
public class BatchDemo {
    /**
     * 批量插入数据
     * @throws SQLException
     */
    @Test
    public void batchInsertTest() throws Exception {
        QueryRunner queryRunner =
                new QueryRunner(DruidPool.getInstance().getDataSource());
        //注意DbUtils中的进行批处理是的sql语句最后绝对不能加`;`
        String sql = "insert into batch_test values(?,?)";
        //1. 创建一个临时存放sql参数的集合作为临时容器
        LinkedList<Object[]> paramsTemp = new LinkedList<>();
        //2. 每一条sql参数存放分别到对应的Object[]数组中, 再把这个Object[]数组存放到临时容器中
        for (int i = 10001; i <= 20000; i++){
            paramsTemp.add(new Object[]{i,"爱丽丝"+i});
        }
        //3. 创建一个长度跟临时容器相同的二维数组作为最终容器
        Object[][] params = new Object[paramsTemp.size()][];
        //4. 将临时容器中的元素一个一个地存放到最终容器中
        paramsTemp.toArray(params);

        int[] batch = queryRunner.batch(sql,params);
    }

    /**
     * 批量删除数据
     */
    @Test
    public void batchDeleteTest() throws Exception {
        QueryRunner queryRunner =
                new QueryRunner(DruidPool.getInstance().getDataSource());
        String sql = "delete from batch_test where bname = ?";

        LinkedList<Object[]> paramsTemp = new LinkedList<>();
        for (int i = 10001; i <= 20000; i++) {
            paramsTemp.add(new Object[]{"爱丽丝" + i});
        }
        Object[][] params = new Object[paramsTemp.size()][];
        paramsTemp.toArray(params);
        int[] deleteResult = queryRunner.batch(sql, params);
    }
}
