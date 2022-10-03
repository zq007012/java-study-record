package com.zq;

import com.zq.dao.UserMapper;
import com.zq.domain.Resource;
import com.zq.utils.Md5Utils;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO
 *
 * @author zq007
 * @version V1.0
 * @date 2022/9/22 8:04
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContextDao.xml")
public class UserMapperTest {
    @Autowired
    @Qualifier("userMapper")
    private UserMapper userMapper;

    @Autowired
    @Qualifier("comZq")
    private Logger logger;

    /**
     * 测试{@link UserMapper#findResourceListByRoleIdList(List)}方法的功能
     */
    @Test
    public void findAllResourceOfRoleIdListTest(){
        ArrayList<Integer> roleIdList = new ArrayList<>();
        /*roleIdList.add(1);
        roleIdList.add(2);
        roleIdList.add(3);*/
        List<Resource> resourceList = userMapper.findResourceListByRoleIdList(roleIdList);
        logger.info(resourceList);

    }

    /**
     * 测试{@link Md5Utils#md5(String, String)}方法的功能
     */
    @Test
    public void md5Test(){
        String password = Md5Utils.md5("123456", Md5Utils.MD5KEY);
        logger.info("密码123456的加盐MD5:" + password);
    }
}
