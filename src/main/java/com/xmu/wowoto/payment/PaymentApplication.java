package com.xmu.wowoto.payment;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 *
*@author MedalWill
*@date 2019/12/14
*
*/
@EnableEurekaClient
@EnableFeignClients
@SpringBootApplication
@MapperScan("com.xmu.wowoto.payment.mapper")
public class PaymentApplication {

    public static void main(String[] args) {
        SpringApplication.run(PaymentApplication.class, args);
    }

}
