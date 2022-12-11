package ir.wy.wycore.spigot.support.placeholder;

import ir.wy.wycore.spigot.support.Support;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import java.util.ArrayList;
import java.util.List;

public interface PlaceholderSupport extends Support {
    void registerSupport();

    String translate(@NotNull String text, @Nullable Player player);

    default List<String> findPlaceholdersIn(@NotNull String text) {
        return new ArrayList<>();
    }
}
