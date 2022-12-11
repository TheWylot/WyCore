package ir.wy.wycore.spigot.support.afk;

import ir.wy.wycore.spigot.support.Support;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public interface AFKSupport extends Support {

    boolean isAfk(@NotNull Player player);
}
