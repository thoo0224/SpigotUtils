package com.thoo.spigot.config;

import java.lang.reflect.Field;

public interface ConfigSerializationOverrideable<T> {

    void serialize(String key, T type, CustomConfiguration config);

    T deserialize(CustomConfiguration config);

}
