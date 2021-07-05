package com.zq.dbconnpool;


import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @ClassName: DRUIDUtils
 * @Description: TODO
 * @Author: zq007
 * @Date: 2021/3/8 10:13
 * @Version: V1.0
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
