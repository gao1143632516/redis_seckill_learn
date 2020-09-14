package com.miaosha;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;


/**
 * @author gmq
 * @create 2020-09-10 17:06
 */
@SpringBootApplication
@MapperScan("com.miaosha.*")
@EnableCaching//开启缓存
@EnableScheduling
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 86400*30)
public class MiaoShao  {
    public static void main(String[] args) {
        SpringApplication.run(MiaoShao.class,args);
    }

}
