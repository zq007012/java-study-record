package com.zq.store.dao;

import com.zq.dbconnpool.DruidPool;
import com.zq.store.entity.Orders;
import com.zq.store.entity.Product;
import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.GenerousBeanProcessor;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

/**
 * @ClassName: OrderItemDao
 * @Description: TODO
 * @Author: zq007
 * @Date: 2021/3/15 21:43
 * @Version: V1.0
 */
public class OrderitemDao {
    private static OrderitemDao instance;
    private DataSource dataSource;

    private OrderitemDao() throws Exception {
        setDataSource(DruidPool.getInstance().getDataSource());
    }

    public static OrderitemDao getInstance() throws Exception {
        if (null == instance) {
            synchronized (OrderitemDao.class) {
                if (null == instance) {
                    instance = new OrderitemDao();
                }
            }
        }
        return instance;
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<com.zq.store.entity.Orderitem> getOrderitemList(Orders orders) throws SQLException {
        return getOrderitemListByOrderId(orders.getOrdersId());
    }

    public List<com.zq.store.entity.Orderitem> getOrderitemListByOrderId(String ordersId) throws SQLException {
        // 1. 编写SQL语句
        // language=MySQL
        String sql = "select * from orderitem where orders_id_foreign = ?";
        // 2. 创建QueryRunner对象
        QueryRunner queryRunner = new QueryRunner(dataSource);
        // 3. 创建一个可以下划线与驼峰命名转换的BeanHandler对象
        GenerousBeanProcessor gbp = new GenerousBeanProcessor();
        BasicRowProcessor brp = new BasicRowProcessor(gbp);
        BeanListHandler<com.zq.store.entity.Orderitem> beanListHandler =
                new BeanListHandler<>(com.zq.store.entity.Orderitem.class, brp);
        //4. 执行查找并以JavaBean集合的形式获得结果
        return queryRunner.query(sql, beanListHandler, ordersId);
    }

    public List<com.zq.store.entity.Orderitem> getOrderitemList(Product product) throws SQLException {
        return getOrderitemListByProductId(product.getProductId());
    }

    public List<com.zq.store.entity.Orderitem> getOrderitemListByProductId(String productId) throws SQLException {
        // 1. 编写SQL语句
        // language=MySQL
        String sql = "select * from orderitem where product_id_foreign = ?";
        // 2. 创建QueryRunner对象
        QueryRunner queryRunner = new QueryRunner(dataSource);
        // 3. 创建一个可以下划线与驼峰命名转换的BeanHandler对象
        GenerousBeanProcessor gbp = new GenerousBeanProcessor();
        BasicRowProcessor brp = new BasicRowProcessor(gbp);
        BeanListHandler<com.zq.store.entity.Orderitem> beanListHandler =
                new BeanListHandler<>(com.zq.store.entity.Orderitem.class, brp);
        //4. 执行查找并以JavaBean集合的形式获得结果
        return queryRunner.query(sql, beanListHandler, productId);
    }
}
