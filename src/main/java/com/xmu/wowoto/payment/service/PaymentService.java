package com.xmu.wowoto.payment.service;

import com.xmu.wowoto.payment.dao.PaymentDao;
import com.xmu.wowoto.payment.domain.Payment;
import org.springframework.beans.factory.annotation.Autowired;

public interface PaymentService {

    public Payment addPayment(Payment payment);
    public Payment updatePayment(Payment payment);
    public Payment getPayment(Integer id);
    public Payment getPaymentByPaySn(String paySn);
    public Payment deletePayment(Integer id);

}
