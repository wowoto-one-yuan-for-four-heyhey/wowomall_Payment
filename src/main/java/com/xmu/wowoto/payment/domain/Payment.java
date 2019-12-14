package com.xmu.wowoto.payment.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 *
*@author MedalWill
*@date 2019/12/14
*
*/

@EqualsAndHashCode
@ToString
@Setter
@Getter
@Alias("payment")
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
     * 是否付款成功,0不成功，1成功
     */
    private Boolean beSuccessful;
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

}