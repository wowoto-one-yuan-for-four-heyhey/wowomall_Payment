package com.xmu.wowoto.payment.service.impl;

import com.xmu.wowoto.payment.dao.PaymentDao;
import com.xmu.wowoto.payment.domain.Payment;
import com.xmu.wowoto.payment.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
*@author MedalWill
*@date 2019/12/15 15:50
*
*/
@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    PaymentDao paymentDao;

    @Override
    public Integer addPayment(Payment payment){
        return paymentDao.addPayment(payment);
    }
    @Override
    public Integer updatePayment(Payment payment){
        return paymentDao.updatePayment(payment);
    }
    @Override
    public Integer deletePayment(Integer id){
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

}
