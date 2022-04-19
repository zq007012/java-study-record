package com.zq.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * TODO
 *
 * @author zq007
 * @version V1.0
 * @date 2022/4/19 8:15
 */
@Configuration("springConfig")
@ComponentScan("com.zq")
@EnableTransactionManagement
@Import(DataSourceConfig.class)
public class SpringConfig {
    @Autowired
    @Qualifier("dataSource")
    private DataSource dataSource;

    @Bean("platformTransactionManager")
    public PlatformTransactionManager getPlatformTransactionManager(){
       return new DataSourceTransactionManager(dataSource);
    }

    @Bean("jdbcTemplate")
    public JdbcTemplate getJdbcTemplate(){
        return new JdbcTemplate(dataSource);
    }
}
