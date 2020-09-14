package com.miaosha.service;

import com.miaosha.Scheduled.Scheduler;
import com.miaosha.config.Constant;
import com.miaosha.dao.GoodsDao;
import com.miaosha.dao.SeckillDao;
import com.miaosha.entity.Goods;
import com.miaosha.entity.Seckill;
import com.miaosha.exception.SeckillServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author gmq
 * @create 2020-09-10 17:45
 */
@Service
public class SeckillService {
    private final Logger logger = LoggerFactory.getLogger(Scheduler.class);
    @Resource
    private SeckillDao seckillDao;
    @Resource
    private RedisTemplate redisTemplate;

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public List<Seckill> getSeckill(){
        return seckillDao.getSeckill();
    }
    public void updateSeckillDate(Integer status){
         seckillDao.updateSeckillStatus(status);
    }
    public Map<String, Object> hanldeSeckill(Integer seckillId,Integer userId){
        Seckill seckill = seckillDao.getSeckillById(seckillId);
        if(seckill==null){
            throw new SeckillServiceException("秒杀活动不存在或者已经结束！");
        }
        Boolean b = redisTemplate.opsForSet().isMember(Constant.SECKILL_USER_PRE+seckillId,userId);
        if(b){
            throw new SeckillServiceException("不能重复参加该活动");
        }
        Integer goodsId = (Integer) redisTemplate.opsForList().leftPop(Constant.SECKILL_PRE+seckillId);
        if(goodsId==null){
            throw new SeckillServiceException("商品已经售空！");
        }
         redisTemplate.opsForSet().add(Constant.SECKILL_USER_PRE+seckillId,userId);
        Map<String,Object> result = new HashMap<>();
        String orderNum = UUID.randomUUID().toString();
        result.put("orderNum",orderNum);
        result.put("userId",userId);
        result.put("seckillId",seckillId);
        result.put("code",200);
        logger.debug("Send"+"orderNum={},userid={},seckillId={}",orderNum,userId,seckillId);
        rabbitTemplate.convertAndSend("seckill","",result);
        return result;
    }

}
