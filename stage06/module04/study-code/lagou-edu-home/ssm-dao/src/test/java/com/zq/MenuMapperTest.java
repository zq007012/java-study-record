package com.zq;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zq.dao.MenuMapper;
import com.zq.domain.Menu;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * TODO
 *
 * @author zq007
 * @version V1.0
 * @date 2022/6/6 21:18
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContextDao.xml")
public class MenuMapperTest {
    @Autowired
    @Qualifier("menuMapper")
    private MenuMapper menuMapper;

    @Autowired
    @Qualifier("comZq")
    private Logger logger;

    /**
     * 测试{@link MenuMapper#findAllMenusContainsSubMenusByPrentId(Integer)}方法的功能
     */
    @Test
    public void findAllMenusContainsSubMenusByPrentIdTest() throws JsonProcessingException {
        List<Menu> menuList = menuMapper.findAllMenusContainsSubMenusByPrentId(-1);
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonStr = objectMapper.writeValueAsString(menuList);
        logger.info(jsonStr);
    }
}
