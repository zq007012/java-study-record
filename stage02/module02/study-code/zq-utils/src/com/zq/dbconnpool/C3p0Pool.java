package com.zq.dbconnpool;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @ClassName: C3P0Utils
 * @Description: 使用单例设计模式封装一个c3po数据库连接池
 * @Author: zq007
 * @Date: 2021/3/7 20:26
 * @Version: V1.0
 */
public class C3p0Pool {
    private ComboPooledDataSource dataSource;
    private static C3p0Pool c3p0;

    private C3p0Pool(){
        dataSource = new ComboPooledDataSource("mysql");
    }

    public static C3p0Pool getInstance(){
        if (null == c3p0){
            synchronized (C3p0Pool.class){
                if (null == c3p0){
                    c3p0 = new C3p0Pool();
                }
            }
        }

        return c3p0;
    }

    /**
     * 从数据库连接池里获取一个数据库的连接
     * @return
     * @throws SQLException
     */
    public Connection getDbConnection() throws SQLException {
        return dataSource.getConnection();
    }

    /**
     * 获c3p0的取数据库连接池
     * @return
     */
    public ComboPooledDataSource getDataSource(){
        return dataSource;
    }
}
