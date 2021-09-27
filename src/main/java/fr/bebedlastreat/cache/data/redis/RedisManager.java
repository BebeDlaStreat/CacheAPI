package fr.bebedlastreat.cache.data.redis;

import org.redisson.api.RedissonClient;

public class RedisManager {
    private static final String PREFIX = "cacheapi_";

    public static boolean keyExist(String key) {
        RedissonClient redissonClient = RedisAccess.instance.getRedissonClient();
        return redissonClient.getBucket(PREFIX + key).isExists();
    }

    public static void setKey(String key, Object value) {
        RedissonClient redissonClient = RedisAccess.instance.getRedissonClient();
        redissonClient.getBucket(PREFIX + key).set(value);
    }

    public static void removeKey(String key) {
        RedissonClient redissonClient = RedisAccess.instance.getRedissonClient();
        redissonClient.getBucket(PREFIX + key).delete();
    }

    public static <T> T getKey(String key, Class<T> classOfT) {
        RedissonClient redissonClient = RedisAccess.instance.getRedissonClient();
        return (T) redissonClient.getBucket(PREFIX + key).get();
    }
}
