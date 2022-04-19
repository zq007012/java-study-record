package com.zq.dao;

/**
 * TODO
 *
 * @author zq007
 * @version V1.0
 * @date 2022/4/9 9:16
 */
public interface AccountDao {
    void transferIn(String inUser, Double amount);
    void transferOut(String outUser, Double amount);
}
