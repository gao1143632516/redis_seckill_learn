package com.miaosha.controller;

import com.miaosha.entity.Goods;
import com.miaosha.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


/**
 * @author gmq
 * @create 2020-09-10 17:28
 */
@Controller
public class GoodsController {
    @Autowired
    private GoodsService goodsService;
    @GetMapping("/goods")
    @ResponseBody
    public List getGoodsList(){
        List<Goods> list = goodsService.getGoods();
        return list;
    }
    @GetMapping("/goods/{goodsId}")
    @ResponseBody
    public Goods getGoodsById(@PathVariable("goodsId")Integer goodsId){
        Goods goods = null;
        if(goodsId!=null){
             goods = goodsService.getGoodsById(goodsId);
        }
        return goods;
    }
    @GetMapping("/add")
    @ResponseBody
    public List add(){
        Goods goods = new Goods();
        goods.setGoodsName("火腿肠");
        goods.setGoodsPrice(2.0);
        goodsService.insertGoods(goods);
        System.out.println(goods);
        return getGoodsList();
    }
}
