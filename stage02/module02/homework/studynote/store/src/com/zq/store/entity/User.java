package com.zq.store.entity;


import com.zq.store.dao.OrdersDao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

public class User implements Serializable {

    private static final long serialVersionUID = -2729492926141731273L;
    private String userId;
    private String username;
    private String password;
    private String telephone;
    private java.sql.Date birthday;
    private String gender;
    private List<Orders> ordersList;

    public List<Orders> getOrdersList() {
        return ordersList;
    }

    public void setOrdersList(List<Orders> ordersList) {
        this.ordersList = ordersList;
    }

    public void setOrderList(OrdersDao ordersDao) throws SQLException {
        setOrdersList(ordersDao.getOrdersList(this));
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }


    public java.sql.Date getBirthday() {
        return birthday;
    }

    public void setBirthday(java.sql.Date birthday) {
        this.birthday = birthday;
    }


    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

}
