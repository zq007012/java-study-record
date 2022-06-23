package com.zq.service;

import com.zq.domain.Menu;

import java.util.List;

/**
 * TODO
 *
 * @author zq007
 * @version V1.0
 * @date 2022/6/6 20:03
 */
public interface MenuService {

    /**
     * 向menu表中保存一条菜单记录,
     * 要保存的字段有description, href, icon, name, orderNum, parentId, shown, level, createdTime, updatedTime, createdBy, updatedBy
     *
     * 其中createdTime, updatedTime, createdBy, updatedBy需要自己在service层生成
     * @param menu
     */
    void saveMenu(Menu menu);

    /**
     * 修改menu表中where id = #{id}的记录,
     * 需要修改的字段有description, href, icon, name, orderNum, parentId, shown, level, updatedTime, updatedBy
     *
     * 其中updatedTime, updatedBy需要在service层生成
     * @param menu
     */
    void updateMenu(Menu menu);

    /**
     * 在menu表中查询所有菜单信息
     * @return
     */
    List<Menu> findAllMenu();

    /**
     * 根据parentId获取当前层级的所有菜单, 且每个菜单都要有该菜单下的所有子菜单,
     * 子菜单也要有子菜单
     *
     * @param parentId 当值为-1时, 会获取到顶层菜单
     * @return
     */
    List<Menu> findAllMenusContainsSubMenusByPrentId(Integer parentId);

    /**
     * 在menu表中获取id=menuId的记录
     * @param menuId
     * @return
     */
    Menu findMenuById(Integer menuId);
}
