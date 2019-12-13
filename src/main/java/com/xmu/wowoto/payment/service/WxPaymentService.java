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

    @PostMapping("wxPaymentService/wxPayment")
    public String useWxPay(Payment payment);

}
