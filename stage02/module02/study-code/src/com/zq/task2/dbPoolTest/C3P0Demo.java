package com.zq.task2.dbPoolTest;


import org.junit.jupiter.api.Test;
import com.zq.utils.CloseUtils;
import com.zq.dbconnpool.C3p0Pool;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @ClassName: C3P0Demo
 * @Description: TODO
 * @Author: zq007
 * @Date: 2021/3/7 20:39
 * @Version: V1.0
 */
public class C3P0Demo {
    @Test
    public void test(){
        Connection dbConnection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            dbConnection = C3p0Pool.getInstance().getDbConnection();
            statement = dbConnection.createStatement();
            //1. 获取所有团队的名字即每个团队下所有成员的名字
            String sql = "SELECT t.`tname`,p.`pname` FROM pirate p RIGHT JOIN" +
                    " " +
                    "team t ON p.`team_id` = t.`team_id`;";
            resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                String team = resultSet.getString("tname");
                String name = resultSet.getString("pname");
                System.out.println(team + " : " + "\t" + name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                CloseUtils.closeResources(resultSet, statement, dbConnection);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }
}
