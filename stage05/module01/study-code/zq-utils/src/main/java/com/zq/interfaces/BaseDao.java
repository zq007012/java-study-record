package com.zq.interfaces;

import org.apache.commons.dbutils.QueryRunner;

import javax.sql.DataSource;

/**
 * @ClassName: Dao
 * @Description: TODO
 * @Author: zq007
 * @Date: 2021/5/30 15:20
 * @Version: V1.0
 */
public abstract class BaseDao<T> implements Dao<T> {
    private DataSource dataSource;
    private QueryRunner queryRunner;

    public BaseDao() {
    }

    public BaseDao(DataSource dataSource) {
        setDataSource(dataSource);
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.queryRunner=new QueryRunner(dataSource);
    }

    public QueryRunner getQueryRunner() {
        return queryRunner;
    }


}
