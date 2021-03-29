package com.thoo.spigot.config;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.logging.Level;

public final class CustomConfiguration extends YamlConfiguration {

    private final JavaPlugin plugin;
    private final File file;
    private final String fileName;

    public CustomConfiguration(JavaPlugin plugin, String fileName) {
        this.fileName = fileName;
        this.plugin = plugin;
        this.file = new File(plugin.getDataFolder(), fileName);
    }

    public void initialize() {
        if(!file.exists()) {
            file.getParentFile().mkdirs();
            plugin.saveResource(fileName, false);
        }

        try {
            load(file);
        } catch(Exception e) {
            plugin.getLogger().log(Level.SEVERE, "Error occurred when loading configuration file " + fileName, e);
        }
    }

}
