package ir.wy.wycore.behind.entity.ai;

import org.bukkit.entity.Mob;
import org.jetbrains.annotations.NotNull;

import java.util.EnumSet;
import java.util.List;
import java.util.Set;
public abstract class CustomDir<T extends Mob> implements EntityDir<T>, TargetDir<T> {


    @Override
    public T addtoEntity(@NotNull T entity, int priority) {
        return null;
    }

    @Override
    public T addToEntity(@NotNull T entity, int priority) {
        return EntityDir.super.addToEntity(entity, priority);
    }
}
