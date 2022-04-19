package com.zq.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * TODO
 *
 * @author zq007
 * @version V1.0
 * @date 2022/4/15 18:17
 */
@Configuration
@ComponentScan({"com.zq"})
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class SpringConfig {

}
