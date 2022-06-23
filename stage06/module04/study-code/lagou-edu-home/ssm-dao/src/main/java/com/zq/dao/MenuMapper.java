package com.zq.dao;

import com.zq.domain.Menu;

import java.util.List;

/**
 * TODO
 *
 * @author zq007
 * @version V1.0
 * @date 2022/6/6 17:38
 */
public interface MenuMapper {

    /**
     * 获取所有where parent_id = #{parentId}的菜单记录,
     * 获取的菜单如果有下级菜单的话同时也要获取该菜单的所有下级菜单
     * @param parentId
     * @return
     */
    List<Menu> findAllMenusContainsSubMenusByPrentId(Integer parentId);

    /**
     * 在menu表中查询所有菜单信息
     * @return
     */
    List<Menu> findAllMenu();

    /**
     * 在menu表中获取id=menuId的记录
     * @param menuId
     * @return
     */
    Menu findMenuById(Integer menuId);

    /**
     * 向menu表中保存一条菜单记录,
     * 要保存的字段有description, href, icon, name, orderNum, parentId, shown, level, createdTime, updatedTime, createdBy, updatedBy
     * @param menu
     * @return
     */
    int saveMenu(Menu menu);

    /**
     * 修改menu表中where id = #{id}的记录,
     * 需要修改的字段有description, href, icon, name, orderNum, parentId, shown, level, updatedTime, updatedBy
     * @param menu
     * @return
     */
    int updateMenu(Menu menu);
}
