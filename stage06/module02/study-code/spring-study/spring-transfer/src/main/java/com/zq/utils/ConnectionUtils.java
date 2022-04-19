package com.zq.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * TODO
 *
 * @author zq007
 * @version V1.0
 * @date 2022/4/9 10:11
 */
@Component
public class ConnectionUtils {
    @Autowired
    @Qualifier("dataSource")
    private DataSource dataSource;

    private ThreadLocal<Connection> threadLocal = new ThreadLocal<>();

    public Connection getConnection(){
        Connection conn = threadLocal.get();
        if (conn == null){
            try {
                conn=dataSource.getConnection();
                threadLocal.set(conn);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return conn;
    }

    public void removeConnection(){
        threadLocal.remove();
    }
}
