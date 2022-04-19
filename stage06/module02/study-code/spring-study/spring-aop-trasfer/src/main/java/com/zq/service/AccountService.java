package com.zq.service;

/**
 * TODO
 *
 * @author zq007
 * @version V1.0
 * @date 2022/4/9 9:31
 */
public interface AccountService {
    void transfer(String inUser, String outUser, Double amount);
}
