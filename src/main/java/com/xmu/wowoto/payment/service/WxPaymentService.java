package com.xmu.wowoto.payment.service;

import com.xmu.wowoto.payment.domain.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

/**
 *
 * @author Zach
 * @date 2019/12/13 16:38
 */
@FeignClient("wxPaymentService")
public interface WxPaymentService {

    /**
     * 获取prepayId
    *@author MedalWill
    *@date 2019/12/14
    *
    *@param payment 对象
    *@return prepayId
    */
    @PostMapping("wxpayment")
    String useWxPay(Payment payment);

    /**
     * 模拟用户发起最终支付
     * @author Zach
     * @date 2019/12/15
     *
     * @param prepay_id 预支付标识
     * @return
     */

    @PutMapping("wxpayment/{id}")
    Object requestWxPayment(@PathVariable("id") String prepay_id);

    /**
     * 退款
     * 当传入payment模块addPayment方法的参数payment的actualPrice属性为负时，addPayment代表退款操作
     * 此时，addPayment方法调用wxPayment模块的refund方法，传入paySn和actualPrice（退款金额）执行退款操作
     * 该方法与requestWxPayment类似，应调用updatePayment方法，进而调用updateOrder方法，修改相应的（多个）表状态
     * @param refundWhom 先前支付的paysn
     * @param refundPaymentPaySn 当前退款的paysn
     * @param actualPrice 退库啊价格
     * @return
     */

    @PutMapping("wxpayment/{id}/refund")
    Object refund(@PathVariable("id") @RequestParam String refundWhom,
                  @RequestParam String refundPaymentPaySn, @RequestParam BigDecimal actualPrice);
}
