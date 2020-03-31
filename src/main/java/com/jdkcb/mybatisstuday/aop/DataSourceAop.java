package com.jdkcb.mybatisstuday.aop;

import com.jdkcb.mybatisstuday.common.DataSourceType;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * 第一种方法：采用AOP方式拦截请求执行的方法，动态切换需要访问的数据源
 */
//@Aspect
//@Component
public class DataSourceAop {
//    //在primary方法前执行
//    @Before("execution(* com.jdkcb.mybatisstuday.controller.UserController.primary(..))")
//    public void setDataSource2test01() {
//        System.err.println("Primary业务");
//        DataSourceType.setDataBaseType(DataSourceType.DataBaseType.Primary);
//    }
//
//    //在secondary方法前执行
//    @Before("execution(* com.jdkcb.mybatisstuday.controller.UserController.secondary(..))")
//    public void setDataSource2test02() {
//        System.err.println("Secondary业务");
//        DataSourceType.setDataBaseType(DataSourceType.DataBaseType.Secondary);
//    }
}
