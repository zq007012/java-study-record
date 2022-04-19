package com.zq.conifg;

import org.apache.commons.dbutils.QueryRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.sql.DataSource;

/**
 * TODO
 *
 * @author zq007
 * @version V1.0
 * @date 2022/4/10 9:23
 */
@Configuration
@Import(DataSourceConfig.class)
@ComponentScan("com.zq")
public class SpringConfig {
    @Bean("queryRunner")
    public QueryRunner getQueryRunner(@Autowired DataSource dataSource){
        return new QueryRunner(dataSource);
    }
}
