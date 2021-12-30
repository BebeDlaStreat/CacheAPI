package fr.bebedlastreat.cache.data.mysql;

import com.google.gson.Gson;

import java.sql.SQLException;

public class SQLManager {
    private static final String TABLE = "cacheapi";

    public static boolean exist(String key) {
         return (boolean) getMysql().query("SELECT * FROM " + TABLE + " WHERE `key`='" + key + "'", rs -> {
            try {
               return rs.next();
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        });
    }

    public static void set(String key, String value) {
        if (exist(key)) {
            getMysql().update("UPDATE " + TABLE + " SET `value`='" +  new Gson().toJson(value) + "' WHERE `key`='" + key + "'");
        } else {
            getMysql().update("INSERT INTO " + TABLE + " (`key`, `value`) VALUES ('" + key + "', '" + new Gson().toJson(value) + "')");
        }
    }

    public static void remove(String key) {
        getMysql().update("DELETE FROM " + TABLE + " WHERE `key`='" + key + "'");
    }

    public static String get(String key) {
        return (String) getMysql().query("SELECT * FROM " + TABLE + " WHERE `key`='" + key + "'", rs -> {
            try {
                return rs.getString("value");
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
        });
    }

    private static MySQL getMysql() {
        return MySQL.getInstance();
    }
}
