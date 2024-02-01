package org.litterwhite.ffxivUtil.System.Service.Imp;

import org.litterwhite.ffxivUtil.System.Service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class RedisServiceImp implements RedisService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public void saveToRedis(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    @Override
    public String getFromRedis(String key) {
        return redisTemplate.opsForValue().get(key);
    }

}
