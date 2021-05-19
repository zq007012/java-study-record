package com.zq.store.dao;

import com.zq.dbconnpool.DruidPool;
import com.zq.store.entity.Category;
import com.zq.store.entity.Product;
import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.BeanProcessor;
import org.apache.commons.dbutils.GenerousBeanProcessor;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @ClassName: CategoryDao
 * @Description: TODO
 * @Author: zq007
 * @Date: 2021/3/15 22:06
 * @Version: V1.0
 */
public class CategoryDao {
    private static CategoryDao instance;
    private DataSource dataSource;

    private CategoryDao() throws Exception {
        setDataSource(DruidPool.getInstance().getDataSource());
    }

    public static CategoryDao getInstance() throws Exception {
        if (null == instance) {
            synchronized (CategoryDao.class) {
                if (null == instance) {
                    instance = new CategoryDao();
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
     * 图片中的1即为一, 图片中的∞即为多
     * <p>
     * 1. Dao最好使用单例设计模式, 并且要封装一个数据库连接池DataSource对象, 生成这个对象的getter和setter
     * 2. 一的Dao要创建一个可以获取一的实体对象的方法, 参数是多的实体对象, 本质上是多的外键值
     * 3. 多的Dao要创建一个可以获取多的实体对象集合的方法, 参数是一的实体对象, 本质上是一的主键值
     */

    public Category getCategory(Product product) throws SQLException {
        return getCategoryByProductId(product.getCategoryIdForeign());
    }

    public Category getCategoryByProductId(String categoryId) throws SQLException {
        // 1. 编写SQL语句
        // language=MySQL
        String sql = "select * from category where category_id = ?";
        // 2. 创建QueryRunner对象
        QueryRunner queryRunner = new QueryRunner(dataSource);
        // 3. 创建一个可以下划线与驼峰命名转换的BeanHandler对象
        GenerousBeanProcessor gbp = new GenerousBeanProcessor();
        BasicRowProcessor brp = new BasicRowProcessor(gbp);
        BeanHandler<Category> beanHandler = new BeanHandler<>(Category.class,
                brp);
        //4. 执行查找并获得查找到的对象
        return queryRunner.query(sql, beanHandler, categoryId);


    }
}
