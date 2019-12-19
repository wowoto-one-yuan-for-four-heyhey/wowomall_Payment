package com.xmu.wowoto.payment.util;

import com.xmu.wowoto.payment.service.OrderService;
import com.xmu.wowoto.payment.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;


/**
 * @author MedalWill
 * @date 2019/12/19 22:20
 */
@Component
public class RedisKeyExpirationListener extends KeyExpirationEventMessageListener {

    @Autowired
    OrderService orderService;

    @Autowired
    PaymentService paymentService;


    public RedisKeyExpirationListener(RedisMessageListenerContainer listenerContainer) {
        super(listenerContainer);
    }

    /**
     * 针对redis数据失效事件，进行数据处理
     * @param message
     * @param pattern
     */
    @Override
    public void onMessage(Message message, byte[] pattern) {
        String expiredKey = message.toString();
        System.out.println(expiredKey);
        Integer key=Integer.parseInt(expiredKey);
        Integer orderId=paymentService.getOrderIdByPaymentId(key);
        orderService.updateOrderStatus(orderId);
        paymentService.deletePayment(key);

        }
    }


