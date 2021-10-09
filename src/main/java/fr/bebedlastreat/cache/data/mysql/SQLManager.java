package fr.bebedlastreat.cache.data.mysql;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import fr.bebedlastreat.cache.Main;

import java.sql.SQLException;
import java.util.List;

public class SQLManager {
    private static final String TABLE = "cacheapi";

    public static boolean exist(String key) {
         return (boolean) Main.getInstance().getMysql().query("SELECT * FROM " + TABLE + " WHERE `key`='" + key + "'", rs -> {
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
            Main.getInstance().getMysql().update("UPDATE " + TABLE + " SET `value`='" +  new Gson().toJson(value) + "' WHERE `key`='" + key + "'");
        } else {
            Main.getInstance().getMysql().update("INSERT INTO " + TABLE + " (`key`, `value`) VALUES ('" + key + "', '" + new Gson().toJson(value) + "')");
        }
    }

    public static void remove(String key) {
        Main.getInstance().getMysql().update("DELETE FROM " + TABLE + " WHERE `key`='" + key + "'");
    }

    public static String get(String key) {
        return (String) Main.getInstance().getMysql().query("SELECT * FROM " + TABLE + " WHERE `key`='" + key + "'", rs -> {
            try {
                return rs.getString("value");
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
        });
    }
}
