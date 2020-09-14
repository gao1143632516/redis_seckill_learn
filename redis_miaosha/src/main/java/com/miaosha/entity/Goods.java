package com.miaosha.entity;

import java.io.Serializable;

/**
 * @author gmq
 * @create 2020-09-10 17:35
 */
public class Goods  implements Serializable{
    private Integer goodsId;
    private String goodsName;
    private Double goodsPrice;

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public Double getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(Double goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "goodsId=" + goodsId +
                ", goodsName='" + goodsName + '\'' +
                ", goodsPrice=" + goodsPrice +
                '}';
    }
}
