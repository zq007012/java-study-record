package com.lagou.testmetadata;

import com.lagou.utils.DruidUtils;
import org.junit.Test;

import java.sql.*;

public class TestMetaData {

    //1.获取数据库相关的元数据信息 使用DatabaseMetaData
    @Test
    public void testDataBaseMetaData() throws SQLException {

        //1.获取数据库连接对象 connection
        Connection connection = DruidUtils.getConnection();

        //2.获取代表数据库的 元数据对象 DatabaseMetaData
        DatabaseMetaData metaData = connection.getMetaData();

        //3.获取数据库相关的元数据信息
        String url = metaData.getURL();
        System.out.println("数据库URL: " + url);

        String userName = metaData.getUserName();
        System.out.println("当前用户: " + userName );

        String productName = metaData.getDatabaseProductName();
        System.out.println("数据库产品名: " + productName);

        String version = metaData.getDatabaseProductVersion();
        System.out.println("数据库版本: " + version);

        String driverName = metaData.getDriverName();
        System.out.println("驱动名称: " + driverName);

        //判断当前数据库是否只允许只读
        boolean b = metaData.isReadOnly();  //如果是 true 就表示 只读
        if(b){
            System.out.println("当前数据库只允许读操作!");
        }else{
            System.out.println("不是只读数据库");
        }

        connection.close();
    }

   //获取结果集中的元数据信息
    @Test
    public void testResultSetMetaData() throws SQLException {

        //1.获取连接
        Connection con = DruidUtils.getConnection();

        //2.获取预处理对象
        PreparedStatement ps = con.prepareStatement("select * from employee");
        ResultSet resultSet = ps.executeQuery();

        //3.获取结果集元素据对象
        ResultSetMetaData metaData = ps.getMetaData();

        //1.获取当前结果集 共有多少列
        int count = metaData.getColumnCount();
        System.out.println("当前结果集中共有: " + count + " 列");

        //2.获结果集中 列的名称 和 类型
        for (int i = 1; i <=  count; i++) {
            String columnName = metaData.getColumnName(i);
            System.out.println("列名: "+ columnName);

            String columnTypeName = metaData.getColumnTypeName(i);
            System.out.println("类型: " +columnTypeName);
        }

        //释放资源
        DruidUtils.close(con,ps,resultSet);
    }

}
