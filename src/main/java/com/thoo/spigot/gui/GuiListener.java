package com.thoo.spigot.gui;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.InventoryHolder;

public final class GuiListener implements Listener {

    @EventHandler(ignoreCancelled = true)
    public void onClick(InventoryClickEvent e){
        InventoryHolder holder = e.getInventory().getHolder();
        if (!(holder instanceof Gui)) {
            return;
        }

        e.setCancelled(((Gui) holder).onClick((Player) e.getWhoClicked(), e.getRawSlot(), e.getClick()));
    }

    @EventHandler
    public void onOpen(InventoryOpenEvent e) {
        InventoryHolder holder = e.getInventory().getHolder();
        if (!(holder instanceof Gui)) {
            return;
        }

        ((Gui) holder).onOpen((Player) e.getPlayer());
    }

    @EventHandler
    public void onClose(InventoryCloseEvent e) {
        final InventoryHolder holder = e.getInventory().getHolder();
        if (!(holder instanceof Gui)) {
            return;
        }

        ((Gui) holder).onClose((Player) e.getPlayer());
    }

}
