package dev.steph.flyplugin;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class Main extends JavaPlugin {

    public void onEnable() {
        getCommand("fly").setExecutor(new FlyPlugin());
        getCommand("speed").setExecutor(new Speed());
    }

    /**
     * Creates an ItemStack based on the provided itemStack string.
     * Format: "material:amount;name:display_name;opis:lore_line1##lore_line2"
     */
    public static ItemStack createItemStack(String itemStack) {
        ItemStack is = new ItemStack(Material.AIR);
        String[] strings = itemStack.split(";");
        String[] item = strings[0].split(":");
        if (item.length > 1) {
            Material m = Material.getMaterial(item[0]);
            if (m != null) {
                is.setType(m);
                is.setAmount(Integer.parseInt(item[1]));
            }
        } else {
            Material m = Material.getMaterial(item[0]);
            if (m != null) {
                is.setType(m);
            }
        }

        ItemMeta itemMeta = is.getItemMeta();
        if (itemMeta != null) {
            for (int i = 1; i < strings.length; i++) {
                String[] keyValue = strings[i].split(":");
                if (keyValue.length == 2) {
                    String key = keyValue[0];
                    String value = keyValue[1].replace("_", " ");

                    if (key.equalsIgnoreCase("name")) {
                        itemMeta.setDisplayName(value);
                    } else if (key.equalsIgnoreCase("opis")) {
                        String[] loreLines = value.split("##");
                        List<String> lore = new ArrayList<>();
                        for (String loreLine : loreLines) {
                            lore.add(loreLine);
                        }
                        itemMeta.setLore(lore);
                    }
                }
            }
            is.setItemMeta(itemMeta);
        }
        return is;
    }
}
