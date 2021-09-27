package fr.bebedlastreat.cache.data.redis;

import fr.bebedlastreat.cache.Main;
import org.bukkit.configuration.file.FileConfiguration;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.codec.JsonJacksonCodec;
import org.redisson.config.Config;

import java.util.Iterator;

public class RedisAccess {
    private RedissonClient redissonClient;
    public static RedisAccess instance;

    public RedissonClient getRedissonClient() {
        return redissonClient;
    }

    public RedisAccess(RedisCredentials redisCredentials) {
        instance = this;
        this.redissonClient = initRedisson(redisCredentials);
    }

    public static void init() {
        FileConfiguration config = Main.getInstance().getConfig();
        new RedisAccess(new RedisCredentials(config.getString("redis.host"), config.getString("redis.password"), config.getInt("redis.port")));
    }

    public static void close() {
        RedisAccess.instance.getRedissonClient().shutdown();
    }

    public static void sendToDatabase() {
        RedissonClient redissonClient = RedisAccess.instance.getRedissonClient();
        Iterator<String> iterator = redissonClient.getKeys().getKeys().iterator();
        while (iterator.hasNext()) {
            /**
             * todo save key
             */
        }
    }

    public RedissonClient initRedisson(RedisCredentials redisCredentials) {
        final Config config = new Config();
        config.setCodec(new JsonJacksonCodec());
        config.setThreads(6);
        config.setNettyThreads(6);
        config.useSingleServer()
                .setAddress(redisCredentials.toRedisUrl())
                .setPassword(redisCredentials.getPassword())
                .setDatabase(0)
                .setClientName(redisCredentials.getClientName());
        return Redisson.create(config);
    }
}
