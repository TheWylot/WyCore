package ir.wy.wycore.behind.entity.ai;

import ir.wy.wycore.behind.WyBase;
import org.bukkit.entity.Mob;
import org.jetbrains.annotations.NotNull;

public interface EntityController<T extends Mob> {
    EntityController<T> addTargetDir(int priority,
                                     @NotNull TargetDir<? super T> dir);

    EntityController<T> clearTargetDirs();

    EntityController<T> removeTargetDir(@NotNull TargetDir<? super T> dir);

    EntityController<T> addEntityDir(int priority,
                                     @NotNull EntityDir<? super T> dir);

    EntityController<T> removeEntityDir(@NotNull EntityDir<? super T> dir);

    EntityController<T> clearEntityDirs();

    default EntityController<T> clearAllDirs() {
        this.clearTargetDirs();
        return this.clearEntityDirs();
    }

    T getEntity();

    static <T extends Mob> EntityController<T> getFor(@NotNull final T entity) {
        return WyBase.get().createEntityController(entity);
    }
}
