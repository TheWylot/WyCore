package ir.wy.wycore.behind.event;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ArmorChangeEvent extends PlayerEvent {

    private static final HandlerList HANDLERS = new HandlerList();
    private final List<ItemStack> before;
    private final List<ItemStack> after;

    public ArmorChangeEvent(@NotNull final Player player, @NotNull final List<ItemStack> after, @NotNull final List<ItemStack> before) {
        super(player);
        this.before = before;
        this.after = after;
    }

    @Override
    @NotNull
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    public List<ItemStack> getBefore() {
        return this.before;
    }

    public List<ItemStack> getAfter() {
        return this.after;
    }
}
