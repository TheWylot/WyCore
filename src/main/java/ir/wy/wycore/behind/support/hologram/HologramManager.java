package ir.wy.wycore.behind.support.hologram;

import ir.wy.wycore.spigot.support.SupportRegistery;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class HologramManager {

    private static final SupportRegistery<HologramSupport> REGISTERY = new SupportRegistery<>();

    @Deprecated
    private static final Set<HologramSupport> REGISTERED = new HashSet<>();

    private static void register(@NotNull final HologramSupport Support) {
        REGISTERY.register(Support);
    }

    private HologramManager() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}
