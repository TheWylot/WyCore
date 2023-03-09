package ir.wy.wycore.behind.gui.page;

import ir.wy.wycore.behind.WyBase;
import ir.wy.wycore.behind.gui.GUISector;
import ir.wy.wycore.behind.gui.Slot;
import ir.wy.wycore.behind.gui.menu.Menu;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class Page implements GUISector {

    public static final String PAGE_KEY = "page";

    public static final String MAX_PAGE_KEY = "max_page";

    private final int pageNumber;
    private final Menu page;

    private Menu delegate = null;
    private int rows = 6;
    private int columns = 9;

    public Page(final int pageNumber,
                @NotNull final Menu page) {
        this.pageNumber = pageNumber;
        this.page = page;
    }

    public int getPageNumber() {
        return this.pageNumber;
    }

    @Override
    public @Nullable Slot getSlotAt(final int row,
                                    final int column,
                                    @NotNull final Player player,
                                    @NotNull final Menu menu) {
        if (menu.getPage(player) != pageNumber) {
            return null;
        }

        if (delegate == null) {
            delegate = WyBase.get().blendMenuState(page, menu);
        }

        return page.getSlot(row, column, player);
    }

    @Override
    public void init(final int maxRows,
                     final int maxColumns) {
        this.rows = maxRows;
        this.columns = maxColumns;
    }

    @Override
    public int getRows() {
        return rows;
    }

    @Override
    public int getColumns() {
        return columns;
    }
}
