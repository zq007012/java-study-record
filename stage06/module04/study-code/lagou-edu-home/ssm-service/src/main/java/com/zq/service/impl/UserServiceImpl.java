package com.zq.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zq.dao.UserMapper;
import com.zq.domain.*;
import com.zq.service.UserService;
import com.zq.utils.Md5Utils;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

/**
 * TODO
 *
 * @author zq007
 * @version V1.0
 * @date 2022/6/4 7:22
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    @Qualifier("userMapper")
    private UserMapper userMapper;

    @Autowired
    @Qualifier("comZq")
    private Logger logger;


    /**
     * 为用户分配角色
     * 在user_role_relation表中删除用户userId担任的所有角色
     * 在user_role_relation表中将roleIdList中的角色分配给用户userId
     *
     * @param userVo 封装了userId和roleIdList
     */
    @Override
    @Transactional(readOnly = false, isolation = Isolation.REPEATABLE_READ)
    public void userContextRole(UserVo userVo) {
        Integer userId = userVo.getUserId();
        List<Integer> roleIdList = userVo.getRoleIdList();

        LocalDateTime now = LocalDateTime.now();

        ArrayList<UserRoleRelation> userRoleRelations = new ArrayList<>();
        for (Integer roleId : roleIdList) {
            UserRoleRelation userRoleRelation = new UserRoleRelation();
            userRoleRelation.setUserId(userId);
            userRoleRelation.setRoleId(roleId);
            userRoleRelation.setCreatedTime(now);
            userRoleRelation.setUpdatedTime(now);
            userRoleRelation.setCreatedBy("system");
            userRoleRelation.setUpdatedBy("system");

            userRoleRelations.add(userRoleRelation);
        }

        userMapper.deleteUserRoleRelationByUserId(userId);
        userMapper.saveUserRoleRelationInBatches(userRoleRelations.toArray(new UserRoleRelation[0]));
    }

    /**
     * 根据phone从数据表user中获取一个user对象, 什么没有获取到时, 表示登录失败, 返回null
     * 否则对password进行加盐和md5加密后, 验证加密的值是否跟user对象中的password相同, 相同则表示登录成功, 将查询到的user对象返回, 不相同则表示登录失败, 返回null
     *
     * @param user user中封装了请求中的phone和password
     * @return
     */
    @Override
    public User login(User user) {
        User userByPhone = userMapper.findUserByPhone(user.getPhone());
        if (userByPhone != null &&
                Md5Utils.verify(user.getPassword(),
                        Md5Utils.MD5KEY,
                        userByPhone.getPassword())) {
            userByPhone.setPassword(null);
        } else {
            userByPhone = null;
        }
        return userByPhone;
    }

    /**
     * 更新用户状态, 因为对表记录进行了更新操作, 所以service层还应该生成updateTime
     *
     * @param user
     */
    @Override
    @Transactional(readOnly = false, isolation = Isolation.REPEATABLE_READ)
    public void updateUserStatus(User user) {
        user.setUpdateTime(LocalDateTime.now());
        userMapper.updateUserStatus(user);
    }

    /**
     * service层调用dao层的条件查询前, 使用PageHelper对这个条件查询进行分页查询改造
     *
     * @param userVo
     * @return
     */
    @Override
    public PageInfo<User> findUserByNameAndCreateTimeInPaging(UserVo userVo) {
        logger.info(userVo);
        logger.info(userVo.getCurrentPage());
        logger.info(userVo.getPageSize());
        PageHelper.startPage(userVo.getCurrentPage(), userVo.getPageSize());
        List<User> userList = userMapper.findUserByNameAndCreateTime(userVo);
        return new PageInfo<>(userList);
    }

    /**
     * 获取该用户担任的所有角色
     *
     * @param userId userId是user的主键Id
     * @return
     */
    @Override
    public List<Roles> findRolesAssociatedWithUserByUserId(Integer userId) {
        return userMapper.findRolesAssociatedWithUserByUserId(userId);
    }

    /**
     * 获取该用户拥有的权限, 其实就是获取用户的菜单列表和资源列表
     *
     * @param userId
     * @return "menuList"是菜单对象组成的列表, "resourceList"是资源对象组成的列表
     */
    @Override
    public Map<String, List> getUserPermissions(Integer userId) {
        HashMap<String, List> map = new HashMap<>(2);
        // 获取资源列表
        List<Integer> roleIdList = userMapper.findRoleIdListOfUser(userId);
        List<Resource> resourceList = userMapper.findResourceListByRoleIdList(roleIdList);
        map.put("resourceList", resourceList);

        //获取与roleIdList关联的菜单id列表menuIdList
        List<Integer> menuIdList = userMapper.findMenuIdListByRoleIdList(roleIdList);
        //获取具有层级结构的主菜单列表, 并且这些菜单要在menuIdList范围内
        List<Menu> menuList = getSubMenuListByParnentIdAndMenuIdList(-1, menuIdList);
        map.put("menuList", menuList);
        return map;
    }

    /**
     * 获取某个菜单的子菜单列表, 这些子菜单的id要在menuIdList范围内并且有着同一个父菜单(parentId表示这个父菜单的id),
     * 利用本方法递归使子菜单也有自己的子菜单列表, 这样在菜单对象中就形成了菜单的层级结构,
     * 如果parentId的值为-1, 那么获取到的则是具有层级结构的主菜单列表
     *
     * @param parentId   表示子菜单的parentId属性, 如果parentId的值为-1, 那么获取到的则是主菜单列表
     * @param menuIdList 子菜单id的取值范围
     * @return 子菜单列表
     */
    private List<Menu> getSubMenuListByParnentIdAndMenuIdList(Integer parentId, List<Integer> menuIdList) {
        //1. 根据parentId获取子菜单列表
        List<Menu> menuList = userMapper.findSubMenuListByParentId(parentId);
        /* 2. 遍历子菜单列表, 删除掉不在menuIdList中的子菜单,
            对于在menuIdList中的子菜单, 可通过本方法的递归来获取这个子菜单的子菜单列表
        */
        for (Iterator<Menu> iterator = menuList.iterator(); iterator.hasNext(); ) {
            Menu menu = iterator.next();
            if (menuIdList.contains(menu.getId())) {
                //通过本方法的递归获取子菜单的子菜单列表
                menu.setSubMenus(getSubMenuListByParnentIdAndMenuIdList(menu.getId(), menuIdList));
            } else {
                iterator.remove();
            }
        }
        return menuList;
    }
}
