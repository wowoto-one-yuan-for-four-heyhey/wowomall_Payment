package com.xmu.wowoto.payment.controller;

import com.xmu.wowoto.payment.controller.po.OrderPo;
import com.xmu.wowoto.payment.domain.Payment;
import com.xmu.wowoto.payment.service.PaymentService;
import com.xmu.wowoto.payment.service.WxPaymentService;
import com.xmu.wowoto.payment.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

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

    /**
     * 订单模块调用此方法请求下单支付
     * 此方法再调用WxPayment模块的unifiedWxPayment()方法（模拟微信统一支付api），unifiedWxPayment方法应该返回prepay_id等信息
     * 此方法拿到prepay_id等信息后，需要修改数据库中payment表相应属性的属性值，如paySn等等
     * 调用此方法后，前端应显示包括商户名称、订单总价、支付方式(等)信息的界面，供用户确认其支付信息
     * 确认其支付信息后，用户可以点击确认支付按钮调用WxPayment模块的RequestPayment()方法发起最终支付
     *
     * @param orderPo 订单POJO
     * @return Payment
     */
    @PostMapping("payment")
    public Object createPayment(@RequestBody OrderPo orderPo){
        // addPayment

        Payment payment = new Payment();
        payment.setActualPrice(orderPo.getIntegralPrice());
        // payChannel √
        payment.setSuccessful(false);    // √
        // payTime √
        // paySn √
        /* beginTime √ */
        /* endTime √ */
        payment.setOrderId(orderPo.getId());
        /* gmt_create √ */
        /* gmt_modified ! */
        payment.setBeDeleted(false);

        Payment paymentWithId;
        paymentWithId = paymentService.addPayment(payment);

        String prepayId;
        prepayId = wxPaymentService.useWxPay(paymentWithId);

        paymentWithId.setPaySn(prepayId);
        Payment paymentWithIdAndPaySn;
        paymentWithIdAndPaySn = paymentService.updatePayment(paymentWithId);

        return ResponseUtil.ok(paymentWithIdAndPaySn);
    }

    /**
     * （模拟的）微信后台调用此方法修改订单状态
     * 此方法还会调用order模块的updateOrder方法，修改订单状态
     *
     * @param prepayId：预支付订单号
     * @return Payment
     */
    @PutMapping("payment/{id}")
    public Object updatePayment(@PathVariable("id") String prepayId, Integer payChannel, boolean successfulPayment){
        Payment payment = new Payment();
        payment = paymentService.getPaymentByPaySn(prepayId);
        payment.setPayChannel(payChannel);
        if(successfulPayment){
            payment.setSuccessful(true);
        }
        else{
            payment.setSuccessful(false);
        }
        payment.setPayTime(LocalDateTime.now());

        Payment paymentUpdated;
        paymentUpdated = paymentService.updatePayment(payment);

        return ResponseUtil.ok(paymentUpdated);
    }

    /**
     * 管理员删除支付（好像没什么用？）
     *
     * @param paymentId 支付Id
     * @return Payment
     */
    @DeleteMapping("admin/payment/{id}")
    public Object adminDeletePayment(@PathVariable("id") Integer paymentId){
        Payment paymentDeleted;
        paymentDeleted = paymentService.deletePayment(paymentId);
        return ResponseUtil.ok(paymentDeleted);
    }

    /**
     * 管理员查看所有支付（用户好像不用看？）
     *
     * @param
     * @return List<GetPaymentVo>
     */
    @GetMapping("admin/payment")
    public Object adminGetAllPayments(){
        List<Payment> listPayment;
        listPayment = paymentService.getAllPayments();
        return ResponseUtil.ok(listPayment);
    }

    /**
     * 管理员查看某个支付（用户好像不用看？）
     *
     * @param paymentId
     * @return GetPaymentVo
     */
    @GetMapping("admin/payment/{id}")
    public Object adminGetAllPayments(@PathVariable("id") Integer paymentId){
        Payment payment = new Payment();
        payment = paymentService.getPayment(paymentId);
        return ResponseUtil.ok(payment);
    }

}
