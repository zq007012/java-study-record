package com.zq.store.entity;


import com.zq.store.dao.OrderitemDao;
import com.zq.store.dao.ProductDao;
import com.zq.store.dao.UserDao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Orders implements Serializable {

    private static final long serialVersionUID = -4369655620886926347L;
    private String ordersId;
    private java.sql.Timestamp ordertime;
    private double total;
    private String name;
    private String telephone;
    private String address;
    private long state;
    private String UserIdForeign;
    private List<com.zq.store.entity.Orderitem> orderitemList;
    private List<Product> productList;
    private User user;

    @Override
    public String toString() {
        return "Orders{" +
                "ordersId='" + ordersId + '\'' +
                ", ordertime=" + ordertime +
                ", total=" + total +
                ", name='" + name + '\'' +
                ", telephone='" + telephone + '\'' +
                ", address='" + address + '\'' +
                ", state=" + state +
                ", UserIdForeign='" + UserIdForeign + '\'' +
                '}';
    }

    public Orders() {

    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setUser(UserDao userDao) throws SQLException {
        setUser(userDao.hasUser(this));
    }

    public List<com.zq.store.entity.Orderitem> getOrderitemList() {
        return orderitemList;
    }

    public void setOrderitemList(List<com.zq.store.entity.Orderitem> orderitemList) {
        this.orderitemList = orderitemList;
    }

    public void setOrderitemList(OrderitemDao orderItemDao) throws SQLException {
        setOrderitemList(orderItemDao.getOrderitemList(this));
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public void setProductList(OrderitemDao orderItemDao,
                               ProductDao productDao) throws Exception {
        if (null == getOrderitemList()) {
            setOrderitemList(orderItemDao);
        }
        if (null == getOrderitemList()) {
            throw new Exception("中间表中没有相关的数据");
        }

        ArrayList<Product> productList = new ArrayList<>();
        for (com.zq.store.entity.Orderitem orderitem :
                orderitemList) {
            productList.add(productDao.getProduct(orderitem));
        }
        setProductList(productList);
    }


    /**
     * TODO 完善Entity类
     * <p>
     * 一对多关系
     * 1. 一的实体要有多的实体对象的集合,  这个集合通过多的Dao方法获取, 这个Dao方法的参数是this
     * 2. 多的实体要有一的实体对象,  这个对象通过一的Dao方法获取, 这个Dao方法的参数是this
     * <p>
     * 多对多关系
     * 1. 多的实体要有中间表实体对象的集合, 这个集合通过中间表的Dao方法获取, 这个Dao方法的参数是this
     * 2. 多的实体要有另一个多的实体对象的集合, 这个集合通过中间表的实体对象和另一个多的Dao方法获得,
     * 这个Dao方法的参数是上述中间表实体对象的集合的每一个元素
     * 3. 中间表要有两个多的对象, 这两个对象分别通过对应的Dao的方法获取, 这个Dao方法的参数是this
     */

    public String getOrdersId() {
        return ordersId;
    }

    public void setOrdersId(String ordersId) {
        this.ordersId = ordersId;
    }


    public java.sql.Timestamp getOrdertime() {
        return ordertime;
    }

    public void setOrdertime(java.sql.Timestamp ordertime) {
        this.ordertime = ordertime;
    }


    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public long getState() {
        return state;
    }

    public void setState(long state) {
        this.state = state;
    }


    public String getUserIdForeign() {
        return UserIdForeign;
    }

    public void setUserIdForeign(String userIdForeign) {
        this.UserIdForeign = userIdForeign;
    }

}
