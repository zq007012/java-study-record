package com.zq.store.app;

import com.zq.store.dao.*;
import com.zq.store.entity.Category;
import com.zq.store.entity.Orders;
import com.zq.store.entity.Product;
import com.zq.store.entity.User;
import com.zq.utils.DateTimeUtils;
import com.zq.utils.UUIDUtils;
import org.junit.Test;

import java.util.List;

/**
 * @ClassName: UserTest
 * @Description: TODO
 * @Author: zq007
 * @Date: 2021/3/16 14:39
 * @Version: V1.0
 */
public class DaoTest {
    /**
     * 1. 编写一个注册用户的方法,接收的参数是一个User对象
     */
    @org.junit.Test
    public void test1() throws Exception {
        User user = new User();
        user.setUserId(UUIDUtils.generateUUID());
        user.setUsername("汉库克");
        user.setPassword("111111");
        user.setTelephone("18888888888");
        user.setBirthday(DateTimeUtils.getSqlDate("1990.12.12"));
        user.setGender("女");

        boolean success = UserDao.getInstance().signUpUser(user);
        if (success) {
            System.out.println("注册成功");
        } else {
            System.out.println("已有该账号, 注册失败");
        }
    }

    /**
     * 2. 编写一个 用户登录的方法,接收的参数是 用户名 和密码, 返回值是User对象
     */
    @Test
    public void test2() throws Exception {
        User user = UserDao.getInstance().hasUser("汉库克", "111111");
        if (null != user) {
            System.out.println("登录成功");
        } else {
            System.out.println("登录失败");
        }

    }

    /**
     * 3. 根据商品ID 获取商品名称 ,商品价格 以及商品所属分类的名称, 商品的id值为1
     * 4. 根据分类ID 获取商品分类信息
     */
    @Test
    public void test3() throws Exception {
        Product product =
                ProductDao.getInstance().getProductByProductId("1");
        product.setCategory(CategoryDao.getInstance());
        Category category = product.getCategory();
        System.out.println("ID为'1'的商品价格为" + product.getPrice() + "元, " +
                "所属的分类是" + category.getCname());
    }

    /**
     * 5. 查询指定分类ID 下的商品个数
     * 6. 查询指定分类ID 下的所有商品信息
     * 分类ID为1
     */
    @Test
    public void test4() throws Exception {
        Category category = CategoryDao.getInstance().getCategoryByProductId(
                "1");
        category.setProductList(ProductDao.getInstance());
        List<Product> productList = category.getProductList();
        System.out.println("ID为'1'的分类下有" + productList.size() + "种商品, 分别是: " +
                productList.toString());
    }

    /**
     * 7. 获取 uid为 001 的用户的所有订单信息
     */
    @Test
    public void test5() throws Exception {
        List<Orders> ordersList = OrdersDao.getInstance().
                getOrdersListByUserId("001");
        System.out.println("uid为 001 的用户的所有订单信息是: " + ordersList.toString());
    }

    /**
     * 8. 获取订单编号为 order001的订单中的所有商品信息
     */
    @Test
    public void test6() throws Exception {
        Orders order = OrdersDao.getInstance().getOrdersByOrdersId(
                "order001");
        order.setProductList(OrderitemDao.getInstance(),
                ProductDao.getInstance());

        List<Product> productList = order.getProductList();
        System.out.println("订单编号为 order001的订单中的所有商品信息是: " +
                productList.toString());
    }
}
