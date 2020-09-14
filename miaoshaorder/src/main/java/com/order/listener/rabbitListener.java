package com.order.listener;

import com.order.dao.OrderDao;
import com.order.entity.Order;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Map;

/**
 * @author gmq
 * @create 2020-09-11 19:13
 */
@Component
@RabbitListener(queues = "seckill-order")
public class rabbitListener {
    @Resource
    private OrderDao orderDao;
    @RabbitHandler
    public void process(Map message1, Channel channel,Message meassage){
        System.out.println(message1);
        System.out.println(meassage);
        try {
            channel.basicAck(meassage.getMessageProperties().getDeliveryTag(),false);
           Order order = new Order();
           order.setOrderNum(message1.get("orderNum").toString());
           order.setGoodsId((Integer) message1.get("goodsId"));
           order.setUserId((Integer)message1.get("userId"));
           order.setReceivePhone("15136798934");
            orderDao.saveOrder(order);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("receiver success");

    }
}
