package com.zq.service;

import com.github.pagehelper.PageInfo;
import com.zq.domain.Resource;
import com.zq.domain.ResourceVo;

/**
 * TODO
 *
 * @author zq007
 * @version V1.0
 * @date 2022/6/10 15:13
 */
public interface ResourceService {

    /**
     * 删除资源信息
     * 删除role_resource_relaton表中国where resource_id = #{resourceId}的记录
     * 删除resource表中where id = #{resourceI}的记录
     * @param resourceId resourceId是资源表resource的主键id
     */
    void deleteResourceByResourceId(Integer resourceId);

    /**
     * 生成createdTime, updatedTime, createdBy, updatedBy, 补完形参resource,
     * 将形参resource中的信息保存到resource表中
     * @param resource 封装了name,  url,  categoryId,  description
     */
    void saveResource(Resource resource);

    /**
     * 生成updatedTime, updatedBy, 补完形参resource, 以where id = #{id}为条件将形参resource中的信息更新到resource表中
     * @param resource 封装了id, name,  url,  categoryId,  description
     */
    void updateResource(Resource resource);

    /**
     * 资源列表多条件且分页查询
     * @param resourceVo
     * @return
     */
    PageInfo<Resource> findAllResourceInPaging(ResourceVo resourceVo);
}
