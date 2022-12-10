package ir.wy.wycore.support.anticheat;

import ir.wy.wycore.support.anticheat.AntiCheatSupport;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;

public class AntiCheatManager {

    private static final Set<AntiCheatSupport> ANTICHEATS = new HashSet<>();

    public static void exemptPlayer(@NotNull final Player player) {
        ANTICHEATS.forEach(anticheat -> anticheat.exempt(player));
    }

    public static void unexemptPlayer(@NotNull final Player player) {
        ANTICHEATS.forEach(anticheat -> anticheat.unexempt(player));
    }

    private AntiCheatManager() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}
