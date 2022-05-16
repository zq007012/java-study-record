package com.zq.service;

import com.zq.domain.Account;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * TODO
 *
 * @author zq007
 * @version V1.0
 * @date 2022/5/2 8:41
 */
public interface AccountService {
    List<Account> findAll();

    void save(Account account);

    Account findById(Integer id);

    void update(Account account);

    void delete(Integer id);

    void deleteBatch(Integer[] ids);
}
