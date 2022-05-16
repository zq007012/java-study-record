package com.zq.controller;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zq.domain.Employee;
import com.zq.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

/**
 * TODO
 *
 * @author zq007
 * @version V1.0
 * @date 2022/5/11 18:46
 */
@Controller("employeeController")
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    @Qualifier("employeeService")
    private EmployeeService employeeService;

    @RequestMapping("/findAll")
    public String findAll(Model model){
        List<Employee> employeeList = employeeService.findAll();
        model.addAttribute("employeeList", employeeList);
        return "list";
    }

    @RequestMapping("/searchByEmpName")
    public String search(String empNameKeyword,Model model){
        List<Employee> employeeList = employeeService.searchByEmpName(empNameKeyword);
        model.addAttribute("employeeList",employeeList);
        return "list";
    }

    @RequestMapping("/save")
    public String save(Employee employee){
        employeeService.save(employee);
        return "redirect:/employee/findAll";
    }
}
