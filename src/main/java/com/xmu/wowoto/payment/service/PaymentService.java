package com.xmu.wowoto.payment.service;

import com.xmu.wowoto.payment.dao.PaymentDao;
import com.xmu.wowoto.payment.domain.Payment;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface PaymentService {

    Payment addPayment(Payment payment);
    Payment deletePayment(Integer id);
    Payment updatePayment(Payment payment);
    Payment getPayment(Integer id);
    Payment getPaymentByPaySn(String paySn);
    List<Payment> getAllPayments();

}
