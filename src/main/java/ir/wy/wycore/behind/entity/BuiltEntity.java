package ir.wy.wycore.behind.entity;

import org.apache.commons.lang.Validate;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Entity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Function;
import java.util.function.Predicate;

public class BuiltEntity implements DebugableEntity {

    private final NamespacedKey key;
    private final Predicate<@NotNull Entity> debug;
    private final Function<Location, Entity> provider;

    public BuiltEntity(@NotNull final NamespacedKey key,
                        @NotNull final Predicate<@NotNull Entity> debug,
                        @NotNull final Function<Location,Entity> provider) {
        this.key = key;
        this.debug = debug;
        this.provider = provider;
    }

    @Override
    public boolean matches(@Nullable final Entity entity) {
        if (entity == null) {
            return false;
        }

        return debug.test(entity);
    }

    @Override
    public Entity spawn(@NotNull final Location location) {
        Validate.notNull(location.getWorld());

        return provider.apply(location);
    }

    //public void register() {
    //    Entities.registerCustomEntity(this.getKey(), this);
    //}

    public NamespacedKey getKey() {
        return this.key;
    }
}