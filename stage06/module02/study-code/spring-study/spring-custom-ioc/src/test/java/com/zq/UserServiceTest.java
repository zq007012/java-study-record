package com.zq;

import com.zq.service.UserService;
import com.zq.service.impl.UserServiceImpl;
import org.junit.Test;

/**
 * TODO
 *
 * @author zq007
 * @version V1.0
 * @date 2022/4/6 9:25
 */
public class UserServiceTest {
    /**
     *
     */
    @Test
    public void saveTest(){
        UserService userService = new UserServiceImpl();
        userService.save();
    }
}
