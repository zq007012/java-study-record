package com.zq.domain;

import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TODO
 * @author zq007
 * @version V1.0
 * @date 2022-03-28 
 */
@Data
@NoArgsConstructor
public class SysUserRole implements Serializable {

    private static final long serialVersionUID =  103659430265720943L;

    private Integer id;

    private Integer userId;

    private Integer roleId;

}
