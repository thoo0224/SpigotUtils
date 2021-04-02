package com.thoo.test;

import com.thoo.spigot.annotations.ConfigSerializable;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

public class Test {

    private static final HashSet<Class<?>> TYPE_SET;
    static {
        TYPE_SET = new HashSet<>(16);
        addAllTypes(Integer.class, int.class,
                Byte.class, byte.class,
                Character.class, char.class,
                Boolean.class, boolean.class,
                Double.class, double.class,
                Float.class, float.class,
                Long.class, long.class,
                Short.class, short.class,
                String.class);
    }

    public static void main(String[] args) throws IllegalAccessException {

        serialize("section", new TestModel());
    }

    public static <T> void serialize(String key, T t) throws IllegalAccessException {
        Class<?> clazz = t.getClass();
        boolean skipNonPublicFields = true;
        if(clazz.isAnnotationPresent(ConfigSerializable.class)) {
            ConfigSerializable annotation = clazz.getAnnotation(ConfigSerializable.class);
            skipNonPublicFields = annotation.skipNonPublicFields();
        }

        Field[] fields = clazz.getDeclaredFields();
        for(Field field : fields) {
            if(!field.isAccessible()) {
                field.setAccessible(true);
            }

            int modifiers = field.getModifiers();
            if(Modifier.isTransient(modifiers) || (skipNonPublicFields && !Modifier.isPublic(modifiers))) {
                return;
            }

            if(TYPE_SET.contains(field.getType()) || field.getType().isAssignableFrom(Collection.class) || field.getType().isArray()) {
                //set(key + field.getName(), field.get(t));
                set(key + "." + field.getName(), field);
                continue;
            }

            serialize(key + "." + field.getName(), field.get(t));
        }
    }

    public static <T> void set(String key, Field field) {
        System.out.println("Key: " + key + " Type: " + field.getType().toString());
    }

    public static void addAllTypes(Class<?>... classes) {
        TYPE_SET.addAll(Arrays.asList(classes));
    }

}
