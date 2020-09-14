package com.order.service;

import com.order.dao.OrderDao;
import com.order.entity.Order;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author gmq
 * @create 2020-09-14 11:10
 */
@Service
public class OrderService {
    @Resource
     private OrderDao orderDao;

    public void  saveOrder(){
        Order order = new Order();
        order.setUserId(1);
        order.setGoodsId(1);
        order.setSeckillId(1);
        orderDao.saveOrder(order);

    }
}
