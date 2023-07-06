package dev.steph.flyplugin;

import org.bukkit.ChatColor;

public class Util {

    public static String Color(String s) {
        if (s == null) return "";
        return ChatColor.translateAlternateColorCodes('&', s);
    }
}
