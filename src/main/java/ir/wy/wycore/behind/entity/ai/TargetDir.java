package ir.wy.wycore.behind.entity.ai;

import org.bukkit.entity.Mob;
import org.jetbrains.annotations.NotNull;

public interface TargetDir<T extends Mob> extends Dir<T> {
    @Override
    default T addToEntity(@NotNull T entity, int priority) {
        return EntityController.getFor(entity)
                .addTargetDir(priority, this)
                .getEntity();
    }
}
