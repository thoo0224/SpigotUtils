package com.thoo.spigot.config.adapters;

import com.thoo.spigot.config.ConfigTypeAdapter;
import com.thoo.spigot.config.CustomConfiguration;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;

public final class LocationTypeAdapter implements ConfigTypeAdapter<Location> {

    @Override
    public void serialize(String key, Location location, CustomConfiguration config) {
        ConfigurationSection section = config.getConfigurationSection(key);
        section.set("world", location.getWorld().getName());
        section.set("x", location.getX());
        section.set("y", location.getY());
        section.set("z", location.getZ());
        section.set("pitch", location.getPitch());
        section.set("yaw", location.getYaw());
    }

    @Override
    public Location deserialize(CustomConfiguration config) {
        return null;
    }

}
