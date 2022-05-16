package com.zq.controller;

import com.zq.domain.Dept;
import com.zq.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * TODO
 *
 * @author zq007
 * @version V1.0
 * @date 2022/5/12 9:40
 */
@Controller("deptController")
@RequestMapping("/dept")
public class DeptController {
    @Autowired
    @Qualifier("deptService")
    private DeptService deptService;

    @RequestMapping("/findAll")
    @ResponseBody
    public List<Dept> findAll(){
        return deptService.findAll();
    }
}
