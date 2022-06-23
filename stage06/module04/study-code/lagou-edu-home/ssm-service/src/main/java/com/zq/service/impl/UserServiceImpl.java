package com.zq.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zq.dao.UserMapper;
import com.zq.domain.Roles;
import com.zq.domain.User;
import com.zq.domain.UserRoleRelation;
import com.zq.domain.UserVo;
import com.zq.service.UserService;
import com.zq.utils.Md5Utils;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
}
