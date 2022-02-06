package com.zq.utils;


import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 这是Druid数据库连接池的工具集, 使用了单例设计模式, 只提供一个指定的数据库连接池
 * @author   zq007
 * @date    2021/3/8 10:13
 * @version V1.0
 */
public class DruidPool {
    private DruidDataSource dataSource;
    private static DruidPool druid;

    private DruidPool() throws Exception {
        Properties properties = initialsProperties();
        dataSource =
                (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);

    }

    private Properties initialsProperties() throws IOException {
        Properties properties = new Properties();
        InputStream inputStream =
                DruidPool.class.getClassLoader().getResourceAsStream(
                        "druid.properties");
        assert inputStream != null;
        properties.load(inputStream);
        inputStream.close();
        return properties;
    }

    public static DruidPool getInstance() throws Exception {
        if (null == druid) {
            synchronized (DruidPool.class) {
                if (null == druid) {
                    druid = new DruidPool();
                }
            }
        }

        return druid;
    }

    public Connection getDbConnection() throws SQLException {
        return dataSource.getConnection();
    }

    public DataSource getDataSource() {
        return dataSource;
    }
}
