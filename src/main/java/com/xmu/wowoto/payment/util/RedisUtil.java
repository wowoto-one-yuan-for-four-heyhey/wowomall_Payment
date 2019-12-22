package com.xmu.wowoto.payment.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author MedalWill
 * @date 2019/12/14 21:13
 */
@Component
public class RedisUtil {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 读取缓存
     * @param key
     * @return
     */
    public String get(final String key) {
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 写入缓存
     */
    public boolean set(final String key, String value) {
        boolean result = false;
        try {
            redisTemplate.opsForValue().set(key, value);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean getAndSet(final String key, String value) {
        boolean result = false;
        try {
            redisTemplate.opsForValue().getAndSet(key, value);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    public boolean hasKey(final String key) {
        if( redisTemplate.hasKey(key))
        {
            return true;
        }
        return false;
    }

    /**
     * 删除缓存
     * */
    public boolean delete(final String key) {
        boolean result = false;
        try {
            redisTemplate.delete(key);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean addpayment(final String key, long time) {
        boolean result = false;
        try {
            redisTemplate.opsForValue().set(key, "1", time, TimeUnit.MILLISECONDS);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
