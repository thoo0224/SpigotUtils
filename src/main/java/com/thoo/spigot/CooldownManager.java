package com.thoo.spigot;

import java.time.Instant;
import java.util.IdentityHashMap;
import java.util.Map;

/**
 * Not finished yet!
 * @param <T>
 */
public final class CooldownManager<T> {

    private final Map<T, Instant> cooldowns = new IdentityHashMap<T, Instant>();
    private final Instant duration;

    public CooldownManager(Instant duration) {
        this.duration = duration;
    }

    public boolean hasCooldown(T entity) {
        return !cooldowns.containsKey(entity);
    }

}
