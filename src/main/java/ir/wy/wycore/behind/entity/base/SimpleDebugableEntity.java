package ir.wy.wycore.behind.entity.base;

import ir.wy.wycore.behind.entity.DebugableEntity;
import org.apache.commons.lang.Validate;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.jetbrains.annotations.NotNull;

public class SimpleDebugableEntity implements DebugableEntity {
    private final EntityType type;
    public SimpleDebugableEntity(@NotNull final EntityType type) {
        this.type = type;
        Validate.notNull(type.getEntityClass(), "Entity cannot be undefined");
    }

    @Override
    public boolean matches(@NotNull final Entity entity) {
        return entity != null && entity.getType() == type;
    }

    @Override
    public Entity spawn(@NotNull final Location location) {
        Validate.notNull(location.getWorld());
        assert type.getEntityClass() != null;
        return location.getWorld().spawn(location, type.getEntityClass());
    }

    public EntityType getType() {
        return this.type;
    }
}
