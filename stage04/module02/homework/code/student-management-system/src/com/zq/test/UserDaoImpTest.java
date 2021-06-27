package com.zq.test;

import com.zq.model.dao.UserDaoImp;
import com.zq.model.factory.UserDaoFactory;
import com.zq.model.javabean.User;
import com.zq.utils.UUIDUtils;
import org.junit.Test;

import java.util.List;

/**
 * @ClassName: UserDaoTest
 * @Description: TODO
 * @Author: zq007
 * @Date: 2021/5/27 10:43
 * @Version: V1.0
 */
public class UserDaoImpTest {
    @Test
    public void TestCreate() throws Exception {
        UserDaoImp userDaoImp = (UserDaoImp) UserDaoFactory.newUserDao();
        User user = new User();
        user.setUserId(UUIDUtils.generateUUID());
        user.setUserName("admin");
        user.setPassword("123456");
        boolean result = userDaoImp.create(user);
        System.out.println(result ? "新增用户成功, " + user.toString() : "新增用户失败");
    }

    @Test
    public void testRetrieve() throws Exception {
        UserDaoImp userDaoImp = (UserDaoImp) UserDaoFactory.newUserDao();

        List<User> userList = userDaoImp.retrieve(User.USER_NAME, "admin");
        System.out.println(userList.get(0));
    }

    @Test
    public void testUpdate() throws Exception {
        UserDaoImp userDaoImp = (UserDaoImp) UserDaoFactory.newUserDao();

        User user = userDaoImp.retrieve(User.USER_NAME, "admin").get(0);
        boolean update = userDaoImp.update(user.getUserId(), User.PASSWORD, "000000");
        System.out.println(update ? "编辑用户成功" : "编辑用户失败");
        System.out.println(userDaoImp.retrieve(User.USER_NAME, "admin").get(0));
    }

    @Test
    public void testDelete() throws Exception {
        UserDaoImp userDaoImp = (UserDaoImp) UserDaoFactory.newUserDao();

        int result = userDaoImp.delete(User.USER_NAME, "admin");
        System.out.println("删除了" + result + "条记录");
    }

}
