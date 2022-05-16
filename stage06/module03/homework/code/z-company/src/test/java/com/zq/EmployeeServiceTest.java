package com.zq;

/**
 * TODO
 *
 * @author zq007
 * @version V1.0
 * @date 2022/5/11 18:05
 */

import com.zq.domain.Employee;
import com.zq.service.EmployeeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class EmployeeServiceTest {

    @Autowired
    @Qualifier("employeeService")
    private EmployeeService employeeService;
    
    /**
     * 测试{@link EmployeeService#findAll()}方法的功能
     */
    @Test
    public void findAllTest(){
        List<Employee> employeeList = employeeService.findAll();
        for (Employee employee : employeeList) {
            System.out.println(employee);
        }
    }
}
