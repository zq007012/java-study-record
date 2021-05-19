package com.zq.store.dao;

import com.zq.dbconnpool.DruidPool;
import com.zq.store.entity.Orderitem;
import com.zq.store.entity.Orders;
import com.zq.store.entity.User;
import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.BeanProcessor;
import org.apache.commons.dbutils.GenerousBeanProcessor;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

/**
 * @ClassName: OrdersDao
 * @Description: TODO
 * @Author: zq007
 * @Date: 2021/3/15 21:28
 * @Version: V1.0
 */
public class OrdersDao {
    private static OrdersDao instance;
    private DataSource dataSource;

    private OrdersDao() throws Exception {
        setDataSource(DruidPool.getInstance().getDataSource());
    }

    public static OrdersDao getInstance() throws Exception {
        if (null == instance) {
            synchronized (OrdersDao.class) {
                if (null == instance) {
                    instance = new OrdersDao();
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

    /**
     * TODO
     * 初步创建Dao类
     * <p>
     * 从SqlYog中获取所有表间的关系图(通过打开在SqlYog 文件菜单下的新架构设计器,
     * 把所有表拖到架构设计其中获取)
     * <p>
     * 图片中的1即为1, 图片中的∞即为多
     * <p>
     * 1. Dao最好使用单例设计模式, 并且要封装一个数据库连接池DataSource对象, 生成这个对象的getter和setter
     * 2. 一的Dao要创建一个可以获取一的实体对象的方法, 参数是多的实体对象, 本质上是多的外键值
     * 3. 多的Dao要创建一个可以获取多的实体对象集合的方法, 参数是一的实体对象, 本质上是一的主键值
     */
    public List<Orders> getOrdersList(User user) throws SQLException {
        return getOrdersListByUserId(user.getUserId());
    }

    public List<Orders> getOrdersListByUserId(String userId) throws SQLException {
        // 1. 编写SQL语句
        // language=MySQL
        String sql = "select * from orders where user_id_foreign = ?";
        // 2. 创建QueryRunner对象
        QueryRunner queryRunner = new QueryRunner(dataSource);
        // 3. 创建一个可以下划线与驼峰命名转换的BeanHandler对象
        GenerousBeanProcessor gbp = new GenerousBeanProcessor();
        BasicRowProcessor brp = new BasicRowProcessor(gbp);
        BeanListHandler<Orders> beanListHandler =
                new BeanListHandler<>(Orders.class, brp);
        //4. 执行查找并以JavaBean集合的形式获得结果
        return queryRunner.query(sql, beanListHandler, userId);
    }

    public Orders getOrders(Orderitem orderitem) throws SQLException {
        return getOrdersByOrdersId(orderitem.getOrdersIdForeign());
    }

    public Orders getOrdersByOrdersId(String ordersId) throws SQLException {
        // 1. 编写SQL语句
        // language=MySQL
        String sql = "select * from orders where orders_id = ?";
        // 2. 创建QueryRunner对象
        QueryRunner queryRunner = new QueryRunner(dataSource);
        // 3. 创建一个可以下划线与驼峰命名转换的BeanHandler对象
        GenerousBeanProcessor gbp = new GenerousBeanProcessor();
        BasicRowProcessor brp = new BasicRowProcessor(gbp);
        BeanHandler<Orders> beanHandler = new BeanHandler<>(Orders.class, brp);
        //4. 执行查找并以JavaBean的形式获得结果
        return queryRunner.query(sql, beanHandler, ordersId);
    }
}
