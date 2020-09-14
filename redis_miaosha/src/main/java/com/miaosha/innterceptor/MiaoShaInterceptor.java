package com.miaosha.innterceptor;

import com.miaosha.config.Constant;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.concurrent.TimeUnit;

/**
 * @author gmq
 * @create 2020-09-14 9:54
 */
@Component
public class MiaoShaInterceptor  implements HandlerInterceptor{
    @Resource
    private RedisTemplate redisTemplate;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        response.setContentType("text/html;charset=utf-8");
        String ip = request.getRemoteAddr();
        Integer userId = Integer.parseInt(request.getParameter("userId"));
        String pathinfo = request.getRequestURI();
        String redisKey = ip+userId+pathinfo;
        
        if(userId!=null){

            Integer count = (Integer) redisTemplate.opsForValue().get(redisKey);
            if(count==null){
                redisTemplate.opsForValue().set(redisKey,1,60,TimeUnit.SECONDS);
                return true;
            }
           // redisTemplate.opsForValue().setIfAbsent(redisKey,1,60, TimeUnit.SECONDS);
            if(Constant.SECKILL_LIMIT_COUNT_PER_MINUTS<count){
                response.getWriter().write("访问过于频繁，请1分钟后重试");
                return false;
            }
            redisTemplate.opsForValue().increment(redisKey,1);
        }else{
            response.getWriter().write("请登录后再执行本次操作！");
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {

    }
}
