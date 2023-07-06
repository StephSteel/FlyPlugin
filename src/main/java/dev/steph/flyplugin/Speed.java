package dev.steph.flyplugin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Speed implements CommandExecutor {

    public Speed() {

    }

    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage("Player only command!");
            return false;
        }
        Player p = (Player) commandSender;
        if (!p.hasPermission("flyspeed.speed")) {
            p.sendMessage(Util.Color("&cYou don't have enough permissions!"));
            return false;
        }
        if (strings.length == 0) {
            p.sendMessage(Util.Color("&cUsage: /speed <value>"));
            return false;
        }
        try {
            float value = Float.parseFloat(strings[0]);
            if (value < 0 || value > 10) {
                p.sendMessage(Util.Color("&cInvalid speed value! Speed must be between 0 and 10."));
                return false;
            }
            p.setFlySpeed(value / 10);
            p.sendMessage(Util.Color("&aFlight speed set to &b" + value));
            return true;
        } catch (NumberFormatException e) {
            p.sendMessage(Util.Color("&cInvalid speed value! Speed must be a number."));
            return false;
        }
    }
}
