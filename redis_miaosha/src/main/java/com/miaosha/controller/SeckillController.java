package com.miaosha.controller;

import com.miaosha.entity.Goods;
import com.miaosha.exception.SeckillServiceException;
import com.miaosha.service.GoodsService;
import com.miaosha.service.SeckillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author gmq
 * @create 2020-09-10 17:28
 */
@Controller
public class SeckillController {
    @Resource
    private SeckillService seckillService;

    @ResponseBody
    @RequestMapping("/toSeckill")
    public Map toSeckill(Integer seckillId, Integer userId){
        HashMap<String, Object> result = new HashMap<>();

        try{
            seckillService.hanldeSeckill(seckillId,userId);
            result.put("code",200);
            result.put("message","抢购成功");


        }catch (SeckillServiceException t){
            result.put("code","202");
            result.put("message",t.getMessage());

        }
        return result;
    }
}
