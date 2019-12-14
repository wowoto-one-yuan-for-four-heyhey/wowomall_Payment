package com.xmu.wowoto.payment.service;

import com.xmu.wowoto.payment.domain.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

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

}
