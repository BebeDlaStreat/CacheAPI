package fr.bebedlastreat.cache;

import fr.bebedlastreat.cache.data.mysql.MySQL;
import fr.bebedlastreat.cache.data.redis.RedisAccess;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;
import org.apache.commons.dbcp2.BasicDataSource;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class Bungee extends Plugin {
    private static Bungee instance;

    private BasicDataSource connectionPool;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        initConnection();
        RedisAccess.init(getConfig().getString("redis.host"), getConfig().getString("redis.password"), getConfig().getInt("redis.port"));
        super.onEnable();
    }

    private void saveDefaultConfig() {
        if (!getDataFolder().exists()) getDataFolder().mkdir();
        File file = new File(this.getDataFolder(), "config.yml");
        try {
            if (!file.exists())
                Files.copy(getResourceAsStream("config.yml"), file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }

    public static Bungee getInstance() {
        return instance;
    }

    private void initConnection(){
        connectionPool = new BasicDataSource();
        connectionPool.setDriverClassName("com.mysql.jdbc.Driver");
        connectionPool.setUsername(getConfig().getString("mysql.user"));
        connectionPool.setPassword(getConfig().getString("mysql.password"));
        connectionPool.setUrl("jdbc:mysql://" + getConfig().getString("mysql.host") + ":" + getConfig().getString("mysql.port") + "/" + getConfig().getString("mysql.database") + "?autoReconnect=true");
        connectionPool.setInitialSize(1);
        connectionPool.setMaxTotal(10);
        new MySQL(connectionPool).createTables();
    }

    private Configuration getConfig() {
        try {
            return ConfigurationProvider.getProvider(YamlConfiguration.class).load(new File(this.getDataFolder(), "config.yml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
