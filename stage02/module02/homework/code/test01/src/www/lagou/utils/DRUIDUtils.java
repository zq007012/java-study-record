package www.lagou.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Properties;

/**
 * @ClassName: DRUIDUtils
 * @Description: TODO
 * @Author: zq007
 * @Date: 2021/3/12 8:17
 * @Version: V1.0
 */
public class DRUIDUtils {
    private static DataSource dataSource;

    static {
        Properties properties = new Properties();
        InputStream in =
                DRUIDUtils.class.getClassLoader().getResourceAsStream(
                        "druid.properties");
        try {
            properties.load(in);
            dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            System.out.println("DRUID连接池初始化失败");
            e.printStackTrace();
        }
    }

    /**
     * 提供一个数据库连接池
     * @return
     */
    public static DataSource getDataSource(){
        return dataSource;
    }

    /**
     * 提供一个数据库连接池里的连接
     * @return
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
