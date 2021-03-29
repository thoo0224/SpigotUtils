package com.thoo.spigot.chat;

import com.thoo.spigot.item.ItemBuilder;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public final class ChatUtils {

    private ChatUtils() {
        ItemStack item = new ItemBuilder(Material.AIR)
                .setDisplayName("")
                .setLores("Lore1", "Lore2")
                .build();
    }

    public static String colorize(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }

}
