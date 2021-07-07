package com.springboot.xmybatispagehelper.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author Lee
 * @Date 2021-7-7 13:00PM
 * @Location Wuxi
 * @Describe InterceptorConfig
 * @Email 237594470@qq.com
 */
@Configuration
@Slf4j
public class InterceptorConfig implements WebMvcConfigurer {

    @Autowired
    private TraceValidHanderInterceptor traceValidHanderInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(traceValidHanderInterceptor).addPathPatterns("/page/tree/**");
        log.debug("TraceValidHanderInterceptor has loaded success");
    }
}
