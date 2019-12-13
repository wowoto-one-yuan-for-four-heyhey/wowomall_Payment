package com.xmu.wowoto.payment.service;

import com.xmu.wowoto.payment.controller.vo.WxPaymentVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author MedalWill
 * @date 2019/12/12 22:15
 */

@Service
@FeignClient("wowoto-wxPayment")
public interface WxPaymentService {
    @PostMapping("wxPayment")
    String addWxPayment(WxPaymentVo wxPaymentVo);

}
