package fr.bebedlastreat.cache.data.redis;

import fr.bebedlastreat.cache.data.mysql.SQLManager;
import redis.clients.jedis.Jedis;

public class RedisAccess {
    private Jedis jedis;
    private static RedisAccess instance;

    public Jedis getJedis() {
        return jedis;
    }

    public static RedisAccess getInstance() {
        return instance;
    }

    public static Jedis get() {
        return RedisAccess.getInstance().getJedis();
    }

    public RedisAccess(String host, String password, int port) {
        instance = this;
        jedis = new Jedis(host, port);
        jedis.auth(password);
    }

    public static void init(String host, String password, int port) {
        new RedisAccess(host, password, port);
    }

    public static void close() {
        RedisAccess.instance.getJedis().close();
    }

    public static void sendToDatabase() {
        Jedis jedis = RedisAccess.get();
        for (String key : jedis.keys(RedisManager.PREFIX + "*")) {
            String s = key.substring(RedisManager.PREFIX.length());
            SQLManager.set(s, RedisManager.get(s));
        }
    }
}
