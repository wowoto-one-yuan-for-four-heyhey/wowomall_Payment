package com.xmu.wowoto.payment.service;

import com.xmu.wowoto.payment.dao.PaymentDao;
import com.xmu.wowoto.payment.domain.Payment;
import org.springframework.beans.factory.annotation.Autowired;

public interface PaymentService {

    public Payment addPayment(Payment payment);
    public Payment getPayment(Integer id);
    public Payment deletePayment(Integer id);
    public Integer findBySn(String Sn);
    public Payment updatePayment(Integer id);

}
