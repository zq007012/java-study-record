package com.zq.model.factory;

import com.zq.dbconnpool.DruidPool;
import com.zq.model.dao.StudentDao;
import com.zq.model.dao.StudentDaoImp;

/**
 * @ClassName: StudentDaoFactory
 * @Description: TODO
 * @Author: zq007
 * @Date: 2021/5/30 22:32
 * @Version: V1.0
 */
public class StudentDaoFactory{
    public static StudentDao newStudentDao() throws Exception {
        return new StudentDaoImp(DruidPool.getInstance().getDataSource());
    }
}
