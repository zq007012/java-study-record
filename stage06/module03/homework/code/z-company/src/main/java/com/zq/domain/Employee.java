package com.zq.domain;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.NoArgsConstructor;

/**
 * TODO
 * @author zq007
 * @version V1.0
 * @date 2022-05-11 
 */
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Employee implements Serializable {

    private static final long serialVersionUID =  466459209341326133L;

    /**
     * 员工号
     */
    @Setter
    @Getter
    private Integer empId;

    /**
     * 姓名
     */
    @Setter
    @Getter
    private String empName;

    /**
     * 部门号
     */
    @Setter
    @Getter
    private Integer deptId;

    /**
     * 职位, 包括包括产品经理、销售经理、 客户经理等
     */
    @Setter
    @Getter
    private String jobName;

    /**
     * 入职时间
     */
    @Setter
    @Getter
    private LocalDate joinDate;

    /**
     * 联系方式
     */
    @Setter
    @Getter
    private String telephone;

    /**
     * 部门名字
     */
    @Setter
    @Getter
    private String deptName;

}
