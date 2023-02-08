package ir.wy.wycore.behind.support.protection;

import ir.wy.wycore.spigot.support.Support;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import org.jetbrains.annotations.NotNull;

public interface ProtectionSupport extends Support {

    boolean canBreakBlock(@NotNull Player player, @NotNull Block block);

    boolean canCreateExplosion(@NotNull Player player, @NotNull Location location);

    boolean canPlaceBlock(@NotNull Player player, @NotNull Block block);

    boolean canInjure(@NotNull Player player, @NotNull LivingEntity victim);

    default boolean canPickupItem(@NotNull Player player, @NotNull Location location) {
        return true;
    }
}
