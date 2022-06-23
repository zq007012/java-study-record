package com.zq.controller;

import com.zq.domain.Menu;
import com.zq.domain.ResponseResult;
import com.zq.domain.RoleMenuVo;
import com.zq.domain.Roles;
import com.zq.service.MenuService;
import com.zq.service.RoleService;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * TODO
 *
 * @author zq007
 * @version V1.0
 * @date 2022/6/5 11:46
 */
@Controller("roleController")
@RequestMapping("/role")
@ResponseBody
public class RoleController {
    @Autowired
    @Qualifier("roleService")
    private RoleService roleService;

    @Autowired
    @Qualifier("menuService")
    private MenuService menuService;

    @Autowired
    @Qualifier("comZq")
    private Logger logger;

    /**
     * 情求方式是get, 没有携带参数, 获取顶层菜单对象的集合,
     * 每个顶级的菜单对象里要有一级菜单的集合,
     * 每个一级菜单里要有二级菜单的集合,
     * 每个二级菜单里要有三级菜单的集合, 以此类推, 直到没有下级菜单.
     *
     * @return
     */
    @RequestMapping("/findAllMenu")
    public ResponseResult findAllMenu() {
        List<Menu> content = menuService.findAllMenusContainsSubMenusByPrentId(-1);
        return new ResponseResult(true, 200, "响应成功", content);
    }

    @RequestMapping("/findAllRole")
    public ResponseResult findAllRole(@RequestBody Roles roles) {
        List<Roles> content = roleService.findAllByName(roles.getName());
        return new ResponseResult(true, 200, "响应成功", content);
    }

    @RequestMapping("/saveOrUpdateRole")
    public ResponseResult saveOrUpdateRole(@RequestBody Roles role) {
        if (role.getId() == null) {
            roleService.saveRole(role);
        } else {
            roleService.updateRole(role);
        }
        return new ResponseResult(true, 200, "响应成功", null);
    }

    @RequestMapping("/findMenuByRoleId")
    public ResponseResult findMenuByRoleId(Integer roleId) {
        List<Integer> content = roleService.findMenuIdsByRoleId(roleId);
        return new ResponseResult(true, 200, "响应成功", content);
    }

    @RequestMapping("/roleContextMenu")
    public ResponseResult roleContextMenu(@RequestBody RoleMenuVo roleMenuVo, HttpServletRequest request){
        roleService.updateRoleContextMenu(roleMenuVo);
        return new ResponseResult(true,200,"响应成功",null);
    }
    
    @RequestMapping("/deleteRole")
    public ResponseResult deleteRole( Integer id){
        roleService.deleteRole(id);
        return new ResponseResult(true,200,"响应成功",null);
    }
}
