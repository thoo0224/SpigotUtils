package com.thoo.test;

import com.thoo.spigot.annotations.ConfigSerializable;
import com.thoo.spigot.config.Serializable;

@ConfigSerializable
public class TestModel extends Serializable {

    public String name = "Test String";
    public ItemModel item = new ItemModel();

}
