package com.zq.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TODO
 *
 * @author zq007
 * @version V1.0
 * @date 2022/6/1 20:22
 */
@Data
@NoArgsConstructor
public class PromotionAdVo {
    /**
     * 获取第几页的广告信息
     */
    private Integer currentPage;

    /**
     * 每页有多少条广告信息
     */
    private Integer pageSize;
}
