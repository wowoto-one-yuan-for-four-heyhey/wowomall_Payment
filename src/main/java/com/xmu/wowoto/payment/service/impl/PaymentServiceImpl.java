package com.xmu.wowoto.payment.service.impl;

import com.xmu.wowoto.payment.dao.PaymentDao;
import com.xmu.wowoto.payment.domain.Payment;
import com.xmu.wowoto.payment.service.PaymentService;
import com.xmu.wowoto.payment.util.RedisUtil;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
*@author MedalWill
*@date 2019/12/15 15:50
*
*/
@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    PaymentDao paymentDao;

    @Override
    public Integer addPayment(Payment payment){
        Integer result=paymentDao.addPayment(payment);
        if(result!=0)
        {Integer id=payment.getId();
        String key=id.toString();
        LocalDateTime endtime = payment.getEndTime();
        Duration duration= Duration.between(LocalDateTime.now(),endtime);
        long time=duration.toMillis();
        redisUtil.addpayment(key,time);
        return 1;}
        else
        {return 0;}
    }
    @Override
    public Integer updatePayment(Payment payment){
        return paymentDao.updatePayment(payment);
    }
    @Override
    public Integer deletePayment(Integer id){
        return paymentDao.deletePayment(id);
    }
    @Override
    public Payment getPayment(Integer id){
        return paymentDao.getPayment(id);
    }
    @Override
    public Payment getPaymentByPaySn(String paySn){
        return paymentDao.getPaymentByPaySn(paySn);
    }
    @Override
    public List<Payment> getPaymentByOrderId(Integer OrderId){
        return paymentDao.getPaymentByOrderId(OrderId);
    }
}
