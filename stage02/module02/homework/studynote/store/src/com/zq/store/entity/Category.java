package com.zq.store.entity;


import com.zq.store.dao.ProductDao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

public class Category implements Serializable {

    private static final long serialVersionUID = 8182660072095344094L;
    private String categoryId;
    private String cname;

    public Category() {
    }

    /**
     * Category与{@link Product}是一对多关系
     * Category是一, {@link Product}是多
     * 这是Category对象对应的所有{@link Product}对象的集合
     */
    private List<Product> productList;

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public void setProductList(ProductDao productDao) throws SQLException {
        setProductList(productDao.getProductList(this));
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    @Override
    public java.lang.String toString() {
        return "Category:{" +
                "categoryId='" + categoryId + "'  " +
                "cname='" + cname + "'  " +
                "}";
    }
}
