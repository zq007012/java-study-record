package com.zq.dao;

import com.zq.domain.Employee;

import java.util.List;

/**
 * TODO
 *
 * @author zq007
 * @version V1.0
 * @date 2022/5/11 17:36
 */
public interface EmployeeDao {
    List<Employee> findAll();

    List<Employee> searchByEmpName(String empNameKeyWord);

    int save(Employee employee);
}
