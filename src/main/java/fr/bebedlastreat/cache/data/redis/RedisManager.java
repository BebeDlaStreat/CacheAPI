package fr.bebedlastreat.cache.data.redis;

public class RedisManager {
    public static final String PREFIX = "cacheapi/";

    public static boolean exist(String key) {
        return RedisAccess.get().exists(PREFIX + key);
    }

    public static void set(String key, String value) {
        RedisAccess.get().set(PREFIX + key, value);
    }

    public static void remove(String key) {
        RedisAccess.get().del(PREFIX + key);
    }

    public static String get(String key) {
        return RedisAccess.get().get(PREFIX + key);
    }
}
