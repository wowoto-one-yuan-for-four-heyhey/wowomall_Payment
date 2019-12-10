package com.xmu.wowoto.payment.mapper;

import com.xmu.wowoto.payment.domain.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface PaymentMapper {
    int addPayment(Payment payment);
    Payment findPayment(Integer id);
    int updatePayment(Integer id);
    int deletePayment(Integer id);
    Integer findPaymentBySn(String paySn);
}
