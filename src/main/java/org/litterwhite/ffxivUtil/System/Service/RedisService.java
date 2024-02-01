package org.litterwhite.ffxivUtil.System.Service;

public interface RedisService {
    void saveToRedis(String key, String value);

    String getFromRedis(String key);
}
