package com.thoo.spigot.gui;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.InventoryHolder;

public interface Gui extends InventoryHolder {

    /**
     *
     * @param player The player who clicked
     * @param slot Clicked item slot
     * @param type Click type
     * @return If the event should be cancelled (if returned true players can not modify the inventory)
     */
    default boolean onClick(Player player, int slot, ClickType type) { return true; }
    default void onOpen(Player player) { }
    default void onClose(Player player) { }

}
