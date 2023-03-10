package ir.wy.wycore.behind.packet;

import ir.wy.wycore.WyCore;
import ir.wy.wycore.behind.WyBase;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public record Packet(@NotNull Object handle) {
    void send(@NotNull final Player player) {
        WyBase.get().sendPacket(player, this);
    }
}
