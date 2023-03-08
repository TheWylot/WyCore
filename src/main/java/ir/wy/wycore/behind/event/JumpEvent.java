package ir.wy.wycore.behind.event;

import org.bukkit.Location;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerMoveEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class JumpEvent extends PlayerMoveEvent {

    private static final HandlerList HANDLER_LIST = new HandlerList();
    private final PlayerMoveEvent handle;

    public JumpEvent(@NotNull final PlayerMoveEvent event) {
        super(event.getPlayer(), event.getFrom(), event.getTo());
        this.handle = event;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    public static @NotNull HandlerList getHandlerList() {
        return HANDLER_LIST;
    }

    @Override
    public boolean isCancelled() {
        return handle.isCancelled();
    }

    @Override
    public void setCancelled(final boolean cancelled) {
        handle.setCancelled(cancelled);
    }

    @NotNull @Override
    public Location getFrom() {
        return handle.getFrom();
    }

    @NotNull @Override
    public void setFrom(@NotNull final Location from) {
        handle.setFrom(from);
    }

    @Nullable @Override
    public Location getTo() {
        return handle.getTo();
    }

    @Override
    public void setTo(@NotNull final Location to) {
        handle.setTo(to);
    }
}
