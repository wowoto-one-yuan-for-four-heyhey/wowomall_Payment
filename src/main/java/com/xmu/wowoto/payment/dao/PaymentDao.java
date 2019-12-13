package com.xmu.wowoto.payment.dao;

import com.xmu.wowoto.payment.domain.Payment;
import com.xmu.wowoto.payment.mapper.PaymentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PaymentDao {

    @Autowired
    PaymentMapper paymentMapper;

    public Payment addPayment(Payment payment) {
        paymentMapper.addPayment(payment);

        Integer id = payment.getId();
        return paymentMapper.findPayment(id);
    }

    public Payment findPayment(Integer id)
    {
        Payment pm=paymentMapper.findPayment(id);
        return pm;
    }

    public Payment updatePayment(Integer id)
    {
        paymentMapper.updatePayment(id);
        Payment pm=paymentMapper.findPayment(id);
        return pm;
    }
    public Payment deletePayment(Integer id)
    {
        paymentMapper.deletePayment(id);
        Payment pm=paymentMapper.findPayment(id);
        return pm;
    }
    public Integer findBySn(String Sn)
    {
        return paymentMapper.findPaymentBySn(Sn);
    }

}
