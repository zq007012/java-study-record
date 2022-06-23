package com.zq.service;

import com.zq.domain.RoleMenuVo;
import com.zq.domain.Roles;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * TODO
 *
 * @author zq007
 * @version V1.0
 * @date 2022/6/5 11:43
 */
public interface RoleService {

    /**
     * 删除角色, 在role_menu_relation表中清空与角色关联的菜单id, 在roles表中删除本角色
     * @param roleId
     */
    void deleteRole(Integer roleId);

    /**
     * 为角色分配菜单列表, 删除旧的, 分配新的
     * @param roleMenuVo
     */
    void updateRoleContextMenu(RoleMenuVo roleMenuVo);

    /**
     * 根据角色id查询所有关联菜单id
     * @param roleId
     * @return
     */
    List<Integer> findMenuIdsByRoleId(Integer roleId);

    /**
     * 请求方式是post, 请求体的content-type是application/json, 所以请求体中的内容是json格式的字符串,
     * json字符串携带的参数有name, code, description,
     * 业务层需要生成createdBy, updatedBy
     * @param role
     */
    void saveRole(Roles role);

    /**
     * 请求方式是post, 请求体的content-type是application/json, 所以请求体中的内容是json格式的字符串,
     * json字符串携带的参数有id, name, code, description,
     * 业务层需要生成updatedBy, updateTime
     * @param role
     */
    void updateRole(Roles role);

    /**
     * 在roles表中以name为条件进行模糊搜索, 否则搜索roles表中所有的记录
     * @param roleName
     * @return
     */
    List<Roles> findAllByName(String roleName);
}
