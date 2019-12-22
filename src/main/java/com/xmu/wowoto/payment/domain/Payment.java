package com.xmu.wowoto.payment.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @Author: 数据库与对象模型标准组
 * @Description: 付款记录
 * @Date: Created in 16:00 2019/12/11
 **/
public class Payment {



    private Integer id;
    /**
     * 付款金额
     */
    private BigDecimal actualPrice;
    /**
     * 付款渠道，微信，银行等等
     */
    private Integer payChannel;
    /**
     * 付款状态,0未支付，1成功，2失败
     */
    private Integer statusCode;
    /**
     * 付款时间
     */
    private LocalDateTime payTime;
    /**
     * 付款编号，从支付渠道获得的
     */
    private String paySn;
    /**
     * 付款的开始时间 （在开始时间和结束时间之间才可以付款）
     */
    private LocalDateTime beginTime;
    /**
     * 付款的结束时间
     */
    private LocalDateTime endTime;
    /**
     * 订单Id
     */
    private Integer orderId;
    private LocalDateTime gmtCreate;
    private LocalDateTime gmtModified;
    private Boolean beDeleted;

    public Integer getId() {
        return this.id;
    }

    public BigDecimal getActualPrice() {
        return this.actualPrice;
    }

    public Integer getPayChannel() {
        return this.payChannel;
    }

    public Integer getStatusCode() {
        return this.statusCode;
    }

    public LocalDateTime getPayTime() {
        return this.payTime;
    }

    public String getPaySn() {
        return this.paySn;
    }

    public LocalDateTime getBeginTime() {
        return this.beginTime;
    }

    public LocalDateTime getEndTime() {
        return this.endTime;
    }

    public Integer getOrderId() {
        return this.orderId;
    }

    public LocalDateTime getGmtCreate() {
        return this.gmtCreate;
    }

    public LocalDateTime getGmtModified() {
        return this.gmtModified;
    }

    public Boolean getBeDeleted() {
        return this.beDeleted;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setActualPrice(BigDecimal actualPrice) {
        this.actualPrice = actualPrice;
    }

    public void setPayChannel(Integer payChannel) {
        this.payChannel = payChannel;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public void setPayTime(LocalDateTime payTime) {
        this.payTime = payTime;
    }

    public void setPaySn(String paySn) {
        this.paySn = paySn;
    }

    public void setBeginTime(LocalDateTime beginTime) {
        this.beginTime = beginTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public void setGmtCreate(LocalDateTime gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public void setGmtModified(LocalDateTime gmtModified) {
        this.gmtModified = gmtModified;
    }

    public void setBeDeleted(Boolean beDeleted) {
        this.beDeleted = beDeleted;
    }

    @Override
    public boolean equals(final Object o) {
      return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Payment;
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public String toString() {
        return "Payment(id=" + this.getId() + ", actualPrice=" + this.getActualPrice() + ", payChannel=" + this.getPayChannel() + ", statusCode=" + this.getStatusCode() + ", payTime=" + this.getPayTime() + ", paySn=" + this.getPaySn() + ", beginTime=" + this.getBeginTime() + ", endTime=" + this.getEndTime() + ", orderId=" + this.getOrderId() + ", gmtCreate=" + this.getGmtCreate() + ", gmtModified=" + this.getGmtModified() + ", beDeleted=" + this.getBeDeleted() + ")";
    }
}