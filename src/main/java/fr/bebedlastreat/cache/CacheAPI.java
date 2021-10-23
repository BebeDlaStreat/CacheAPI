package fr.bebedlastreat.cache;

import fr.bebedlastreat.cache.data.mysql.SQLManager;
import fr.bebedlastreat.cache.data.redis.RedisManager;
import org.bukkit.Bukkit;

public class CacheAPI {

    public static void set(String key, String value) {
        RedisManager.set(key, value);
        /*Bukkit.getScheduler().runTaskAsynchronously(Main.getInstance(), () -> {
            SQLManager.set(key, value);
        });*/
    }

    public static String get(String key) {
        if (RedisManager.exist(key)) {
            return RedisManager.get(key);
        } else if (SQLManager.exist(key)) {
            return SQLManager.get(key);
        }
        return null;
    }

    public static boolean keyExist(String key) {
        if (RedisManager.exist(key)) return true;
        if (SQLManager.exist(key)) return true;
        return false;
    }

    public static void remove(String key) {
        RedisManager.remove(key);
        Bukkit.getScheduler().runTaskAsynchronously(Main.getInstance(), () -> {
            SQLManager.remove(key);
        });
    }

    public static void test() {
        CacheAPI.set("welcome_message", "Welcome on CacheAPI!");
        System.out.println(CacheAPI.get("welcome_message"));
    }
}
