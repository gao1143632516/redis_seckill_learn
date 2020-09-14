package com.miaosha.service;
import java.util.*;

import com.miaosha.dao.GoodsDao;
import com.miaosha.entity.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author gmq
 * @create 2020-09-10 17:45
 */
@Service
public class GoodsService {

    @Resource
    private GoodsDao goodsDao;
    @Resource
    RedisTemplate<String,Object> redisTemplate;
    @Cacheable(value="gmq",key = "targetClass+methodName")
    public  List<Goods>getGoods(){
        return goodsDao.getGoodsList();
    }
    public void insertGoods(Goods goods){
        goodsDao.insertGoods(goods);
    }
    public Goods getGoodsById(Integer goodsId){
        Goods goods = (Goods)redisTemplate.opsForValue().get("goodsid:"+goodsId);
        if(goods==null){
            goods = goodsDao.getGoodsById(goodsId);
            redisTemplate.opsForValue().set("goodsid:"+goodsId,goods);

        }else{
            System.out.println("我从缓存来");
        }

        return goods;
    }
}
