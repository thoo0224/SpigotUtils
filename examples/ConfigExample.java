package com.thoo.spigot;

import com.thoo.spigot.config.CustomConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class Plugin extends JavaPlugin {

    private CustomConfiguration messageConfig;

    @Override
    public void onEnable() {
        messageConfig = new CustomConfiguration(this, "messages.yml");
        String message = messageConfig.getString("some-message");
        // You can use every method you would normally use.
    }

}
