package com.zq.task1;

import com.zq.utils.CloseUtils;
import org.junit.jupiter.api.Test;

import java.sql.*;

/**
 * @ClassName: JDBCDemo1
 * @Description: TODO
 * @Author: zq007
 * @Date: 2021/3/6 17:07
 * @Version: V1.0
 */
public class JDBCDemo1 {
    @Test
    public void jdbcBasicTest(){
        // 1. 注册驱动, 可以省略, 因为第二步会帮忙注册驱动的
        Connection onePiece = null;
        Statement onePieceStatement = null;
        ResultSet queryRS = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");

            // 2. 跟数据库服务器上的数据库建立网络连接
            String url =
                        "jdbc:mysql://127.0.0.1:3306/one_piece?characterEncoding" +
                    "=utf-8";
            onePiece = DriverManager.getConnection(
                    url, "zq", "111111");

            // 3. 获取SQL语句执行平台, 以便客户端向数据库发送sql语句
            onePieceStatement = onePiece.createStatement();

            // 4. 执行sql语句
            // 添加一条记录
            String addSQL = "INSERT INTO pirate VALUES(NULL, '薇薇' , 18 , '女', '2002.6.1', 0 , 1);";
            //执行添加后就能得到反馈信息
            int recordsUpdate = onePieceStatement.executeUpdate(addSQL);
            System.out.println("recordsUpdate = " + recordsUpdate);

            // 删除一条记录
            String deleteSQL = "DELETE FROM pirate WHERE pname = '薇薇';";
            //执行删除后就能得到反馈信息
            int recordsDel = onePieceStatement.executeUpdate(deleteSQL);
            System.out.println("recordsDel = " + recordsDel);

            //查询所有记录
            String querySQL = "SELECT t.`tname`, p.`pname`, p.`age`\n" +
                    "FROM pirate p RIGHT JOIN team t ON p.`team_id` = t.`team_id`;";
            //执行查询后就能获得反馈信息
            queryRS = onePieceStatement.executeQuery(querySQL);
            while (queryRS.next()){
                String tname = queryRS.getString("tname");
                String pname = queryRS.getString("pname");
                int age = queryRS.getInt("age");

                System.out.println("团队: " + tname + "\t姓名: " + pname +
                        "\t年龄: " + age);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            /*// 5. 关闭资源
            //遵循后开先关原则
            queryRS.close();
            onePieceStatement.close();
            onePiece.close();*/
            try {
                CloseUtils.closeResources(queryRS,onePieceStatement,onePiece);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }
}
