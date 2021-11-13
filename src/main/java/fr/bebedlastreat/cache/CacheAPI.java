package fr.bebedlastreat.cache;

import fr.bebedlastreat.cache.data.mysql.SQLManager;
import fr.bebedlastreat.cache.data.redis.RedisManager;
import org.bukkit.Bukkit;

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
            return SQLManager.get(key);
        }
        return null;
    }

    public static int getInt(String key) {
        if (RedisManager.exist(key)) {
            return Integer.parseInt(RedisManager.get(key));
        } else if (SQLManager.exist(key)) {
            return Integer.parseInt(SQLManager.get(key));
        }
        return 0;
    }

    public static double getDouble(String key) {
        if (RedisManager.exist(key)) {
            return Double.parseDouble(RedisManager.get(key));
        } else if (SQLManager.exist(key)) {
            return Double.parseDouble(SQLManager.get(key));
        }
        return 0;
    }

    public static long getLong(String key) {
        if (RedisManager.exist(key)) {
            return Long.parseLong(RedisManager.get(key));
        } else if (SQLManager.exist(key)) {
            return Long.parseLong(SQLManager.get(key));
        }
        return 0;
    }

    public static float getFloat(String key) {
        if (RedisManager.exist(key)) {
            return Float.parseFloat(RedisManager.get(key));
        } else if (SQLManager.exist(key)) {
            return Float.parseFloat(SQLManager.get(key));
        }
        return 0;
    }

    public static boolean keyExist(String key) {
        if (RedisManager.exist(key)) return true;
        return SQLManager.exist(key);
    }

    public static void remove(String key) {
        RedisManager.remove(key);
        Bukkit.getScheduler().runTaskAsynchronously(Main.getInstance(), () -> SQLManager.remove(key));
    }
}
