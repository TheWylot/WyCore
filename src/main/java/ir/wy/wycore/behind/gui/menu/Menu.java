package ir.wy.wycore.behind.gui.menu;

import ir.wy.wycore.behind.gui.Slot;
import ir.wy.wycore.behind.gui.page.Page;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public interface Menu {

    int getRows();

    default int getColumns() {
        return 9;
    }

    Slot getSlot(int row, int column);

    default Slot getSlot(final int row,
                         final int column,
                         @NotNull final Player player) {
        return this.getSlot(row, column);
    }

    String getTitle();

    Inventory open(@NotNull Player player);

    List<ItemStack> getCaptiveItems(@NotNull Player player);

    @Nullable
    default ItemStack getCaptiveItem(@NotNull final Player player,
                                     final int row,
                                     final int column) {
        return null;
    }

    default void setState(@NotNull Player player,
                          @NotNull String key,
                          @NotNull Object value) {

    }

    void removeState(@NotNull Player player, @NotNull String key);

    void clearState(@NotNull Player player);

    @Nullable <T> T getState (@NotNull Player player, @NotNull String key);

    Map<String, Object> getState(@NotNull Player player);

    void refresh(@NotNull Player player);

    default boolean allowChangingHeldItem() {
        return false;
    }

    default void callEvent(@NotNull final Player player, @NotNull final MenuHandler menuHandler) {

    }

    default int getPage(@NotNull final Player player) {
        Integer pageState = this.getState(player, Page.PAGE_KEY);
        return Objects.requireNonNullElse(pageState, 1);
    }



}
