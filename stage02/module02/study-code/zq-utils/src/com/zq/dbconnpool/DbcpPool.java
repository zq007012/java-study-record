package com.zq.dbconnpool;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.zq.utils.CloseUtils;
import org.apache.commons.dbcp2.BasicDataSource;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @ClassName: DPCPUtils
 * @Description: TODO
 * @Author: zq007
 * @Date: 2021/3/6 20:03
 * @Version: V1.0
 */
public class DbcpPool {
    private BasicDataSource dataSource;
    private  static DbcpPool dbcpPool;
    private DbcpPool(){
        dataSource = new BasicDataSource();
        initialsDatasource();
    }

    private void initialsDatasource() {
        try {
            SAXReader saxReader = new SAXReader();
            InputStream inputstream =
                    DbcpPool.class.getClassLoader().getResourceAsStream(
                            "dbcp-config.xml");
            Document document = saxReader.read(inputstream);
            String defaultConfigXpath = "/dbcp-config/default-config";
            String initialPoolSizeXpath = defaultConfigXpath +
                    "/property[@name='InitialPoolSize']";
            String minIdelXpath = defaultConfigXpath +
                    "/property[@name='MinIdlePoolSize']";
            String maxIdleXpath = defaultConfigXpath +
                    "/property[@name='MaxIdlePoolSize']";
            String maxTotalXpath = defaultConfigXpath +
                    "/property[@name='MaxTotalPoolSize']";
            String minEvictableIdleTimeMillisXpath = defaultConfigXpath +
                    "/property[@name='MinEvictableIdleTimeMillis']";
            dataSource.setInitialSize(Integer.parseInt(
                    document.selectSingleNode(initialPoolSizeXpath).getText()));

            dataSource.setMinIdle(Integer.parseInt(
                    document.selectSingleNode(minIdelXpath).getText()));

            dataSource.setMaxIdle(Integer.parseInt(document.selectSingleNode(
                    maxIdleXpath).getText()));

            dataSource.setMaxTotal(Integer.parseInt(document.selectSingleNode(
                    maxTotalXpath).getText()));

            dataSource.setMinEvictableIdleTimeMillis(Integer.parseInt(
                    document.selectSingleNode(minEvictableIdleTimeMillisXpath).
                            getText()));

            String namedConfig = "/dbcp-config/named-config[@name='mysql']";
            dataSource.setDriverClassName(document.selectSingleNode(
                    namedConfig + "/property[@name='DriverClassName']").getText());
            dataSource.setUrl(document.selectSingleNode(
                    namedConfig + "/property[@name='Url']").getText());
            dataSource.setUsername(document.selectSingleNode(
                    namedConfig + "/property[@name='Username']").getText());
            dataSource.setPassword(document.selectSingleNode(
                    namedConfig + "/property[@name='Password']").getText());

            if (null != inputstream){
                inputstream.close();
            }
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
    }

    /*static{
        dataSource.setDriverClassName(DRIVER_NAME);
        dataSource.setUrl(URL);
        dataSource.setUsername(USER);
        dataSource.setPassword(PASSWORD);
        dataSource.setMaxTotal(30);
        dataSource.setMaxIdle(15);
        dataSource.setMinIdle(5);
        dataSource.setInitialSize(5);
    }*/

    public static DbcpPool getInstance(){
        return dbcpPool;
    }
    public Connection getDbConnection() throws SQLException {
        return dataSource.getConnection();
    }

    public BasicDataSource getDataSource() {
        return dataSource;
    }
}
