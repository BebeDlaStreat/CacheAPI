package fr.bebedlastreat.cache;

import fr.bebedlastreat.cache.commands.DataCommand;
import fr.bebedlastreat.cache.data.mysql.MySQL;
import fr.bebedlastreat.cache.data.redis.RedisAccess;
import org.apache.commons.dbcp2.BasicDataSource;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    private static Main instance;

    private BasicDataSource connectionPool;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        initConnection();
        RedisAccess.init(getConfig().getString("redis.host"), getConfig().getString("redis.password"), getConfig().getInt("redis.port"));

        getCommand("data").setExecutor(new DataCommand());

        if (getConfig().getBoolean("auto-save")) {
            Bukkit.getScheduler().runTaskTimerAsynchronously(this, RedisAccess::sendToDatabase, getConfig().getLong("auto-save-interval"), getConfig().getLong("auto-save-interval"));
        }
        super.onEnable();
    }

    @Override
    public void onDisable() {
        if (getConfig().getBoolean("save-on-stop")) {
            RedisAccess.sendToDatabase();
        }
        RedisAccess.close();
        super.onDisable();
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


    public static Main getInstance() {
        return instance;
    }
}
