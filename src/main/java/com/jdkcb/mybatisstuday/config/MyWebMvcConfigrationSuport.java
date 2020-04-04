package com.jdkcb.mybatisstuday.config;

import com.jdkcb.mybatisstuday.interceptor.LogInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * 日志拦截器配置定义
 */
@Configuration
public class MyWebMvcConfigrationSuport extends WebMvcConfigurationSupport {

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(new LogInterceptor()).addPathPatterns("/**");

        super.addInterceptors(registry);
    }
}
