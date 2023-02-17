package ir.wy.wycore.behind.entity;

import ir.wy.wycore.behind.managing.Debugable;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface DebugableEntity extends Debugable<Entity> {
    @Override
    boolean matches(@Nullable Entity entity);

    Entity spawn(@NotNull Location location);
}
