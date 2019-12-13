package com.xmu.wowoto.payment.service.impl;

import com.xmu.wowoto.payment.dao.PaymentDao;
import com.xmu.wowoto.payment.domain.Payment;
import com.xmu.wowoto.payment.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    PaymentDao paymentDao;

    public Payment addPayment(Payment payment){
        return paymentDao.addPayment(payment);
    }
    public Payment getPayment(Integer id){
        return paymentDao.findPayment(id);
    }
    public Payment deletePayment(Integer id){
        return paymentDao.deletePayment(id);
    }
    public Integer findBySn(String Sn){
        Integer id=paymentDao.findBySn(Sn);
        return id;
    }
    public Payment updatePayment(Integer id){
        return paymentDao.updatePayment(id);
    }
}
