package ir.wy.wycore.support.afk;

import ir.wy.wycore.support.Support;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public interface AFKSupport extends Support {

    boolean isAfk(@NotNull Player player);
}
