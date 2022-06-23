package com.zq.service.impl;

import com.zq.dao.RoleMapper;
import com.zq.domain.RoleMenuRelation;
import com.zq.domain.RoleMenuVo;
import com.zq.domain.Roles;
import com.zq.service.RoleService;
import com.zq.utils.EmptyUtils;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * TODO
 *
 * @author zq007
 * @version V1.0
 * @date 2022/6/5 11:44
 */
@Service("roleService")
public class RoleServiceImpl implements RoleService {
    @Autowired
    @Qualifier("roleMapper")
    private RoleMapper roleMapper;

    @Autowired
    @Qualifier("comZq")
    private Logger logger;

    /**
     * 删除角色, 在role_menu_relation表中清空与角色关联的菜单id, c
     *
     * @param roleId
     */
    @Override
    @Transactional(readOnly = false, isolation = Isolation.REPEATABLE_READ)
    public void deleteRole(Integer roleId) {
        //在role_menu_relation表中清空与角色关联的菜单id
        roleMapper.deleteRoleContextMenuByRoleId(roleId);
        //在role_menu_relation表中清空与角色关联的菜单id
        roleMapper.deleteRoleById(roleId);
    }

    /**
     * 为角色分配菜单列表, 删除旧的, 分配新的
     *
     * @param roleMenuVo
     */
    @Override
    //涉及了增删改, 要进行事务控制
    @Transactional(readOnly = false, isolation = Isolation.REPEATABLE_READ)
    public void updateRoleContextMenu(RoleMenuVo roleMenuVo) {
        // 1. 删除该角色旧的菜单列表
        Integer roleId = roleMenuVo.getRoleId();
        roleMapper.deleteRoleContextMenuByRoleId(roleId);

        // 2. 为该角色创建新的菜单列表
        ArrayList<RoleMenuRelation> roleMenuRelations = new ArrayList<>();
        for (Integer menuId :
                roleMenuVo.getMenuIdList()) {
            RoleMenuRelation roleMenuRelation = new RoleMenuRelation();

            roleMenuRelation.setMenuId(menuId);
            roleMenuRelation.setRoleId(roleId);

            LocalDateTime now = LocalDateTime.now();
            roleMenuRelation.setCreatedTime(now);
            roleMenuRelation.setUpdatedTime(now);

            roleMenuRelation.setCreatedBy("system");
            roleMenuRelation.setUpdatedBy("system");

            roleMenuRelations.add(roleMenuRelation);
        }

        // 3. 为该角色分配新的菜单列表
        if (roleMenuRelations.size() != 0) {
            roleMapper.saveRoleContextMenu(roleMenuRelations.toArray(new RoleMenuRelation[0]));
        }
    }

    /**
     * 根据角色id查询所有关联菜单id
     *
     * @param roleId
     * @return
     */
    @Override
    public List<Integer> findMenuIdsByRoleId(Integer roleId) {
        return roleMapper.findMenuIdsByRoleId(roleId);
    }

    /**
     * 请求方式是post, 请求体的content-type是application/json, 所以请求体中的内容是json格式的字符串,
     * json字符串携带的参数有name, code, description,
     * 业务层需要生成createdBy, updatedBy
     *
     * @param role
     */
    @Override
    @Transactional(readOnly = false, isolation = Isolation.REPEATABLE_READ)
    public void saveRole(Roles role) {
        if (EmptyUtils.isEmpty(role.getCreatedBy())) {
            role.setCreatedBy("system");
        }

        if (EmptyUtils.isEmpty(role.getUpdatedBy())) {
            role.setUpdatedBy("system");
        }

        roleMapper.saveRole(role);
    }

    /**
     * 请求方式是post, 请求体的content-type是application/json, 所以请求体中的内容是json格式的字符串,
     * json字符串携带的参数有id, name, code, description,
     * 业务层需要生成updatedBy, updateTime
     *
     * @param role
     */
    @Override
    @Transactional(readOnly = false, isolation = Isolation.REPEATABLE_READ)
    public void updateRole(Roles role) {
        if (EmptyUtils.isEmpty(role.getUpdatedBy())) {
            role.setUpdatedBy("system");
        }

        role.setUpdatedTime(LocalDateTime.now());
        roleMapper.updateRole(role);
    }

    /**
     * 在roles表中以name为条件进行模糊搜索, 否则搜索roles表中所有的记录
     *
     * @param roleName
     * @return
     */
    @Override
    public List<Roles> findAllByName(String roleName) {
        logger.info("roleName: " + roleName);
        return roleMapper.findAllByName(roleName);
    }

}
