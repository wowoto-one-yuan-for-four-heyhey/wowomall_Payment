package com.xmu.wowoto.payment.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

/**
 * @author MedalWill
 * @date 2019/12/14 21:13
 */

@FeignClient("orderService")
public interface OrderService {
    
/**
 *修改订单状态为完成
*@author MedalWill
*@date 2019/12/14
*
*@param  id 订单id
*@return Object(OrderPo+ok)
*/
    @PutMapping("orders/{id}/paymentStatus")
    Object updateOrderStatus(@PathVariable("id") Integer id);

    @PutMapping("orders/{id}/autoCancellation")
    Object autoCancel(@PathVariable Integer id);

}
