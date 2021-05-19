package com.zq.store.dao;

import com.zq.dbconnpool.DruidPool;
import com.zq.store.entity.Category;
import com.zq.store.entity.Orderitem;
import com.zq.store.entity.Product;
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
 * @ClassName: ProductDao
 * @Description: TODO
 * @Author: zq007
 * @Date: 2021/3/15 21:55
 * @Version: V1.0
 */
public class ProductDao {
    private static ProductDao instance;
    private DataSource dataSource;

    private ProductDao() throws Exception {
        setDataSource(DruidPool.getInstance().getDataSource());
    }

    public static ProductDao getInstance() throws Exception {
        if (null == instance) {
            synchronized (ProductDao.class) {
                if (null == instance) {
                    instance = new ProductDao();
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
     * 多对一关系中, 通过一的对象获取多的集合,
     * 底层是通过  一的ID  在作为多的表中 获取多的集合
     * @param category
     * @return
     * @throws SQLException
     */
    public List<Product> getProductList(Category category) throws SQLException {
        return getProductByCategoryId(category.getCategoryId());
    }

    /**
     * 多对一关系中, 通过  一的ID  在作为多的表中 获取多的集合
     * @param categoryId
     * @return
     * @throws SQLException
     */
    public List<Product> getProductByCategoryId(String categoryId) throws SQLException {
        // 1. 编写SQL语句
        // language=MySQL
        String sql = "select * from product where category_id_foreign = ?";
        // 2. 创建QueryRunner对象
        QueryRunner queryRunner = new QueryRunner(dataSource);
        // 3. 创建一个可以下划线与驼峰命名转换的BeanHandler对象
        GenerousBeanProcessor gbp = new GenerousBeanProcessor();
        BasicRowProcessor brp = new BasicRowProcessor(gbp);
        BeanListHandler<Product> beanListHandler =
                new BeanListHandler<>(Product.class, brp);
        //4. 执行查找并以JavaBean集合的形式获得结果
        return queryRunner.query(sql, beanListHandler, categoryId);
    }

    public Product getProduct(Orderitem orderitem) throws SQLException {
        return getProductByProductId(orderitem.getProductIdForeign());
    }

    public Product getProductByProductId(String productId) throws SQLException {
        // 1. 编写SQL语句
        // language=MySQL
        String sql = "select * from product where product_id = ?";
        // 2. 创建QueryRunner对象
        QueryRunner queryRunner = new QueryRunner(dataSource);
        // 3. 创建一个可以下划线与驼峰命名转换的BeanHandler对象
        GenerousBeanProcessor gbp = new GenerousBeanProcessor();
        BasicRowProcessor brp = new BasicRowProcessor(gbp);
        BeanHandler<Product> beanHandler = new BeanHandler<>(Product.class,
                brp);
        //4. 执行查找并以JavaBean的形式获得结果
        return queryRunner.query(sql, beanHandler, productId);
    }
}
