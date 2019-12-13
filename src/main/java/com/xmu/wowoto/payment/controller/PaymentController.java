package com.xmu.wowoto.payment.controller;

import com.xmu.wowoto.payment.controller.po.OrderPo;
import com.xmu.wowoto.payment.controller.vo.PaymentVO;
import com.xmu.wowoto.payment.domain.Payment;
import com.xmu.wowoto.payment.service.PaymentService;
import com.xmu.wowoto.payment.util.ResponseUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("paymentService")
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    /**
     * 订单模块调用此方法请求下单支付
     * 此方法再调用WxPayment模块的unifiedWxPayment()方法（模拟微信统一支付api），unifiedWxPayment方法应该返回prepay_id等信息
     * 此方法拿到prepay_id等信息后，需要修改数据库中payment表相应属性的属性值，如paySn等等
     * 调用此方法后，前端应显示包括商户名称、订单总价、支付方式(等)信息的界面，供用户确认其支付信息
     * 确认其支付信息后，用户可以点击确认支付按钮调用WxPayment模块的RequestPayment()方法发起最终支付
     *
     * @param orderPo
     * @return OrderPaymentVo
     */
    @PostMapping("payment")
    public Payment addPayment(@RequestBody OrderPo orderPo){
        Payment payment = new Payment();
        payment.setActualPrice(orderPo.getIntegralPrice());
        // payChannel
        payment.setSuccessful(false);
        // payTime
        // paySn
        payment.setBeginTime();

    }
    //order调过来
    @PostMapping("payments")
    public Object addPayment(@RequestBody PaymentVO paymentVO){
        Payment payment =new Payment();
        payment.setActualPrice(paymentVO.getActualPrice());
        payment.setPayChannel(paymentVO.getPayChannel());
        payment.setOrderId(paymentVO.getOrderId());
        //调用wx统一支付api获得pay_sn
        // TODO:wx支付返回sn
        String paySn="test";
        payment.setPaySn(paySn);

        return paymentService.addPayment(payment);
    }

    /**
     * 调用此方法去调用order模块的updateOrder方法，修改订单状态
     * @param prepay_id：预支付订单号
     * @return
     *  // TODO:调用order模块的update模块修改订单状态
     *  return true;
     */
    @PutMapping("payments/{id}")
    public Object updatePayment(@PathVariable("id") String prepay_id){
        Integer id=paymentService.findBySn(prepay_id);
        // TODO:调用order模块的update模块修改订单状态
        Payment payment=paymentService.updatePayment(id);
        return payment;
    }
}
