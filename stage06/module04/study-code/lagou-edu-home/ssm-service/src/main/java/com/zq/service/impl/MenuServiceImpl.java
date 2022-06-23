package com.zq.service.impl;

import com.zq.dao.MenuMapper;
import com.zq.domain.Menu;
import com.zq.service.MenuService;
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
 * @date 2022/6/6 20:36
 */
@Service("menuService")
public class MenuServiceImpl implements MenuService {

    @Autowired
    @Qualifier("menuMapper")
    private MenuMapper menuMapper;


    /**
     * 向menu表中保存一条菜单记录,
     * 要保存的字段有description, href, icon, name, orderNum, parentId, shown, level, createdTime, updatedTime, createdBy, updatedBy
     * <p>
     * 其中createdTime, updatedTime, createdBy, updatedBy需要自己在service层生成
     *
     * @param menu
     */
    @Override
    @Transactional(readOnly = false, isolation = Isolation.REPEATABLE_READ)
    public void saveMenu(Menu menu) {
        LocalDateTime now = LocalDateTime.now();
        menu.setCreatedTime(now);
        menu.setUpdatedTime(now);
        if (EmptyUtils.isEmpty(menu.getCreatedBy())) {
            menu.setCreatedBy("system");
        }

        if (EmptyUtils.isEmpty(menu.getUpdatedBy())) {
            menu.setUpdatedBy("system");
        }

        menuMapper.saveMenu(menu);
    }

    /**
     * 修改menu表中where id = #{id}的记录,
     * 需要修改的字段有description, href, icon, name, orderNum, parentId, shown, level, updatedTime, updatedBy
     * <p>
     * 其中updatedTime, updatedBy需要在service层生成
     *
     * @param menu
     */
    @Override
    @Transactional(readOnly = false, isolation = Isolation.REPEATABLE_READ)
    public void updateMenu(Menu menu) {
        menu.setUpdatedTime(LocalDateTime.now());
        if (EmptyUtils.isEmpty(menu.getUpdatedBy())) {
            menu.setUpdatedBy("system");
        }

        menuMapper.updateMenu(menu);
    }

    /**
     * 在menu表中查询所有菜单信息
     *
     * @return
     */
    @Override
    public List<Menu> findAllMenu() {
        return menuMapper.findAllMenu();
    }

    /**
     * 根据parentId获取当前层级的所有菜单, 且每个菜单都要有该菜单下的所有子菜单,
     * 子菜单也要有子菜单
     *
     * @param parentId 当值为-1时, 会获取到顶层菜单
     * @return
     */
    @Override
    public List<Menu> findAllMenusContainsSubMenusByPrentId(Integer parentId) {
        return menuMapper.findAllMenusContainsSubMenusByPrentId(parentId);
    }

    /**
     * 在menu表中获取id=menuId的记录
     *
     * @param menuId
     * @return
     */
    @Override
    public Menu findMenuById(Integer menuId) {
        return menuMapper.findMenuById(menuId);
    }
}
