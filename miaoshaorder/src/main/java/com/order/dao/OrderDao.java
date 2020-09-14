package com.order.dao;

import com.order.entity.Order;

import java.util.*;

/**
 * @author gmq
 * @create 2020-09-14 11:11
 */
public interface OrderDao {
    void saveOrder(Order order);
    List<Map<String,Object>>getOrderInfo();
}
