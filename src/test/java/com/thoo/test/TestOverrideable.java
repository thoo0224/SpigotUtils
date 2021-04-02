package com.thoo.test;

import com.thoo.spigot.config.ConfigSerializationOverrideable;
import com.thoo.spigot.config.CustomConfiguration;

import java.lang.reflect.Field;

public class TestOverrideable implements ConfigSerializationOverrideable<ItemModel> {

    @Override
    public void serialize(String key, ItemModel type, CustomConfiguration config) {

    }

    @Override
    public ItemModel deserialize(CustomConfiguration config) {
        return null;
    }

}
