package com.zq.service;

import com.zq.domain.Employee;

import java.util.List;

/**
 * TODO
 *
 * @author zq007
 * @version V1.0
 * @date 2022/5/11 17:35
 */
public interface EmployeeService {
    List<Employee> findAll();

    List<Employee> searchByEmpName(String empNameKeyword);

    void save(Employee employee);
}
