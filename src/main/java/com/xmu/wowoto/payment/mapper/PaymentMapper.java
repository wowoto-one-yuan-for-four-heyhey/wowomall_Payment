package com.xmu.wowoto.payment.mapper;

import com.xmu.wowoto.payment.domain.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


@Repository
@Mapper
public interface PaymentMapper {
    /**
     * 新建支付
    *@author MedalWill
    *@date 2019/12/14
    *
    *@param payment 支付对象
    *@return int 是否成功标识
    */
    int addPayment(Payment payment);
    /**
     * 删除支付
    *@author MedalWill
    *@date 2019/12/14
    *
    *@param id 支付id
    *@return int 是否成功标识
    */
    int deletePayment(Integer id);
    /**
     * 更新支付
    *@author MedalWill
    *@date 2019/12/14
    *
    *@param payment 更新的对象
    *@return int 是否成功标识
    */
    int updatePayment(Payment payment);
    /**
     * 获取支付
    *@author MedalWill
    *@date 2019/12/14
    *
    *@param id 支付id
    *@return Payment对象
    */
    Payment getPayment(Integer id);
    /**
     * 根据支付标识获取支付
    *@author MedalWill
    *@date 2019/12/14
    *
    *@param paySn 支付标识
    *@return Payment对象
    */
    Payment getPaymentByPaySn(String paySn);


}
