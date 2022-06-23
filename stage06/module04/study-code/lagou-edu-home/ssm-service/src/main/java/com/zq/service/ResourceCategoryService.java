package com.zq.service;

import com.zq.domain.ResourceCategory;

import java.util.List;

/**
 * TODO
 *
 * @author zq007
 * @version V1.0
 * @date 2022/6/10 20:27
 */
public interface ResourceCategoryService {
    /**
     * 查询resource_category表中的所有记录
     * @return
     */
    List<ResourceCategory> findAllResourceCategory();
}
