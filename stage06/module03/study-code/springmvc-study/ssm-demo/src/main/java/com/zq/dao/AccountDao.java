package com.zq.dao;

import com.zq.domain.Account;

import java.util.List;

/**
 * TODO
 *
 * @author zq007
 * @version V1.0
 * @date 2022/5/2 8:11
 */
public interface AccountDao {
    /**
     * 查找所有的账户
     * @return
     */
    List<Account> findAll();

    /**
     * 添加一条account记录
     * @param account
     * @return
     */
    int save(Account account);

    Account findById(Integer id);

    int update(Account account);

    int delete(Integer id);

    int deleteBatch(Integer[] ids);
}
