package com.zq.store.dao;

import com.zq.dbconnpool.DruidPool;
import com.zq.store.entity.Category;
import com.zq.store.entity.Orderitem;
import com.zq.store.entity.Product;
import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.GenerousBeanProcessor;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

/**
 * @ClassName: ProductDaoAuto
 * @Description: TODO
 * @Author: zq007
 * @Date: 2021/3/17 10:51
 * @Version: V1.0
 */
public class ProductDaoAuto {
    private static ProductDaoAuto instance;
    private DataSource dataSource;

    private ProductDaoAuto() throws Exception {
        setDataSource(DruidPool.getInstance().getDataSource());
    }

    public static ProductDaoAuto getInstance() throws Exception {
        if (null == instance) {
            synchronized (ProductDaoAuto.class) {
                if (null == instance) {
                    instance = new ProductDaoAuto();
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
     * ∞对1的关系中, 在 '∞的一方的DAO里' 通过 '1的对象' 获取 '∞的集合', 
     * 底层是通过 '1的主键ID' 在 '作为∞的表中' 获取 '∞的集合'
     * 作为∞的一方的类型{@link Product} ,
     * 作为1的一方的类型{@link Category}, 
     * 1的对象的名称 "category" , 
     * @see Category#getCategoryId()  1的对象获取1的主键ID的方法
     */
    public List<Product> getProductListByCategory(Category category) throws SQLException {
        return getProductListByCategoryId(category.getCategoryId() );
    }
    
    /**
     * ∞对1关系中, 在 '∞的DAO里' 通过 '1的主键ID' 在 '作为∞的表中' 获取 '∞的集合'
     * 作为∞的一方的类型{@link Product} ,
     * 作为1的一方的类型{@link Category}, 
     */
    public List<Product> getProductListByCategoryId(String categoryId) throws SQLException {
        // 1. 编写预处理SQL语句: 在 '∞的表中' 查询 '1的主键ID' 的值为 '?' 的所有记录
        // language=MySQL
        String sql = "select * from product where category_id_foreign = ?";
    
        // 2. 创建QueryRunner对象
        QueryRunner queryRunner = new QueryRunner(dataSource);
    
        // 3. 创建一个可以对下划线和驼峰命名进行转换的BeanHandler对象
        GenerousBeanProcessor gbp = new GenerousBeanProcessor();
        BasicRowProcessor brp = new BasicRowProcessor(gbp);
        BeanListHandler<Product> beanListHandler =
                new BeanListHandler<>(Product.class, brp);
    
        //4. 执行查找, 获取到多的集合并返回
        return queryRunner.query(sql, beanListHandler, categoryId);
    }

    /**
     * TODO 完善Dao类第一步
     *
     * 从SqlYog中获取所有表间的关系图(通过打开在SqlYog 文件菜单下的新架构设计器, 
     * 把所有表拖到架构设计其中获取)
     *
     * 图片中的1即为一, 图片中的∞即为多
     *
     * 1. Dao最好使用单例设计模式, 并且要封装一个数据库连接池DataSource对象, 生成这个对象的getter和setter
     * 2. 一的Dao要创建一个可以获取一的实体对象的方法, 参数是多的实体对象, 本质上是多的外键值
     * 3. 多的Dao要创建一个可以获取多的实体对象集合的方法, 参数是一的实体对象, 本质上是一的主键值
     */
}
