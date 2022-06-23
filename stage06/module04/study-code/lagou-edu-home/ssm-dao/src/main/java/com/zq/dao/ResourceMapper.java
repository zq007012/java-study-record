package com.zq.dao;

import com.zq.domain.Resource;
import com.zq.domain.ResourceVo;

import java.util.List;

/**
 * TODO
 *
 * @author zq007
 * @version V1.0
 * @date 2022/6/10 14:47
 */
public interface ResourceMapper {

    /**
     * 以categoryId, name, url
     * 为条件进行多条件模糊查询
     * @param resourceVo
     * @return
     */
    List<Resource> findAllResource(ResourceVo resourceVo);

    /**
     * 在resource表中新增一条记录来保存这些信息,
     * 要保存的字段有name,  url,  categoryId,  description, createdTime, updatedTime, createdBy, updatedBy
     *
     * @param resource 封装了name,  url,  categoryId,  description, createdTime, updatedTime, createdBy, updatedBy
     * @return 保存了多少条记录, 小于1则表示保存失败
     */
    int saveResource(Resource resource);

    /**
     * 更新resource表中where id = #{id}记录的name,  url,  categoryId,  description, updatedTime, updatedBy关联字段
     * @param resource 封装了id, name,  url,  categoryId,  description, updatedTime, updatedBy
     * @return 更新了多少条记录, 小于1则表示更新失败
     */
    int updateResource(Resource resource);

    /**
     * 删除role_resource_relaton表中where resource_id = #{resourceId}的记录
     * @param resourceId resourceId是role_resource_relation表中的resource_id
     * @return 删除了多少条记录
     */
    int deleteRoleResourceRelationByResourceId(Integer resourceId);


    /**
     * 删除resource表中where id = #{resourceI}的记录
     * @param resourceId resourceId是resource表的主键id
     * @return 删除了多少条记录, 小于1则表示删除失败
     */
    int deleteResourceById(Integer resourceId);
}
