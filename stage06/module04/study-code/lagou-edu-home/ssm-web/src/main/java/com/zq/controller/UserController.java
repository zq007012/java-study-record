package com.zq.controller;

import com.github.pagehelper.PageInfo;
import com.zq.domain.ResponseResult;
import com.zq.domain.User;
import com.zq.domain.UserVo;
import com.zq.service.UserService;
import com.zq.utils.EmptyUtils;
import com.zq.utils.UUIDUtils;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

/**
 * TODO
 *
 * @author zq007
 * @version V1.0
 * @date 2022/6/4 7:43
 */
@Controller("userController")
@ResponseBody
@RequestMapping("/user")
public class UserController {
    @Autowired
    @Qualifier("userService")
    private UserService userService;

    @Autowired
    @Qualifier("comZq")
    private Logger logger;

    /**
     * 请求方式是post, 请求体的content-type是application/json, 在分页查询的前提下以name和createTime为条件进行多条件查询
     *
     * @param userVo
     * @return
     */
    @RequestMapping("/findAllUserByPage")
    public ResponseResult findAllUserByPage(@RequestBody UserVo userVo) {
        logger.info(userVo.getStartCreateTime());
        logger.info(userVo.getEndCreateTime());
        PageInfo<User> content = userService.findUserByNameAndCreateTimeInPaging(userVo);
        return new ResponseResult(true, 200, "响应成功", content);
    }

    /**
     * 更新用户状态, 请求方式是get, 请求中携带参数id, status, id对应user表的主键id, status对应user表的status, 可以用User对象来接收这些参数
     *
     * @param user
     * @return
     */
    @RequestMapping("/updateUserStatus")
    public ResponseResult updateUserStatus(User user) {
        userService.updateUserStatus(user);
        return new ResponseResult(true, 200, "响应成功", user.getStatus());
    }

    @RequestMapping("/login")
    public ResponseResult login(User user, HttpServletRequest request) {
        user = userService.login(user);
        if (user != null) {
            String accessToken = UUIDUtils.generateUUID();
            HttpSession session = request.getSession();
            session.setAttribute("access_token", accessToken);
            session.setAttribute("user_id", user.getId());
            logger.info("access_token: " + accessToken);
            HashMap<String, Object> map = new HashMap<>(3);
            map.put("access_token", accessToken);
            map.put("user_id", user.getId());
            map.put("user", user);
            return new ResponseResult(true, 1, "登录成功", map);
        }
        return new ResponseResult(true, 400, "登录失败, 账号或密码错误", null);
    }

    @RequestMapping("/findUserRoleById")
    public ResponseResult findUserRoleById(Integer id) {
        return new ResponseResult(true,
                200,
                "响应成功",
                userService.findRolesAssociatedWithUserByUserId(id));
    }


    /**
     * 为用户分配角色
     *
     * @param userVo 封装了userId, roleIdList, userId是用户的主键id, roleIdList是要分配给这个用户的角色的主键id的集合
     * @return
     */
    @RequestMapping("/userContextRole")
    public ResponseResult userContextRole(@RequestBody UserVo userVo) {
        userService.userContextRole(userVo);
        return new ResponseResult(true,
                200,
                "响应成功",
                null);
    }

    /**
     * 从请求头中获取access_token和user_id, 与session中的access_token和user_id进行比较,
     * 如果不相同则表明用户还未登录, 响应中提醒用户登录
     * 如果都相同则表明用户已登录, 根据user_id获取该用户拥有的权限, 其实就是获取用户的菜单列表和资源列表
     *
     * @return
     */
    @RequestMapping("/getUserPermissions")
    public ResponseResult getUserPermissions(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Integer userIdFromSession = (Integer) session.getAttribute("user_id");
        String accessTokenFromSession = (String) session.getAttribute("access_token");
        //session中没有保存登录账号或者登录令牌, 需要先登录
        if (EmptyUtils.isEmpty(userIdFromSession) || EmptyUtils.isEmpty(accessTokenFromSession)) {
            return new ResponseResult(true, 400, "请先登录", null);
        }

        String accessTokenFromHeader = request.getHeader("Authorization");
        //请求头中的登录令牌与session中的登录令牌是否匹配
        if (accessTokenFromSession.equalsIgnoreCase(accessTokenFromHeader)) {
            return new ResponseResult(true, 1, "success",
                    userService.getUserPermissions(userIdFromSession));
        } else {
            return new ResponseResult(true, 400, "请先登录", null);
        }
    }
}
