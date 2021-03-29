package com.thoo.spigot.item;

import com.thoo.spigot.chat.ChatUtils;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public final class ItemBuilder {

    private final Material material;
    private final byte data;

    private Consumer<ItemMeta> metaConsumer;
    private ItemFlag[] itemFlags;
    private String displayName;
    private String[] lores;
    private int amount;

    public ItemBuilder(Material material) {
        this(material, (byte) 0);
    }

    public ItemBuilder(Material material, byte data) {
        this.material = material;
        this.data = data;
        this.amount = 0;
    }

    public ItemBuilder setDisplayName(String displayName) {
        this.displayName = displayName;
        return this;
    }

    public ItemBuilder setAmount(int amount) {
        this.amount = amount;
        return this;
    }

    public ItemBuilder setLores(String... lores) {
        this.lores = lores;
        return this;
    }

    public ItemBuilder setItemFlags(ItemFlag... itemFlags) {
        this.itemFlags = itemFlags;
        return this;
    }

    /**
     * This is for if the builder is missing something so you don't need to get and set the item meta.
     * @param metaConsumer gets invoked when the item gets built
     * @return ItemBuilder
     */
    public ItemBuilder modifyMeta(Consumer<ItemMeta> metaConsumer) {
        this.metaConsumer = metaConsumer;
        return this;
    }

    public ItemStack build() {
        ItemStack item = new ItemStack(material, amount, data);
        ItemMeta meta = item.getItemMeta();
        if(displayName != null) {
            meta.setDisplayName(ChatUtils.colorize(displayName));
        }

        if(lores != null) {
            List<String> finalLores = Arrays.stream(lores)
                    .map(ChatUtils::colorize)
                    .collect(Collectors.toList());
            meta.setLore(finalLores);
        }

        if(itemFlags != null) {
            meta.addItemFlags(itemFlags);
        }

        if (metaConsumer != null) {
            metaConsumer.accept(meta);
        }

        item.setItemMeta(meta);
        return item;
    }

}
