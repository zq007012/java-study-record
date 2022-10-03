package com.zq.service;

import com.github.pagehelper.PageInfo;
import com.zq.domain.Roles;
import com.zq.domain.User;
import com.zq.domain.UserVo;

import java.util.List;
import java.util.Map;

/**
 * TODO
 *
 * @author zq007
 * @version V1.0
 * @date 2022/6/4 7:19
 */
public interface UserService {

    /**
     * 为用户分配角色
     * 在user_role_relation表中删除用户userId担任的所有角色
     * 在user_role_relation表中将roleIdList中的角色分配给用户userId
     *
     * @param userVo 封装了userId和roleIdList
     */
    void userContextRole(UserVo userVo);

    /**
     * 根据phone从数据表user中获取一个user对象, 什么没有获取到时, 表示登录失败, 返回null
     * 否则对password进行加盐和md5加密后, 验证加密的值是否跟user对象中的password相同, 相同则表示登录成功, 将查询到的user对象返回, 不相同则表示登录失败, 返回null
     * @param user user中封装了请求中的phone和password
     * @return
     */
    User login(User user);

    /**
     * 更新用户状态, 因为对表记录进行了更新操作, 所以service层还应该生成updateTime
     * @param user
     */
    void updateUserStatus(User user);

    /**
     * service层调用dao层的条件查询前, 使用PageHelper对这个条件查询进行分页查询改造
     * @param userVo
     * @return
     */
    PageInfo<User> findUserByNameAndCreateTimeInPaging(UserVo userVo);

    /**
     * 获取该用户担任的所有角色
     * @param userId userId是user的主键Id
     * @return
     */
    List<Roles> findRolesAssociatedWithUserByUserId(Integer userId);

    /**
     * 获取该用户拥有的权限, 其实就是获取用户的菜单列表和资源列表
     * @param userId
     * @return "menuList"是菜单对象组成的列表, "resourceList"是资源对象组成的列表
     */
    Map<String, List> getUserPermissions(Integer userId);

}
