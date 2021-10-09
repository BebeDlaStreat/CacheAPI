package fr.bebedlastreat.cache.commands;

import fr.bebedlastreat.cache.CacheAPI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.io.IOException;

public class DataCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length < 2) {
            sender.sendMessage("/data get {key}\n/data get/set {key} {value}");
            return false;
        }
        String key = args[1];
        if (args[0].equalsIgnoreCase("get")) {
            if (CacheAPI.keyExist(key)) {
                sender.sendMessage(CacheAPI.get(key));
            } else {
                sender.sendMessage("§cCette clé n'existe pas");
            }
            return true;
        }
        if (args[0].equalsIgnoreCase("set")) {
            if (args.length < 3) {
                sender.sendMessage("/data set {key} [value]");
                return false;
            }
            CacheAPI.set(key, args[2]);
            sender.sendMessage(key + " -> " + args[2]);
            return true;
        }
        return false;
    }
}
