package com.xmu.wowoto.payment.mapper;

import com.xmu.wowoto.payment.domain.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface PaymentMapper {

    int addPayment(Payment payment);
    Payment getPayment(Integer id);
    Payment getPaymentByPaySn(String paySn);
    int updatePayment(Payment payment);
    int deletePayment(Integer id);

}
