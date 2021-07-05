package com.zq.base;

import com.zq.utils.EmptyUtils;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.GenerousBeanProcessor;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Dao基类
 *
 * @author      zq007
 * @version     V1.0
 * @date        2021/7/2 7:41
 */
public abstract class BaseDao<T> {
    public static void aaaDchilema(){
    }
    @Getter
    @Setter
    private DataSource dataSource;

    @Getter
    @Setter
    private QueryRunner queryRunner;

    public BaseDao() {
    }

    public BaseDao(DataSource dataSource) {
        this.dataSource = dataSource;
        queryRunner = new QueryRunner(dataSource);
    }
}
