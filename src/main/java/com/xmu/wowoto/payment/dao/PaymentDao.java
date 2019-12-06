package com.xmu.wowoto.payment.dao;

import com.xmu.wowoto.payment.domain.Payment;
import com.xmu.wowoto.payment.mapper.PaymentMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class PaymentDao {
    @Autowired
    PaymentMapper paymentMapper;
    public Payment addPayment(Payment payment)
    {
        paymentMapper.addPayment(payment);
        Integer id=payment.getId();
        Payment pm=paymentMapper.findPayment(id);
        return pm;
    }

}
