package com.thoo.spigot.config;

import com.thoo.spigot.annotations.ConfigSerializable;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.logging.Level;

public final class CustomConfiguration extends YamlConfiguration {

    private final JavaPlugin plugin;
    private final File file;
    private final String fileName;

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
    private static final HashMap<Class<? extends Serializable>, ConfigTypeAdapter<? extends Serializable>> TYPE_ADAPTERS = new HashMap<>();

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

    public void save() throws IOException {
        save(file);
    }

    @SuppressWarnings("unchecked")
    public <T extends Serializable> void serialize(String key, T t) throws IllegalAccessException {
        Class<?> clazz = t.getClass();
        boolean skipNonPublicFields = true;
        if(clazz.isAnnotationPresent(ConfigSerializable.class)) {
            ConfigSerializable annotation = clazz.getAnnotation(ConfigSerializable.class);
            skipNonPublicFields = annotation.skipNonPublicFields();
        }
        ConfigTypeAdapter<T> parentTypeAdapter = (ConfigTypeAdapter<T>) CustomConfiguration.TYPE_ADAPTERS.getOrDefault(clazz, null);
        if(parentTypeAdapter != null) {
            parentTypeAdapter.serialize(key, t, this);
            return;
        }

        set(key + ".class", clazz.getName());
        Field[] fields = clazz.getDeclaredFields();
        for(Field field : fields) {
            String fieldKey = key + "." + field.getName();
            if(!field.isAccessible()) {
                field.setAccessible(true);
            }

            int modifiers = field.getModifiers();
            if(Modifier.isTransient(modifiers) || (skipNonPublicFields && !Modifier.isPublic(modifiers))) {
                continue;
            }

            if(TYPE_SET.contains(field.getType()) || field.getType().isAssignableFrom(Collection.class) || field.getType().isArray()) {
                set(key + "." + field.getName(), field.get(t));
                continue;
            }

            ConfigTypeAdapter<T> typeAdapter = (ConfigTypeAdapter<T>) CustomConfiguration.TYPE_ADAPTERS.getOrDefault(field.getType(), null);
            if(typeAdapter != null) {
                typeAdapter.serialize(fieldKey, (T) field.get(t), this);
                continue;
            }

            serialize(fieldKey, (T) field.get(t));
        }
    }

    public static void registerTypeAdapter(Class<? extends Serializable> clazz, ConfigTypeAdapter<? extends Serializable> overrideable) {
        CustomConfiguration.TYPE_ADAPTERS.put(clazz, overrideable);
    }

    private static void addAllTypes(Class<?>... classes) {
        TYPE_SET.addAll(Arrays.asList(classes));
    }

}
