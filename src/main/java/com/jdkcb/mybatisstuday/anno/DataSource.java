package com.jdkcb.mybatisstuday.anno;

import java.lang.annotation.*;

/**
 * 自定义注解：
 *      用在Dao层中，标注方法是用的数据源，有AOP进行拦截，进行动态切换数据源
 *
 * 切换数据注解 可以用于类或者方法级别 方法级别优先级 > 类级别
 */
@Target({ElementType.METHOD, ElementType.TYPE, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataSource {
    String value() default "primary"; //该值即key值，默认使用默认数据库
}
