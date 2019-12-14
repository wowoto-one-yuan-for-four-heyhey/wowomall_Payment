package com.xmu.wowoto.payment.service;

import com.xmu.wowoto.payment.domain.Payment;

/**
 *
*@author MedalWill
*@date 2019/12/14
*
*
*
*/
public interface PaymentService {

    Integer addPayment(Payment payment);
    Integer deletePayment(Integer id);
    Integer updatePayment(Payment payment);
    Payment getPayment(Integer id);
    Payment getPaymentByPaySn(String paySn);

}
