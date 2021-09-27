package fr.bebedlastreat.cache.data.redis;

public class RedisCredentials {

    public RedisCredentials(String ip, String password, int port, String clientName) {
        this.ip = ip;
        this.password = password;
        this.port = port;
        this.clientName = clientName;
    }

    public RedisCredentials(String ip, String password, int port) {
        this(ip, password, port, "Redis_bungee_access");
    }

    public String getIp() {
        return ip;
    }

    public String getPassword() {
        return password;
    }

    public int getPort() {
        return port;
    }

    public String getClientName() {
        return clientName;
    }

    private String ip;
    private String password;
    private int port;
    private String clientName;

    public String toRedisUrl() {
        return ip + ":" + port;
    }
}
