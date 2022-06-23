package com.zq.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * TODO
 *
 * @author zq007
 * @version V1.0
 * @date 2022/6/7 19:02
 */
@Data
@NoArgsConstructor
public class RoleMenuVo {
    private Integer roleId;
    private List<Integer> menuIdList;
}
