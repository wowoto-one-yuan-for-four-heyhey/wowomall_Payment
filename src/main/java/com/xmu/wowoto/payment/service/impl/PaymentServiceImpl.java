package com.xmu.wowoto.payment.service.impl;

import com.xmu.wowoto.payment.dao.PaymentDao;
import com.xmu.wowoto.payment.domain.Payment;
import com.xmu.wowoto.payment.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    PaymentDao paymentDao;

    @Override
    public Payment addPayment(Payment payment){
        return paymentDao.addPayment(payment);
    }
    @Override
    public Payment updatePayment(Payment payment){
        return paymentDao.updatePayment(payment);
    }
    @Override
    public Payment deletePayment(Integer id){
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
    public List<Payment> getAllPayments(){ return paymentDao.getAllPayments(); }

}
