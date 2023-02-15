package ir.wy.wycore.behind.packet;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.jetbrains.annotations.NotNull;


public class PacketEvent implements Cancellable {
    private final Packet packet;

    private final Player player;


    private boolean cancelled = false;


    public PacketEvent(@NotNull final Packet packet,
                       @NotNull final Player player) {
        this.packet = packet;
        this.player = player;
    }

    @NotNull
    public Packet getPacket() {
        return packet;
    }

    @NotNull
    public Player getPlayer() {
        return player;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(final boolean cancelled) {
        this.cancelled = cancelled;
    }
}
