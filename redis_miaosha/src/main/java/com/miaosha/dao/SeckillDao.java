package com.miaosha.dao;
import java.util.*;
import com.miaosha.entity.Seckill;

/**
 * @author gmq
 * @create 2020-09-11 11:21
 */
public interface SeckillDao {
    List<Seckill>getSeckill();
    void updateSeckillStatus(Integer seckillId);
    Seckill getSeckillById(Integer seckillId);
}
