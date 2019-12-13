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
    public Payment updatePayment(Payment payment){
        return paymentDao.updatePayment(payment);
    }
    public Payment getPayment(Integer id){
        return paymentDao.getPayment(id);
    }
    public Payment getPaymentByPaySn(String paySn){
        return paymentDao.getPaymentByPaySn(paySn);
    }

    public Payment deletePayment(Integer id){
        return paymentDao.deletePayment(id);
    }

}
