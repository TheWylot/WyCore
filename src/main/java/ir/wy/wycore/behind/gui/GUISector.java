package ir.wy.wycore.behind.gui;

import ir.wy.wycore.behind.gui.Slot;
import ir.wy.wycore.behind.gui.menu.Menu;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface GUISector {

    int getRows();
    int getColumns();

    default void init(final int maxRows, final int maxColumns) {

    }

    @Nullable
    default Slot getSlotAt(final int row, final int column) {
        return null;
    }

    default Slot getSlotAt(final int row, final int column,
                           @NotNull final Player player, @NotNull final Menu menu) {
        return getSlotAt(row, column);
    }
}
