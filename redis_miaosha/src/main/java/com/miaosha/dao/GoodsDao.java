package com.miaosha.dao;


import com.miaosha.entity.Goods;

import java.util.List;

/**
 * @author gmq
 * @create 2020-09-10 17:38
 */
public interface GoodsDao {

     List<Goods> getGoodsList();
     int insertGoods(Goods goods);
     Goods getGoodsById(Integer goodsId);

}
