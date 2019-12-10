package com.xmu.wowoto.payment.controller;

import com.xmu.wowoto.payment.util.ResponseUtil;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController("")
public class PaymentController {


    @PostMapping("payments")
    @ApiOperation("下单，获取prepay_id等五个参数，进而鉴权调起支付")
    public Object addPayment(@ApiParam(name="orderId",value="商户订单号",required=true) Integer orderId){
        Map<String, Object> addPaymentRet = new HashMap<String, Object>();
        return ResponseUtil.ok(addPaymentRet);
    }

    /**
     * 调用此方法去调用order模块的updateOrder方法，修改订单状态
     * @param prepay_id：预支付订单号
     * @return
     *  // TODO:调用order模块的update模块修改订单状态
     *  return true;
     */
    @GetMapping("payments/{id}")
    public boolean updatePayment(@PathVariable("id") Integer prepay_id){
           // TODO:调用order模块的update模块修改订单状态
       return true;
    }
}
