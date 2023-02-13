package ir.wy.wycore.behind.support.anticheat;

import ir.wy.wycore.spigot.support.Support;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public interface AntiCheatSupport extends Support {
    void exempt(@NotNull Player player);

    void unexempt(@NotNull Player player);
}
