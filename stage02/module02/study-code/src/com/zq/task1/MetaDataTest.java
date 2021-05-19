package com.zq.task1;

import com.zq.utils.CloseUtils;
import org.junit.jupiter.api.Test;

import java.sql.*;

/**
 * @ClassName: MetaDataTest
 * @Description: TODO
 * @Author: zq007
 * @Date: 2021/3/8 20:46
 * @Version: V1.0
 */
public class MetaDataTest {
    @Test
    public void metaDataTest() throws Exception {
        String url = "jdbc:mysql://localhost:3306/one_piece?characterEncoding" +
                "=utf-8&rewriteBatchedStatements=true";
        String user = "zq";
        String password = "111111";
        Connection dbConnection = DriverManager.getConnection(url, user,
                password);

        DatabaseMetaData databaseMetaData = dbConnection.getMetaData();
        System.out.println(databaseMetaData.getURL());
        System.out.println(databaseMetaData.getUserName());
        System.out.println(databaseMetaData.getDatabaseProductName());
        System.out.println(databaseMetaData.getDatabaseProductVersion());
        System.out.println(databaseMetaData.getDriverName());
        System.out.println(databaseMetaData.isReadOnly());

        System.out.println("----------------------------------");

        Statement statement = dbConnection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from pirate;");
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
        System.out.println(resultSetMetaData.getColumnCount());
        System.out.println(resultSetMetaData.getColumnName(2));
        System.out.println(resultSetMetaData.getColumnTypeName(2));

        CloseUtils.closeResources(resultSet,statement,dbConnection);
    }
}
