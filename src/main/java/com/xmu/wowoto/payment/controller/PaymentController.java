package com.xmu.wowoto.payment.controller;

import com.xmu.wowoto.payment.domain.Payment;
import com.xmu.wowoto.payment.service.OrderService;
import com.xmu.wowoto.payment.service.PaymentService;
import com.xmu.wowoto.payment.service.WxPaymentService;
import com.xmu.wowoto.payment.util.JacksonUtil;
import com.xmu.wowoto.payment.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * PaymentController
 *
 * @author Zach
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

        if (inPayment.getActualPrice().intValue() >= 0){
            Payment payment = new Payment();
            payment.setActualPrice(inPayment.getActualPrice());
            payment.setOrderId(inPayment.getOrderId());
            payment.setPayChannel(inPayment.getPayChannel());
            payment.setBeginTime(inPayment.getBeginTime());
            payment.setEndTime(inPayment.getEndTime());

            String prepayId = JacksonUtil.parseString(wxPaymentService.useWxPay(payment),"data");
            payment.setPaySn(prepayId);

            Integer paymentId = paymentService.addPayment(payment);
            if(paymentId == 0){return ResponseUtil.addPaymentFailed();}

            Payment retPayment = paymentService.getPayment(payment.getId());
            if(retPayment == null) { return ResponseUtil.getPaymentByPaymentIdFailed(); }

            return ResponseUtil.ok(retPayment);
        }
        else{
            // 如果actualPrice < 0, 则此次方法调用为退款调用
            Payment payment = new Payment();
            // id: 由数据库自动生成
            payment.setActualPrice(inPayment.getActualPrice());
            // payChannel
            Integer OrderId = inPayment.getOrderId();

            List<Payment> tempPaymentList = paymentService.getPaymentByOrderId(OrderId);
            int tempPaymentListSize;
            tempPaymentListSize = tempPaymentList.size();
            Payment tempPayment=new Payment();
            if(tempPaymentListSize==0){
                ResponseUtil.fail();
            }
            if(tempPaymentListSize == 1){
                tempPayment = tempPaymentList.get(0);
            }
            if(tempPaymentListSize == 2){
                if(tempPaymentList.get(0).getBeginTime().isBefore(tempPaymentList.get(1).getBeginTime())){
                    tempPayment = tempPaymentList.get(1);
                }
                else {
                    tempPayment = tempPaymentList.get(0);
                }
            }
            payment.setPayChannel(tempPayment.getPayChannel());
            // beSuccessful: 由数据库创建，若退款成功，refund方法调用updatePayment方法，updatePayment方法对其更新

            // payTime: wxpayment模块的refund()函数调用payment模块的updatePayment()方法修改
            // orderId:
            payment.setOrderId(OrderId);
            // paySn

            String prepayId = wxPaymentService.useWxPay(payment);
            payment.setPaySn(prepayId);
            payment.setPayChannel(inPayment.getPayChannel());
            payment.setBeginTime(inPayment.getBeginTime());
            // beginTime: 由数据库创建
            // endTime: 由数据库创建

            // gmtCreate: 由数据库创建
            // gmtModified: 由数据库创建
            // beDeleted: 由数据库创建

            Integer ret1 = paymentService.addPayment(payment);
            if(ret1 == 0){return ResponseUtil.addPaymentFailed();}

            Payment retPayment = paymentService.getPayment(payment.getId());
            if(retPayment == null) { return ResponseUtil.getPaymentByPaymentIdFailed(); }

            // 至此，完成创建订单操作
            // 调用wxPayment模块refund方法
            Object wxPayment = wxPaymentService.refund(tempPayment.getPaySn(), retPayment.getPaySn(), retPayment.getActualPrice());
            /* order */
            return ResponseUtil.ok(retPayment);
        }

    }

    /**prepayId
     *
     * @param paymentId
     * @return
     */
    @PutMapping("payment/{id}")
    public Object payPayment(@PathVariable("id") Integer paymentId){
        Payment payment = paymentService.getPayment(paymentId);
        if(payment == null) { return ResponseUtil.getPaymentByPaymentIdFailed(); }
        // 至此，完成创建订单操作
        // 调用wxPayment模块requestWxPayment方法

        Object wxPayment = wxPaymentService.requestWxPayment(payment.getPaySn(), payment.getEndTime());
        /*wxPaymentService.requestWxPayment 这个模块存在问题*/
        return ResponseUtil.ok(payment);

    }

    /**
     * （模拟的）微信后台调用此方法修改订单状态
     * 此方法还会调用order模块的updateOrder方法，修改订单状态
     * @param prepayId 预支付标识
     * @param successfulPayment 成功
     * @param operationType 操作类型：支付还是退款
     * @return
     */
    @PutMapping("payment/{id}/status")
    public Object updatePayment(@PathVariable("id") String prepayId, boolean successfulPayment, String operationType){
        Payment payment = paymentService.getPaymentByPaySn(prepayId);
        payment.setPayTime(LocalDateTime.now());

        Integer result = paymentService.updatePayment(payment);

        if(result==0){return ResponseUtil.updatePaymentFailed();}
        Payment ret = paymentService.getPayment(payment.getId());
        /* order updateOrderStatus 还没上线 */
        String pay="pay";
        if(operationType.equals(pay))
        {orderService.updateOrderStatus(ret.getOrderId());}

        return ResponseUtil.ok(ret);
    }

    @GetMapping("payment/{id}")
    public Object getPaymentByOrderId(@PathVariable("id") Integer orderId){
        if(orderId <= 0){
            return ResponseUtil.invalidOrderId();
        }
        List<Payment> paymentList = paymentService.getPaymentByOrderId(orderId);
        if(paymentList.size() == 0) { return ResponseUtil.getPaymentsByOrderIdFailed(); }

        return ResponseUtil.ok(paymentList);
    }

}
