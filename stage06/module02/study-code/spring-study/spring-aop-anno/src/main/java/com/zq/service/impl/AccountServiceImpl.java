package com.zq.service.impl;

import com.zq.advice.TransferAdvice;
import com.zq.service.AccountService;
import org.springframework.stereotype.Service;

/**
 * TODO
 *
 * @author zq007
 * @version V1.0
 * @date 2022/4/9 9:32
 */
@Service("accountService")
public class AccountServiceImpl implements AccountService {

    @Override
    public void transfer() {
        System.out.println("进行了转账操作---------------------");
    }
}
