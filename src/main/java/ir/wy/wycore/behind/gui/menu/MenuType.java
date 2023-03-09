package ir.wy.wycore.behind.gui.menu;

public enum MenuType {

    NORMAL(9, 6),
    DISPENSER(3, 3);

    private final int columns;
    private final int defaultRows;

    MenuType(final int columns, final int defaultRows) {
        this.columns = columns;
        this.defaultRows = defaultRows;
    }

    public int getColumns() {
        return columns;
    }

    public int getDefaultRows() {
        return defaultRows;
    }
}
