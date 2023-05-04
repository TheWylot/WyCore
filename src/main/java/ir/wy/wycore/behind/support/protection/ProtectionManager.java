package ir.wy.wycore.behind.support.protection;

import ir.wy.wycore.spigot.support.SupportRegistery;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;

public final class ProtectionManager {

    private static final SupportRegistery<ProtectionSupport> REGISTERY = new SupportRegistery<>();

    public static void register(@NotNull final ProtectionSupport antigrief) {
        REGISTERY.register(antigrief);
    }

    public static void unregister(@NotNull final ProtectionSupport antigrief) {
        REGISTERY.remove(antigrief);
    }

    public static boolean canPickupItem(@NotNull final Player player,
                                        @NotNull final Location location) {
        return REGISTERY.allSafely(integration -> integration.canPickupItem(player, location));
    }

    public static boolean canBreakBlock(@NotNull final Player player,
                                        @NotNull final Block block) {
        return REGISTERY.allSafely(integration -> integration.canBreakBlock(player, block));
    }

    public static boolean canCreateExplosion(@NotNull final Player player,
                                             @NotNull final Location location) {
        return REGISTERY.allSafely(integration -> integration.canCreateExplosion(player, location));
    }

    public static boolean canPlaceBlock(@NotNull final Player player,
                                        @NotNull final Block block) {
        return REGISTERY.allSafely(integration -> integration.canPlaceBlock(player, block));
    }


    public static boolean canInjure(@NotNull final Player player,
                                    @NotNull final LivingEntity victim) {
        return REGISTERY.allSafely(integration -> integration.canInjure(player, victim));
    }

    private ProtectionManager() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}
