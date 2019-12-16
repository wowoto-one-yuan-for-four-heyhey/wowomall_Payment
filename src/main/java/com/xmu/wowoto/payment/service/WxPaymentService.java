package com.xmu.wowoto.payment.service;

import com.xmu.wowoto.payment.domain.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 *
 * @author Zach
 * @date 2019/12/13 16:38
 */
@FeignClient("wowoto-wxPayment")
public interface WxPaymentService {

    /**
     * 获取prepayId
    *@author MedalWill
    *@date 2019/12/14
    *
    *@param payment 对象
    *@return prepayId
    */
    @PostMapping("wxPaymentService/wxPayment")
    String useWxPay(Payment payment);

    /**
     * 模拟用户发起最终支付
     * @author Zach
     * @date 2019/12/15
     *
     * @param prepay_id 预支付标识
     * @param endTime 支付结束时间
     * @return
     */
    @PutMapping("wxpayment/{id}")
    public Object requestWxPayment(@PathVariable("id") String prepay_id, LocalDateTime endTime);

    /**
     * 退款
     * 当传入payment模块addPayment方法的参数payment的actualPrice属性为负时，addPayment代表退款操作
     * 此时，addPayment方法调用wxPayment模块的refund方法，传入paySn和actualPrice（退款金额）执行退款操作
     * 该方法与requestWxPayment类似，应调用updatePayment方法，进而调用updateOrder方法，修改相应的（多个）表状态
     *
     * @param prepayId 预支付标识
     * @param actualPrice 退款金额
     * @return WxPayment
     */
    @PutMapping("wxpayment/{id}/refund")
    public Object refund(@PathVariable String refundWhom, String refundPaymentPaySn, BigDecimal actualPrice);
}
