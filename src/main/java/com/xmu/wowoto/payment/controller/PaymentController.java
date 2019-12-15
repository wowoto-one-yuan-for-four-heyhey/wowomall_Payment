package com.xmu.wowoto.payment.controller;

import com.xmu.wowoto.payment.domain.Payment;
import com.xmu.wowoto.payment.service.OrderService;
import com.xmu.wowoto.payment.service.PaymentService;
import com.xmu.wowoto.payment.service.WxPaymentService;
import com.xmu.wowoto.payment.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * PaymentController
 *
 * @author Zach
 * @date 2019/12/13
 */
@RestController
@RequestMapping("paymentService")
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    @Autowired
    WxPaymentService wxPaymentService;

    @Autowired
    OrderService orderService;

    /**
     * 订单模块调用此方法请求下单支付
     * 此方法再调用WxPayment模块的unifiedWxPayment()方法（模拟微信统一支付api），unifiedWxPayment方法应该返回prepay_id等信息
     * 此方法拿到prepay_id等信息后，需要修改数据库中payment表相应属性的属性值，如paySn等等
     * 调用此方法后，前端应显示包括商户名称、订单总价、支付方式(等)信息的界面，供用户确认其支付信息
     * 确认其支付信息后，用户可以点击确认支付按钮调用WxPayment模块的RequestPayment()方法发起最终支付
     *
     * @param inPayment 订单POJO
     * @return Payment
     */
    @PostMapping("payment")
    public Object createPayment(@RequestBody Payment inPayment){

        Payment payment = new Payment();
        payment.setActualPrice(inPayment.getActualPrice());
        payment.setOrderId(inPayment.getOrderId());
        payment.setPayChannel(inPayment.getPayChannel());

        String prepayId;
        prepayId = wxPaymentService.useWxPay(payment);
        payment.setPaySn(prepayId);

        Integer result=paymentService.addPayment(payment);

        if(result==0){return ResponseUtil.updatedDataFailed();}

        Payment ret=paymentService.getPayment(payment.getId());

        if(ret==null)
        {
            return ResponseUtil.updatedDateExpired();
        }
        return ResponseUtil.ok(ret);
    }

    /**
     * （模拟的）微信后台调用此方法修改订单状态
     * 此方法还会调用order模块的updateOrder方法，修改订单状态
     *
     * @param prepayId：预支付订单号
     * @return Payment
     */
    @PutMapping("payment/{id}")
    public Object updatePayment(@PathVariable("id") String prepayId, boolean successfulPayment){
        Payment payment = paymentService.getPaymentByPaySn(prepayId);
        if(successfulPayment){
            payment.setBeSuccessful(true);
        }
        else{
            payment.setBeSuccessful(false);
        }
        payment.setPayTime(LocalDateTime.now());

        Integer result=paymentService.updatePayment(payment);

        if(result==0){return ResponseUtil.updatedDataFailed();}

        Payment ret=paymentService.getPayment(payment.getId());

        orderService.updateOrderStatus(ret.getOrderId());

        return ResponseUtil.ok(ret);
    }


}
