package com.thoo.spigot.item;

import com.thoo.spigot.chat.ChatUtils;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public final class PlayerSkullBuilder {

    private String owner;
    private String title;
    private String[] lores;

    public PlayerSkullBuilder setOwner(String owner) {
        this.owner = owner;
        return this;
    }

    public PlayerSkullBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public PlayerSkullBuilder setLores(String... lores) {
        this.lores = lores;
        return this;
    }

    public ItemStack build(int amount) {
        ItemStack item = new ItemStack(Material.SKULL_ITEM, amount, (short) 3);
        SkullMeta meta = (SkullMeta) item.getItemMeta();
        if(title != null) {
            meta.setDisplayName(ChatUtils.colorize(title));
        }

        if(lores != null && lores.length > 0) {
            List<String> finalLores = Arrays.stream(lores)
                    .map(ChatUtils::colorize)
                    .collect(Collectors.toList());
            meta.setLore(finalLores);
        }

        meta.setOwner(owner);
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack build() {
        return build(1);
    }

}
