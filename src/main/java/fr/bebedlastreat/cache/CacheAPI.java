package fr.bebedlastreat.cache;

import fr.bebedlastreat.cache.data.mysql.SQLManager;
import fr.bebedlastreat.cache.data.redis.RedisManager;

public class CacheAPI {

    public static void set(String key, String value) {
        RedisManager.set(key, value);
    }

    public static void set(String key, int value) {
        RedisManager.set(key, String.valueOf(value));
    }

    public static void set(String key, double value) {
        RedisManager.set(key, String.valueOf(value));
    }

    public static void set(String key, long value) {
        RedisManager.set(key, String.valueOf(value));
    }

    public static void set(String key, float value) {
        RedisManager.set(key, String.valueOf(value));
    }

    public static String get(String key) {
        if (RedisManager.exist(key)) {
            return RedisManager.get(key);
        } else if (SQLManager.exist(key)) {
            String value = SQLManager.get(key);
            RedisManager.set(key, value);
            return value;
        }
        return null;
    }

    public static int getInt(String key) {
        if (RedisManager.exist(key)) {
            return Integer.parseInt(RedisManager.get(key));
        } else if (SQLManager.exist(key)) {
            int value = Integer.parseInt(SQLManager.get(key));
            RedisManager.set(key, String.valueOf(value));
            return value;
        }
        return 0;
    }

    public static double getDouble(String key) {
        if (RedisManager.exist(key)) {
            return Double.parseDouble(RedisManager.get(key));
        } else if (SQLManager.exist(key)) {
            double value = Double.parseDouble(SQLManager.get(key));
            RedisManager.set(key, String.valueOf(value));
            return value;
        }
        return 0;
    }

    public static long getLong(String key) {
        if (RedisManager.exist(key)) {
            return Long.parseLong(RedisManager.get(key));
        } else if (SQLManager.exist(key)) {
            long value = Long.parseLong(SQLManager.get(key));
            RedisManager.set(key, String.valueOf(value));
            return value;
        }
        return 0;
    }

    public static float getFloat(String key) {
        if (RedisManager.exist(key)) {
            return Float.parseFloat(RedisManager.get(key));
        } else if (SQLManager.exist(key)) {
            float value = Float.parseFloat(SQLManager.get(key));
            RedisManager.set(key, String.valueOf(value));
            return value;
        }
        return 0;
    }

    public static boolean keyExist(String key) {
        if (RedisManager.exist(key)) return true;
        return SQLManager.exist(key);
    }

    public static void remove(String key) {
        RedisManager.remove(key);
    }
}
