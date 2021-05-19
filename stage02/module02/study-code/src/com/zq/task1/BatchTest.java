package com.zq.task1;

import org.junit.jupiter.api.Test;
import com.zq.utils.CloseUtils;

import java.sql.*;

/**
 * @ClassName: BatchTest
 * @Description: TODO
 * @Author: zq007
 * @Date: 2021/3/8 17:07
 * @Version: V1.0
 */
public class BatchTest {
    @Test
    public void batchTest() throws Exception {
        String url = "jdbc:mysql://localhost:3306/one_piece?" +
                "characterEncoding=utf-8&rewriteBachedStatement=true";
        String user = "zq";
        String password = "111111";
        Connection dbConnection = DriverManager.getConnection(url,user,
                password);
        String sql = "INSERT INTO batch_test VALUES(?, ?);";
        PreparedStatement preparedStatement =
                dbConnection.prepareStatement(sql);
        for (int i = 1; i <= 10000; i++){
            preparedStatement.setInt(1,i);
            preparedStatement.setString(2,"蒂法" + i);
            preparedStatement.addBatch();
        }

        preparedStatement.executeBatch();
        CloseUtils.closeResources(preparedStatement,dbConnection);
    }

    @Test
    public void deleteBatchTest() throws Exception {
        String url = "jdbc:mysql://localhost:3306/one_piece?" +
                "characterEncoding=utf-8&rewriteBachedStatement=true";
        String user = "zq";
        String password = "111111";
        Connection dbConnection = DriverManager.getConnection(url,user,
                password);
        String sql = "delete from batch_test where bname = ?";
        PreparedStatement preparedStatement =
                dbConnection.prepareStatement(sql);
        for (int i = 1; i <= 10000; i++){
            preparedStatement.setString(1,"蒂法" + i);
            preparedStatement.addBatch();
        }

        preparedStatement.executeBatch();
        CloseUtils.closeResources(preparedStatement,dbConnection);
    }
}
