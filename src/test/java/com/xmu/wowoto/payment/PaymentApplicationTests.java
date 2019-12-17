package com.xmu.wowoto.payment;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Random;
import java.util.logging.Logger;

@SpringBootTest
class PaymentApplicationTests {

    @Test
    void contextLoads() {

        Integer length = 8;
        String len = " ";
        for(int i = 0 ;i < length ;i++) {
            len += new Random().ints(105);
        }
    }

}
