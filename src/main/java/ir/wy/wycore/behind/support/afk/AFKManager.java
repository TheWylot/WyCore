package ir.wy.wycore.behind.support.afk;

import ir.wy.wycore.behind.support.afk.AFKSupport;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;

public final class AFKManager {

    private static final Set<AFKSupport> REGISTERED = new HashSet<>();

    public static void register(@NotNull final AFKSupport support) {
        REGISTERED.removeIf(it -> it.getPluginName().equalsIgnoreCase(support.getPluginName()));
        REGISTERED.add(support);
    }

    public static boolean isAfk(@NotNull final Player player) {
        for (AFKSupport integration : REGISTERED) {
            if (integration.isAfk(player)) {
                return true;
            }
        }

        return false;
    }

    private AFKManager() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}
