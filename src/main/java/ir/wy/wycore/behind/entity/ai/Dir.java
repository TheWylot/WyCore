package ir.wy.wycore.behind.entity.ai;

import org.bukkit.entity.Mob;
import org.jetbrains.annotations.NotNull;

public interface Dir<T extends Mob> {
    T addtoEntity(@NotNull T entity, int priority);

    T addToEntity(T entity, int priority);
}
