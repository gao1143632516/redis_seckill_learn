package com.miaosha.Scheduled;

import com.miaosha.config.Constant;
import com.miaosha.dao.SeckillDao;
import com.miaosha.entity.Seckill;
import org.apache.tomcat.jni.Time;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * @author gmq
 * @create 2020-09-11 11:10
 */
@Component
public class Scheduler {
    private final Logger logger = LoggerFactory.getLogger(Scheduler.class);
    @Resource
    private SeckillDao seckillDao;
    @Resource
    RedisTemplate<String,Object> redisTemplate;
    //每隔2秒执行一次
    //@Scheduled(fixedRate = 5000)
    //public void testTasks() {
    //    LocalDateTime dateTime = LocalDateTime.now(); // get the current date and time
    //
    //    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    //    System.out.println(dateTime.format(formatter));
    //
    //}
    @Scheduled(fixedRate = 3000)
    public void scheduleSeckill() {

        List<Seckill> list = seckillDao.getSeckill();
        if(list.size()==0){
          //  System.out.println("暂时没有新活动");
            return ;
        }
        for(int i=0;i<list.size();i++){
            Integer goodsId = list.get(i).getGoodsId();
            Integer seckillId = list.get(i).getSeckillId();
            Integer goodsNum = list.get(i).getGoodsNum();
            logger.debug("goodsnum",goodsNum);
            for(int k=0;k<goodsNum;k++){
                redisTemplate.opsForList().rightPush(Constant.SECKILL_PRE+seckillId,goodsId);
                logger.debug("seckill:k={},se={},goods={}",k,seckillId,goodsId);
            }

            seckillDao.updateSeckillStatus(seckillId);
            System.out.println("seckill:"+seckillId+ "开始了");

        }

    }

}
