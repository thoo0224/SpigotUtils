package com.thoo.spigot.config;

public interface ConfigTypeAdapter<T> {

    void serialize(String key, T type, CustomConfiguration config);

    T deserialize(CustomConfiguration config);

}
