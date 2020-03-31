package com.jdkcb.mybatisstuday.aop;

import com.jdkcb.mybatisstuday.anno.DataSource;
import com.jdkcb.mybatisstuday.common.DataSourceType;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 第二种方法：使用AOP拦截我们自定义的注解。进行动态切换数据源
 */
@Aspect
@Component
public class DynamicDataSourceAspect {
    private static final Logger logger = LoggerFactory.getLogger(DynamicDataSourceAspect.class);

    @Before("@annotation(dataSource)")//拦截我们自定义的注解
    public void changeDataSource(JoinPoint point, DataSource dataSource) throws Throwable {
        String value = dataSource.value();
        if (value.equals("primary")){
            DataSourceType.setDataBaseType(DataSourceType.DataBaseType.Primary);
        }else if (value.equals("secondary")){
            DataSourceType.setDataBaseType(DataSourceType.DataBaseType.Secondary);
        }else {
            DataSourceType.setDataBaseType(DataSourceType.DataBaseType.Primary);//默认使用主数据库
        }

        logger.info("当前连接的数据源："+ value+",默认："+DataSourceType.DataBaseType.Primary);

    }

    @After("@annotation(dataSource)") //清除数据源的配置
    public void restoreDataSource(JoinPoint point, DataSource dataSource) {
        DataSourceType.clearDataBaseType();


    }
}
