package ir.wy.wycore.behind.entity.base;

import ir.wy.wycore.behind.entity.DebugableEntity;
import org.apache.commons.lang.Validate;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.jetbrains.annotations.NotNull;

import java.util.function.Function;
import java.util.function.Predicate;

public class CustomDebugableEntity implements DebugableEntity {
    private final DebugableEntity handler;
    private final Predicate<Entity> test;
    private final Function<Location, Entity> provider;

    public CustomDebugableEntity(@NotNull final DebugableEntity entity,
                                 @NotNull final Predicate<@NotNull Entity> test,
                                 @NotNull final Function<Location, Entity> provider
                                 ) {
        this.handler= entity;
        this.test = test;
        this.provider = provider;
    }

    @Override
    public boolean matches(@NotNull final Entity entity) {
        return entity != null && handler.matches(entity) &&
                test.test(entity);
    }

    @Override
    public Entity spawn(@NotNull final Location location) {
        Validate.notNull(location.getWorld());
        return provider.apply(location);
    }

    public DebugableEntity getHandler() {
        return this.handler;
    }
}
