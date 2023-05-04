package ir.wy.wycore.behind.support.anticheat;

import ir.wy.wycore.behind.WyBase;
import ir.wy.wycore.behind.support.anticheat.AntiCheatSupport;
import ir.wy.wycore.spigot.support.Support;
import ir.wy.wycore.spigot.support.SupportRegistery;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;

public class AntiCheatManager {

    private static SupportRegistery<AntiCheatSupport> REGISTERY = new SupportRegistery<>();

    public static void register(@NotNull final AntiCheatSupport anticheat) {
        REGISTERY.register(anticheat);
    }

    public static void exemptPlayer(@NotNull final Player player) {
        REGISTERY.forEach(anticheat -> anticheat.exempt(player));
    }

    public static void unexemptPlayer(@NotNull final Player player) {
        REGISTERY.forEach(anticheat -> anticheat.unexempt(player));
    }

    private AntiCheatManager() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}
