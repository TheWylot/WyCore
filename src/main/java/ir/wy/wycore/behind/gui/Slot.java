package ir.wy.wycore.behind.gui;

import ir.wy.wycore.behind.gui.menu.Menu;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public interface Slot extends GUISector {

    @NotNull
    ItemStack getItemStack(@NotNull Player player);

    default boolean isCaptive(@NotNull final Player player,
                              @NotNull final Menu menu) {
        return false;
    }

    @NotNull
    default Slot getActionableSlot(@NotNull final Player player,
                                   @NotNull final Menu menu) {
        return this;
    }

    default boolean isCaptiveFromEmpty() {
        return false;
    }

    @Override
    default int getRows() {
        return 1;
    }

    @Override
    default int getColumns() {
        return 1;
    }

    @Override
    default Slot getSlotAt(final int row,
                           final int column) {
        return this;
    }
}
