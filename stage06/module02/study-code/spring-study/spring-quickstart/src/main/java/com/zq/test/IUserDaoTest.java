package com.zq.test;

import com.zq.dao.IUserDao;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * TODO
 *
 * @author zq007
 * @version V1.0
 * @date 2022/4/3 10:19
 */
public class IUserDaoTest{
    /**
     *
     */
    @Test
    public void saveTest(){
        ApplicationContext appContext = new ClassPathXmlApplicationContext("applicationConfig.xml");
        IUserDao iUserDao = (IUserDao) appContext.getBean("save");
        System.out.println(iUserDao.save());
    }
}
