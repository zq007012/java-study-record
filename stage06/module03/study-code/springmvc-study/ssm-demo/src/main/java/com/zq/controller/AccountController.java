package com.zq.controller;

import com.zq.domain.Account;
import com.zq.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO
 *
 * @author zq007
 * @version V1.0
 * @date 2022/5/2 9:39
 */
@Controller("accountController")
@RequestMapping("/account")
public class AccountController {
    @Autowired
    @Qualifier("accountService")
    private AccountService accountService;

    @RequestMapping("/findAll")
    public String findAll(Model model) {
        /*ArrayList<Account> accountList = new ArrayList<>();
        Account account1 = new Account();
        account1.setId(3);
        account1.setName("不知火舞");
        account1.setMoney(11110.0D);

        accountList.add(account1);

        Account account2 = new Account();
        account2.setId(4);
        account2.setName("贝优妮塔");
        account2.setMoney(22220.0D);

        accountList.add(account2);

        Account account3 = new Account();
        account3.setId(5);
        account3.setName("穗乃果");
        account3.setMoney(33330.0D);

        accountList.add(account3);

        model.addAttribute("list", accountList);
        System.out.println(accountList);*/

        List<Account> accountList = accountService.findAll();
        model.addAttribute("list",accountList);

        return "list";
    }

    @RequestMapping("/save")
    public String save(Account account){
        accountService.save(account);
        return "redirect:/account/findAll";
    }

    @RequestMapping("/findById")
    public String findById(Integer id, Model model){
        Account account = accountService.findById(id);
        model.addAttribute("account",account);
        return "update";
    }

    @RequestMapping("/update")
    public String update(Account account){
        accountService.update(account);
        return "redirect:/account/findAll";
    }

    @RequestMapping("/delete")
    public String delete(Integer id){
        accountService.delete(id);
        return "redirect:/account/findAll";
    }

    @RequestMapping("/deleteBatch")
    public String deleteBatch(Integer[] ids){
        accountService.deleteBatch(ids);
        return "redirect:/account/findAll";
    }
}
