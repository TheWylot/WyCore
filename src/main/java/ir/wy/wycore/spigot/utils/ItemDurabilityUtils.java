package ir.wy.wycore.spigot.utils;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerItemBreakEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("RedundantCast")
public final class ItemDurabilityUtils {
    @Deprecated(since = "6.24.0", forRemoval = true)
    public static void damageItem(@NotNull final Player player,
                                  @NotNull final ItemStack item,
                                  final int damage,
                                  final int slot) {
        damageItem(player, item, damage);
    }


    public static void damageItem(@NotNull final Player player,
                                  @NotNull final ItemStack item,
                                  final int damage) {
        if (item.getItemMeta() == null) {
            return;
        }

        if (item.getItemMeta().isUnbreakable()) {
            return;
        }

        if (!(item.getItemMeta() instanceof Damageable)) {
            return;
        }

        if (item.getType() == Material.CARVED_PUMPKIN || item.getType() == Material.PLAYER_HEAD) {
            return;
        }

        PlayerItemDamageEvent event3 = new PlayerItemDamageEvent(player, item, damage);
        Bukkit.getPluginManager().callEvent(event3);

        if (!event3.isCancelled()) {
            int damage2 = event3.getDamage();
            Damageable meta = (Damageable) item.getItemMeta();
            meta.setDamage(meta.getDamage() + damage2);

            if (meta.getDamage() >= item.getType().getMaxDurability()) {
                meta.setDamage(item.getType().getMaxDurability());
                item.setItemMeta((ItemMeta) meta);
                PlayerItemBreakEvent event = new PlayerItemBreakEvent(player, item);
                Bukkit.getPluginManager().callEvent(event);
                item.setType(Material.AIR);
                player.playSound(player.getLocation(), Sound.ENTITY_ITEM_BREAK, SoundCategory.BLOCKS, 1, 1);
            } else {
                item.setItemMeta((ItemMeta) meta);
            }
        }
    }

    public static void damageItem(@NotNull final ItemStack item,
                                  final int damage) {
        if (item.getItemMeta() == null) {
            return;
        }

        if (item.getItemMeta().isUnbreakable()) {
            return;
        }

        if (!(item.getItemMeta() instanceof Damageable)) {
            return;
        }

        if (item.getType() == Material.CARVED_PUMPKIN || item.getType() == Material.PLAYER_HEAD) {
            return;
        }

        @SuppressWarnings("PatternVariableCanBeUsed") Damageable meta = (Damageable) item.getItemMeta();
        meta.setDamage(meta.getDamage() + damage);

        if (meta.getDamage() >= item.getType().getMaxDurability()) {
            meta.setDamage(item.getType().getMaxDurability());
            item.setItemMeta((ItemMeta) meta);
            item.setType(Material.AIR);
        } else {
            item.setItemMeta((ItemMeta) meta);
        }
    }

    public static void damageItemNoBreak(@NotNull final ItemStack item,
                                         final int damage,
                                         @NotNull final Player player) {
        if (item.getItemMeta() == null) {
            return;
        }

        if (item.getItemMeta().isUnbreakable()) {
            return;
        }

        if (!(item.getItemMeta() instanceof Damageable)) {
            return;
        }

        PlayerItemDamageEvent event3 = new PlayerItemDamageEvent(player, item, damage);
        Bukkit.getPluginManager().callEvent(event3);

        if (!event3.isCancelled()) {
            int damage2 = event3.getDamage();
            Damageable meta = (Damageable) item.getItemMeta();
            meta.setDamage(meta.getDamage() + damage2);

            if (meta.getDamage() >= item.getType().getMaxDurability()) {
                meta.setDamage(item.getType().getMaxDurability() - 1);
            }
            item.setItemMeta((ItemMeta) meta);
        }
    }

    public static void repairItem(@NotNull final ItemStack item,
                                  final int repair) {
        if (item.getItemMeta() == null) {
            return;
        }

        if (item.getItemMeta().isUnbreakable()) {
            return;
        }

        if (item.getItemMeta() instanceof Damageable meta) {
            meta.setDamage(meta.getDamage() - repair);

            if (meta.getDamage() < 0) {
                meta.setDamage(0);
            }
            item.setItemMeta((ItemMeta) meta);
        }
    }

}
