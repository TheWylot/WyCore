package ir.wy.wycore.behind;

import org.apache.commons.lang.Validate;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.jetbrains.annotations.NotNull;


public interface WyBase {

    @NotNull
    Entity createDummyEntity(@NotNull Location location);

    static WyBase get() {
        return WyBase.get();
    }

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
