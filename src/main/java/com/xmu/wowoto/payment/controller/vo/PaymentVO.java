package com.xmu.wowoto.payment.controller.vo;

import java.math.BigDecimal;

public class PaymentVO {

    private BigDecimal actualPrice;

    private Integer payChannel;

    private Integer orderId;

    @Override
    public String toString() {
        return "PaymentVO{" +
                "actualPrice=" + actualPrice +
                ", payChannel=" + payChannel +
                ", orderId=" + orderId +
                '}';
    }

    public void setActualPrice(BigDecimal actualPrice) {
        this.actualPrice = actualPrice;
    }

    public BigDecimal getActualPrice() {
        return actualPrice;
    }

    public void setPayChannel(Integer payChannel) {
        this.payChannel = payChannel;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }


    public Integer getPayChannel() {
        return payChannel;
    }

    public Integer getOrderId() {
        return orderId;
    }
}
