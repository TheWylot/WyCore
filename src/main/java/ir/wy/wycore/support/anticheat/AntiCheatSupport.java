package ir.wy.wycore.support.anticheat;

import ir.wy.wycore.support.Support;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public interface AntiCheatSupport extends Support {
        void exempt(@NotNull Player player);

        void unexempt(@NotNull Player player);
}
