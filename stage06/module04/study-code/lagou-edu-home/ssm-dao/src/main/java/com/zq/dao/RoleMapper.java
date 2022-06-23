package com.zq.dao;

import com.zq.domain.RoleMenuRelation;
import com.zq.domain.Roles;

import java.util.List;

/**
 * TODO
 *
 * @author zq007
 * @version V1.0
 * @date 2022/6/5 11:24
 */
public interface RoleMapper {
    /**
     * 在roles表中以name为条件进行模糊搜索, 否则搜索roles表中所有的记录
     * @param roleName
     * @return
     */
    List<Roles> findAllByName(String roleName);

    /**
     * 将name, code, description, createdTime, updatedTime, createdBy, updatedBy这些数据保存到roles表中, 这些数据封装在了Roles对象中
     *
     * @param role
     * @return
     */
    int saveRole(Roles role);

    /**
     * 修改roles表中where id = #{id}的记录中的name, code, description, updatedBy, updateTime所映射的字段, 这些数据封装在了Roles对象中
     *
     * @param role
     * @return
     */
    int updateRole(Roles role);

    /**
     * 根据角色id查询关联菜单id
     * @param roleId
     * @return
     */
    List<Integer> findMenuIdsByRoleId(Integer roleId);

    /**
     * 删除role_menu_relation表中where id = #{roleId}的记录
     * @param roleId
     * @return
     */
    int deleteRoleContextMenuByRoleId(Integer roleId);

    /**
     * 向role_menu_relation表中插入新的记录, 利用foreach节点进行批处理
     * @param roleMenuRelations
     * @return
     */
    int saveRoleContextMenu(RoleMenuRelation...roleMenuRelations);

    /**
     * 在roles表中删除本角色
     * @param roleId
     * @return
     */
    int deleteRoleById(Integer roleId);
}
