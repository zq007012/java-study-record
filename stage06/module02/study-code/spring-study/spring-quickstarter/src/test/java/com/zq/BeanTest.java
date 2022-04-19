package com.zq;

import com.zq.dao.UserDao;
import com.zq.domain.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * TODO
 *
 * @author zq007
 * @version V1.0
 * @date 2022/4/6 17:33
 */
public class BeanTest {
    /**
     *
     */
    @Test
    public void beanTest() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("ApplicationContext.xml");
        UserDao userDao = (UserDao) applicationContext.getBean("userDao");
        userDao.save();
    }

    @Test
    public void diTest() {
        ApplicationContext appContext =
                new ClassPathXmlApplicationContext("ApplicationContext.xml");
        User user = appContext.getBean("user", User.class);
        System.out.println(user);
    }
}
