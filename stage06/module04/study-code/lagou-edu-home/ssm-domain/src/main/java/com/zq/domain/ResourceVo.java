package com.zq.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TODO
 *
 * @author zq007
 * @version V1.0
 * @date 2022/6/10 14:41
 */
@Data
@NoArgsConstructor
public class ResourceVo {
    private Integer categoryId;
    private Integer currentPage;
    private String name;
    private Integer pageSize;
    private String url;
}
