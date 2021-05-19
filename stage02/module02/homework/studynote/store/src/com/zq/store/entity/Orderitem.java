package com.zq.store.entity;


import com.zq.store.dao.OrdersDao;
import com.zq.store.dao.ProductDao;

import java.io.Serializable;
import java.sql.SQLException;

public class Orderitem implements Serializable {

    private static final long serialVersionUID = -3364353565262998262L;
    private String itemid;
    private String productIdForeign;
    private String ordersIdForeign;

    /**
     * 'Orderitem'是个中间表, 是{@link Product}和{@link Orders}的中间表
     */
    private Product product;
    private Orders orders;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setProduct(ProductDao productDao) throws SQLException {
        setProduct(productDao.getProduct(this));
    }

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    public void setOrders(OrdersDao ordersDao) throws SQLException {
        setOrders(ordersDao.getOrders(this));
    }


    public Orderitem() {

    }



    public String getItemid() {
        return itemid;
    }

    public void setItemid(String itemid) {
        this.itemid = itemid;
    }


    public String getProductIdForeign() {
        return productIdForeign;
    }

    public void setProductIdForeign(String productIdForeign) {
        this.productIdForeign = productIdForeign;
    }


    public String getOrdersIdForeign() {
        return ordersIdForeign;
    }

    public void setOrdersIdForeign(String ordersIdForeign) {
        this.ordersIdForeign = ordersIdForeign;
    }

}
