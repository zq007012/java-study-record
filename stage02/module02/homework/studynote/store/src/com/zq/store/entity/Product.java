package com.zq.store.entity;


import com.zq.store.dao.CategoryDao;
import com.zq.store.dao.OrderitemDao;
import com.zq.store.dao.OrdersDao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Product implements Serializable {

    private static final long serialVersionUID = 2343490184789391446L;
    private String productId;
    private String pname;
    private double price;
    private String pdesc;
    private long pflag;
    private String categoryIdForeign;




    public Product() {

    }

    /**
     * Product与{@link Category}是多对一关系
     * Product是多, {@link Category}是一
     * 这是Product对象对应的{@link Category}对象
     */
    private Category category;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setCategory(CategoryDao categoryDao) throws SQLException {
        setCategory(categoryDao.getCategory(this));
    }

    /**
     * Product与{@link Orders}是多对多关系
     * Product是多, {@link Orders}是另一个多
     * {@link com.zq.store.entity.Orderitem}是中间表
     * 这是Product对象对应的所有中间表{@link com.zq.store.entity.Orderitem}对象的集合
     */
    private List<com.zq.store.entity.Orderitem> orderitemList;

    /**
     * Product与{@link Orders}是多对多关系
     * Product是多, {@link Orders}是另一个多
     * {@link com.zq.store.entity.Orderitem}是中间表
     * 这是Product对象对应的所有另一个多{@link Orders}对象的集合
     */
    private List<Orders> ordersList;

    public List<com.zq.store.entity.Orderitem> getOrderitemList() {
        return orderitemList;
    }

    public void setOrderitemList(List<com.zq.store.entity.Orderitem> orderitemList) {
        this.orderitemList = orderitemList;
    }

    public void setOrderitemList(OrderitemDao orderItemDao) throws SQLException {
        setOrderitemList(orderItemDao.getOrderitemList(this));
    }

    public List<Orders> getOrdersList() {
        return ordersList;
    }

    public void setOrdersList(List<Orders> ordersList) {
        this.ordersList = ordersList;
    }

    public void setOrdersList(OrderitemDao orderitemDao,
            OrdersDao ordersDao)
            throws Exception {
        if (null == getOrderitemList()) {
            setOrderitemList(orderitemDao);
        }

        if (null == getOrderitemList()) {
            throw new Exception("中间表中没有对应数据");
        }

        ArrayList<Orders> ordersList = new ArrayList<>();
        for (com.zq.store.entity.Orderitem orderitem :
                orderitemList) {
            ordersList.add(ordersDao.getOrders(orderitem));
        }

        setOrdersList(ordersList);
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }


    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }


    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    public String getPdesc() {
        return pdesc;
    }

    public void setPdesc(String pdesc) {
        this.pdesc = pdesc;
    }


    public long getPflag() {
        return pflag;
    }

    public void setPflag(long pflag) {
        this.pflag = pflag;
    }


    public String getCategoryIdForeign() {
        return categoryIdForeign;
    }

    public void setCategoryIdForeign(String categoryIdForeign) {
        this.categoryIdForeign = categoryIdForeign;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId='" + productId + '\'' +
                ", pname='" + pname + '\'' +
                ", price=" + price +
                ", pdesc='" + pdesc + '\'' +
                ", pflag=" + pflag +
                ", categoryIdForeign='" + categoryIdForeign + '\'' +
                '}';
    }
}
