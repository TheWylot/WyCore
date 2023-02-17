package ir.wy.wycore.behind;

import ir.wy.wycore.behind.entity.ai.EntityController;
import org.apache.commons.lang.Validate;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Mob;
import org.jetbrains.annotations.NotNull;


public interface WyBase {

    @NotNull
    Entity createDummyEntity(@NotNull Location location);

    static WyBase get() {
        return WyBase.get();
    }

    <T extends Mob> EntityController<T> createEntityController(T entity);

    final class Instance {

        /**
         * Instance of eco.
         */
        private static WyBase wybase;

        static void set(@NotNull final WyBase wybase) {
            Validate.isTrue(Instance.wybase == null, "Already initialized!");

            Instance.wybase = wybase;
        }
    }
}
