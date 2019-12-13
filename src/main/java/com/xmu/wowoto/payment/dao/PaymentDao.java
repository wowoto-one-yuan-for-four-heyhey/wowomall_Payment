package com.xmu.wowoto.payment.dao;

import com.xmu.wowoto.payment.domain.Payment;
import com.xmu.wowoto.payment.mapper.PaymentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PaymentDao {

    @Autowired
    PaymentMapper paymentMapper;

    public Payment addPayment(Payment payment) {
        paymentMapper.addPayment(payment);

        Integer id = payment.getId();
        return paymentMapper.getPayment(id);
    }
    public Payment deletePayment(Integer id) {
        paymentMapper.deletePayment(id);
        return paymentMapper.getPayment(id);
    }
    public Payment updatePayment(Payment payment){
        paymentMapper.updatePayment(payment);

        Integer id = payment.getId();
        return paymentMapper.getPayment(id);
    }
    public Payment getPayment(Integer id) {
        Payment payment = paymentMapper.getPayment(id);
        return payment;
    }
    public Payment getPaymentByPaySn(String paySn){
        Payment payment = paymentMapper.getPaymentByPaySn(paySn);
        return payment;
    }
    public List<Payment> getAllPayments(){
        return paymentMapper.getAllPayments();
    }

}
