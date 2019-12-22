package com.xmu.wowoto.payment.service;

import com.xmu.wowoto.payment.domain.Payment;

import java.util.List;

/**
 *
*@author MedalWill
*@date 2019/12/14
*
*
*
*/

public interface PaymentService {

    /**
     * addPayment
     *
     * @param payment
     * @return
     */
    Integer addPayment(Payment payment);
    /**
     * addPayment
     *
     * @param id
     * @return
     */
    Integer deletePayment(Integer id);
    /**
     * addPayment
     *
     * @param payment
     * @return
     */
    Integer updatePayment(Payment payment);
    /**
     * addPayment
     *
     * @param id
     * @return
     */
    Payment getPayment(Integer id);
    /**
     * addPayment
     *
     * @param paySn
     * @return
     */
    Payment getPaymentByPaySn(String paySn);
    /**
     * addPayment
     *
     * @param orderId
     * @return
     */
    List<Payment> getPaymentByOrderId(Integer orderId);
}
