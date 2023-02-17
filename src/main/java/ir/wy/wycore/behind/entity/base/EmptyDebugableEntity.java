package ir.wy.wycore.behind.entity.base;

import ir.wy.wycore.behind.WyBase;
import ir.wy.wycore.behind.entity.DebugableEntity;
import org.apache.commons.lang.Validate;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class EmptyDebugableEntity implements DebugableEntity {

    public EmptyDebugableEntity() {

    }

    @Override
    public boolean matches(@Nullable final Entity entity) {
        return false;
    }

    @Override
    public Entity spawn(@NotNull final Location location) {
        Validate.notNull(location.getWorld());
        return WyBase.get().createDummyEntity(location);
    }
}
