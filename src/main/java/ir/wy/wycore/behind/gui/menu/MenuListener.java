package ir.wy.wycore.behind.gui.menu;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public abstract class MenuListener <T extends MenuHandler> {

    private final Class<T> handleClass;

    protected MenuListener(@NotNull final Class<T> handleClass) {
        this.handleClass = handleClass;
    }

    public abstract void handle(@NotNull Player player,
                                @NotNull Menu menu,
                                @NotNull T event);


    public boolean canHandleEvent(@NotNull final MenuHandler menuHandler) {
        return handleClass.isAssignableFrom(menuHandler.getClass());
    }


}
