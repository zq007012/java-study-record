package com.zq.service.impl;

import com.zq.dao.ResourceCategoryMapper;
import com.zq.domain.ResourceCategory;
import com.zq.service.ResourceCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * TODO
 *
 * @author zq007
 * @version V1.0
 * @date 2022/6/10 20:28
 */
@Service("resourceCategoryService")
public class ResourceCategoryServiceImpl implements ResourceCategoryService{
    @Autowired
    @Qualifier("resourceCategoryMapper")
    private ResourceCategoryMapper resourceCategoryMapper;

    /**
     * 查询resource_category表中的所有记录
     *
     * @return
     */
    @Override
    public List<ResourceCategory> findAllResourceCategory() {
        return resourceCategoryMapper.findAllResourceCategory();
    }
}
