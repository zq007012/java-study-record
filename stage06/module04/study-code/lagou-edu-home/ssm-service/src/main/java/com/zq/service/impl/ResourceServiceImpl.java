package com.zq.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zq.dao.ResourceMapper;
import com.zq.domain.Resource;
import com.zq.domain.ResourceVo;
import com.zq.service.ResourceService;
import com.zq.utils.EmptyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * TODO
 *
 * @author zq007
 * @version V1.0
 * @date 2022/6/10 15:14
 */
@Service("resourceService")
public class ResourceServiceImpl implements ResourceService {
    @Autowired
    @Qualifier("resourceMapper")
    private ResourceMapper resourceMapper;


    /**
     * 删除资源信息
     * 删除role_resource_relaton表中国where resource_id = #{resourceId}的记录
     * 删除resource表中where id = #{resourceI}的记录
     *
     * @param resourceId resourceId是资源表resource的主键id
     */
    @Override
    @Transactional(readOnly = false, isolation = Isolation.REPEATABLE_READ)
    public void deleteResourceByResourceId(Integer resourceId) {
        resourceMapper.deleteRoleResourceRelationByResourceId(resourceId);
        resourceMapper.deleteResourceById(resourceId);
    }

    /**
     * 生成createdTime, updatedTime, createdBy, updatedBy, 补完形参resource,
     * 将形参resource中的信息保存到resource表中
     *
     * @param resource 封装了name,  url,  categoryId,  description
     */
    @Override
    @Transactional(readOnly = false, isolation = Isolation.REPEATABLE_READ)
    public void saveResource(Resource resource) {
        LocalDateTime now = LocalDateTime.now();
        resource.setCreatedTime(now);
        resource.setUpdatedTime(now);
        if (EmptyUtils.isEmpty(resource.getCreatedBy())) {
            resource.setCreatedBy("system");
        }

        if (EmptyUtils.isEmpty(resource.getUpdatedBy())) {
            resource.setUpdatedBy("system");
        }

        resourceMapper.saveResource(resource);
    }

    /**
     * 生成updatedTime, updatedBy, 补完形参resource, 以where id = #{id}为条件将形参resource中的信息更新到resource表中
     *
     * @param resource 封装了id, name,  url,  categoryId,  description
     */
    @Override
    @Transactional(readOnly = false, isolation = Isolation.REPEATABLE_READ)
    public void updateResource(Resource resource) {
        LocalDateTime now = LocalDateTime.now();
        resource.setUpdatedTime(now);
        if (EmptyUtils.isEmpty(resource.getUpdatedBy())) {
            resource.setUpdatedBy("system");
        }
        resourceMapper.updateResource(resource);
    }

    /**
     * 资源列表多条件且分页查询
     * 在多条件查询功能前开启分页查询功能, 以currentPage, pageSize为参数
     * 以categoryId, name, url为条件进行多条件模糊查询
     * 使用PageInfo封装查询结果
     *
     * @param resourceVo
     * @return
     */
    @Override
    public PageInfo<Resource> findAllResourceInPaging(ResourceVo resourceVo) {
        PageHelper.startPage(resourceVo.getCurrentPage(), resourceVo.getPageSize());
        List<Resource> resourceList = resourceMapper.findAllResource(resourceVo);
        return new PageInfo<>(resourceList);
    }
}
