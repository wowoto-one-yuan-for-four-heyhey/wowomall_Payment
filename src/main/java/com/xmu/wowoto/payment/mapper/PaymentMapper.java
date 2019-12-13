package com.xmu.wowoto.payment.mapper;

import com.xmu.wowoto.payment.domain.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface PaymentMapper {

    int addPayment(Payment payment);
    int deletePayment(Integer id);
    int updatePayment(Payment payment);
    Payment getPayment(Integer id);
    Payment getPaymentByPaySn(String paySn);
    List<Payment> getAllPayments();

}
