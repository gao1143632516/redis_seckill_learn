package com.miaosha.config;

import com.miaosha.innterceptor.MiaoShaInterceptor;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.web.servlet.filter.OrderedFormContentFilter;
import org.springframework.boot.web.servlet.filter.OrderedHiddenHttpMethodFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * @author gmq
 * @create 2020-09-14 10:01
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Resource
    private MiaoShaInterceptor miaoShaInterceptor;
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(miaoShaInterceptor).addPathPatterns("/toSeckill");
    }

}
