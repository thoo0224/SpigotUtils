package com.thoo.test;

import com.thoo.spigot.annotations.ConfigSerializable;

@ConfigSerializable
public class TestModel {

    public String name = "Test String";
    public ItemModel item = new ItemModel();

}
