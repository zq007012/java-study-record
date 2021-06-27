package com.zq.model.service;

import com.zq.model.dao.UserDao;
import com.zq.model.javabean.User;
import com.zq.utils.EmptyUtils;
import com.zq.utils.UUIDUtils;

import java.util.List;

/**
 * @ClassName: UserManagerService
 * @Description: 为用户管理员提供服务
 * @Author: zq007
 * @Date: 2021/5/28 21:54
 * @Version: V1.0
 */
public class UserManagerService {
    private UserDao userDao;

    public UserManagerService() {
    }

    public UserManagerService(UserDao userDao) {
        this.userDao = userDao;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    /**
     * 注册新用户
     *
     * @param userName
     * @param password
     * @return
     */
    public boolean registerUser(String userName, String password) {
        User user = getUserByName(userName);
        if ( null != user ){
            return false;
        }
        user = new User();
        user.setUserId(UUIDUtils.generateUUID());
        user.setUserName(userName);
        user.setPassword(password);

        return userDao.create(user);
    }

    public User getUserByName(String userName) {
        List<User> retrieve = userDao.retrieve(User.USER_NAME, userName);
        return EmptyUtils.isEmpty(retrieve) ? null : retrieve.get(0);
    }

    /**
     * 登录验证, 验证账号和密码, 如果返回null, 则说明登录失败
     *
     * @param userName
     * @param password
     * @return
     */
    public User logInVerify(String userName, String password) {
        List<User> userList = userDao.retrieve(User.USER_NAME, userName);
        User user = EmptyUtils.isEmpty(userList) ? null : userList.get(0);
        return user == null || !user.getPassword().equals(password) ? null : user;

    }

    /**
     * 通过sessionId来确认当前用户是否登录过, 返回null则表示没有登录过
     * @param sessionId
     * @return
     */
    public User hadLoggedIn(String sessionId) {
        return userDao.retrieve(User.SESSION_ID_OF_LAST_MARK_LOGIN_SUCCESSFULLY,
                sessionId).get(0);

    }

    /**
     * 把当前登录成功的状态更新到user表中, 更新的是当前的sessionId以及当前日期
     *
     * @param user
     * @param sessionId 当前绘画的sessionId
     * @return
     */
    public boolean storeLoggedInStatus(User user, String sessionId) {
        return userDao.updateSessionIdAndDate(user,sessionId);
    }

    /**
     * 移除用户一周内免登录的状态
     * @param user
     */
    public void removeLogInStatus(User user) {
        userDao.update(user.getUserId(),User.SESSION_ID_OF_LAST_MARK_LOGIN_SUCCESSFULLY,"");
    }
}
