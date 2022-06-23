package com.zq.controller;

import com.zq.domain.Menu;
import com.zq.domain.ResponseResult;
import com.zq.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;

/**
 * TODO
 *
 * @author zq007
 * @version V1.0
 * @date 2022/6/9 16:26
 */
@Controller("menuController")
@RequestMapping("/menu")
@ResponseBody
public class MenuController {
    @Autowired
    @Qualifier("menuService")
    private MenuService menuService;

    @RequestMapping("/findAllMenu")
    public ResponseResult findAllMenu() {
        List<Menu> content = menuService.findAllMenu();
        return new ResponseResult(true, 200, "响应成功", content);
    }

    @RequestMapping("/findMenuInfoById")
    public ResponseResult findMenuInfoById(Integer id) {
        Menu menuInfo = null;
        if (id != null) {
            menuInfo = menuService.findMenuById(id);
        }
        List<Menu> parentMenuList = menuService.findAllMenusContainsSubMenusByPrentId(-1);
        HashMap<String, Object> content = new HashMap<>();
        content.put("menuInfo", menuInfo);
        content.put("parentMenuList", parentMenuList);
        return new ResponseResult(true, 200, "响应成功", content);
    }

    @RequestMapping("/saveOrUpdateMenu")
    public ResponseResult saveOrUpdateMenu(@RequestBody Menu menu) {
        if (menu.getId() == null) {
            menuService.saveMenu(menu);
        } else {
            menuService.updateMenu(menu);
        }
        return new ResponseResult(true, 200, "响应成功", null);
    }
}
