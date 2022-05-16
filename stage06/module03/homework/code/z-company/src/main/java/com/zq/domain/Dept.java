package com.zq.domain;

import java.io.Serializable;
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
public class Dept implements Serializable {

    private static final long serialVersionUID =  7932233935214252112L;

    /**
     * 部门号
     */
    @Setter
    @Getter
    private Integer deptId;

    /**
     * 部门名
     */
    @Setter
    @Getter
    private String deptName;

    /**
     * 主管
     */
    @Setter
    @Getter
    private String majorName;

    /**
     * 联系电话
     */
    @Setter
    @Getter
    private String telephone;

    /**
     * 邮箱
     */
    @Setter
    @Getter
    private String email;

}
