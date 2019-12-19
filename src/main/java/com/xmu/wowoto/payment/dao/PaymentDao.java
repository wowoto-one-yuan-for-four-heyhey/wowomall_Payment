package com.xmu.wowoto.payment.dao;

import com.xmu.wowoto.payment.domain.Payment;
import com.xmu.wowoto.payment.mapper.PaymentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 
*@author MedalWill
*@date 2019/12/14
*
*/
@Repository
public class PaymentDao {

    @Autowired
    PaymentMapper paymentMapper;

    public Integer addPayment(Payment payment) {
        return paymentMapper.addPayment(payment);
    }
    public Integer deletePayment(Integer id) {
        return paymentMapper.deletePayment(id);
    }
    public Integer updatePayment(Payment payment){
        return paymentMapper.updatePayment(payment);
    }
    public Payment getPayment(Integer id) {
        return paymentMapper.getPayment(id);
    }
    public Payment getPaymentByPaySn(String paySn){ return paymentMapper.getPaymentByPaySn(paySn); }
    public List<Payment> getPaymentByOrderId(Integer orderId){ return paymentMapper.getPaymentByOrderId(orderId); }
    public Integer getOrderIdByPaymentId(Integer paymentId){return paymentMapper.getOrderIdByPaymentId(paymentId);}
}
