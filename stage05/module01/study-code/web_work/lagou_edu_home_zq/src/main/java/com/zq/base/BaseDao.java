package com.zq.base;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.dbutils.QueryRunner;

import javax.sql.DataSource;

/**
 * DAO基类, 有一个数据库连接池成员变量和QueryRunner对象成员变量
 *
 * @author zq007
 * @version V1.0
 * @date 2022/2/3 19:59
 */
@NoArgsConstructor
public abstract class BaseDao {
    /**
     * 数据库连接池
     */
    @Getter
    @Setter
    private DataSource dataSource;

    /**
     * DButils的QueryRunner对象
     */
    @Getter
    @Setter
    private QueryRunner queryRunner;


    public BaseDao(DataSource dataSource) {
        this.dataSource = dataSource;
        queryRunner = new QueryRunner(dataSource);
    }

}

