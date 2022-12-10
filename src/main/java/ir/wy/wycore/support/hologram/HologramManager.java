package ir.wy.wycore.support.hologram;

import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class HologramManager {

    private static final Set<HologramSupport> REGISTERED = new HashSet<>();

    private static void register (@NotNull final HologramSupport support) {
        REGISTERED.removeIf(it -> it.getPluginName().equalsIgnoreCase(support.getPluginName()));
        REGISTERED.add(support);
    }

    private static Hologram createHologram(@NotNull final Location location, @NotNull final List<String> contents) {
        for (HologramSupport support : REGISTERED) {
            return support.createHologram(location, contents);
        }
        return new EmptyHologram();
    }

    private HologramManager() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}
