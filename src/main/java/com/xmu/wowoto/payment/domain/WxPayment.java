package com.xmu.wowoto.payment.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class WxPayment {

    private String prepayId;
    private Payment payment;

}
