package com.xmu.wowoto.payment.controller;

import com.xmu.wowoto.payment.controller.vo.PaymentVO;
import com.xmu.wowoto.payment.domain.Payment;
import com.xmu.wowoto.payment.service.PaymentService;
import com.xmu.wowoto.payment.util.ResponseUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("")
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    //order调过来
    @PostMapping("payments")
    @ApiOperation("下单，获取prepay_id等五个参数，进而鉴权调起支付")
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
