package com.thoo.spigot.gui;

import com.thoo.spigot.chat.ChatUtils;
import com.thoo.spigot.item.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

public final class GuiExample implements Gui {

    private final Inventory inventory = Bukkit.createInventory(this, 54, ChatUtils.colorize("&c&lCool GUI"));

    @Override
    public boolean onClick(Player player, int slot, ClickType type) {
        switch(slot) {
            case 0:
                player.sendMessage(ChatUtils.colorize("&cYou clicked the cool sword!"));
                break;
        }
        return true;
    }

    @Override
    public void onOpen(Player player) {
        ItemStack sword = new ItemBuilder(Material.DIAMOND_SWORD)
                .setDisplayName("&cSword")
                .setLores("", "&cCool sword")
                .modifyMeta(meta -> {
                    meta.addEnchant(Enchantment.DURABILITY, 1, true);
                })
                .setItemFlags(ItemFlag.HIDE_ENCHANTS)
                .build();
        inventory.setItem(0, sword);
    }

    @Override
    public Inventory getInventory() {
        return inventory;
    }

}
