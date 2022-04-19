package com.zq.dao.impl;

import com.zq.dao.UserDao;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Properties;

/**
 * TODO
 *
 * @author zq007
 * @version V1.0
 * @date 2022/4/6 9:06
 */
@Data
@NoArgsConstructor
public class UserDaoImpl implements UserDao {
    private Properties propertiesDemo;

    @Override
    public void save() {
        System.out.println(propertiesDemo);
        System.out.println("保存成功了...");
    }
}
