package ir.wy.wycore.behind.particle;

import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;

public interface AvailableParticle {

    void spawn(@NotNull Location location, int amount);
    default void spawn(@NotNull Location location) {
        spawn(location, 1);
    }
}
