package com.zq.model.factory;

import com.zq.dbconnpool.DruidPool;
import com.zq.model.dao.ClassGradeDao;
import com.zq.model.dao.ClassGradeDaoImp;

/**
 * @ClassName: ClassGradeFactory
 * @Description: TODO
 * @Author: zq007
 * @Date: 2021/5/30 22:32
 * @Version: V1.0
 */
public class ClassGradeDaoFactory {
    public static ClassGradeDao newClassGradeDao() throws Exception {
        return new ClassGradeDaoImp(DruidPool.getInstance().getDataSource());
    }
}
