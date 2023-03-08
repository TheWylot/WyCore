package ir.wy.wycore.behind.event;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerExpChangeEvent;

import org.jetbrains.annotations.NotNull;

public class EXPEvent extends Event {
    private static final HandlerList HANDLER_LIST = new HandlerList();
    private final PlayerExpChangeEvent expChangeEvent;

    public EXPEvent(@NotNull final PlayerExpChangeEvent event ) {
        this.expChangeEvent = event;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }

    public PlayerExpChangeEvent getExpChangeEvent() {
        return this.expChangeEvent;
    }
}
