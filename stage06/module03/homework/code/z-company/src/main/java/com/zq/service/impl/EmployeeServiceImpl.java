package com.zq.service.impl;

import com.zq.dao.EmployeeDao;
import com.zq.domain.Employee;
import com.zq.service.EmployeeService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * TODO
 *
 * @author zq007
 * @version V1.0
 * @date 2022/5/11 17:43
 */
@NoArgsConstructor
@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private EmployeeDao employeeDao;

    @Override
    public List<Employee> findAll() {
        return employeeDao.findAll();
    }

    @Override
    public List<Employee> searchByEmpName(String empNameKeyword) {
        return employeeDao.searchByEmpName(empNameKeyword);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void save(Employee employee) {
        employeeDao.save(employee);
    }
}
