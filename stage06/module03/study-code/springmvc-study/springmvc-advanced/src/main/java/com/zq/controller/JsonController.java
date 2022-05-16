package com.zq.controller;

import com.zq.domain.Account;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * TODO
 *
 * @author zq007
 * @version V1.0
 * @date 2022/4/30 9:45
 */
@Controller("jsonController")
public class JsonController {

    @RequestMapping("/jsonObjRequest")
    @ResponseBody
    public String jsonObjRequest(@RequestBody Account account){
        System.out.println(account);
        return "{\"name\":\"mercy\",\"money\":\"1000\"}";
    }

    @RequestMapping("/jsonArrayRequest")
    @ResponseBody
    public Account[] jsonArrayRequest(@RequestBody Account[] accountList){
        for (Account account : accountList) {
            System.out.println(account);
        }
        return accountList;
    }


}
