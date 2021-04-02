package com.thoo.test;

import com.thoo.spigot.annotations.ConfigSerializable;
import com.thoo.spigot.config.Serializable;

@ConfigSerializable
public class ItemModel extends Serializable {

    public int age = 69;
    public double test = 420.0;
    public String name = "Item name";
    public String[] array = new String[] { "test", "test2" };

}
