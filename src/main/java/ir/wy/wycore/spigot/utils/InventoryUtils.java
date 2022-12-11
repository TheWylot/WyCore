package ir.wy.wycore.spigot.utils;

import com.cryptomorin.xseries.XMaterial;
import ir.wy.wycore.spigot.Design;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class InventoryUtils {

    public static int getAmount(Inventory inventory, XMaterial material) {
        int total = 0;
        for (ItemStack item : inventory.getContents()) {
            if (item == null) continue;

            if (material.isSimilar(item)) {
                total += item.getAmount();
            }
        }
        return total;
    }

    public static void removeAmount(Inventory inventory, XMaterial material, int amount) {
        int removed = 0;
        int index = 0;

        for (ItemStack itemStack : inventory.getContents()) {
            if (itemStack == null) {
                index++;
                continue;
            }

            // Don't continue to search if there were enough items removed
            if (removed >= amount) break;

            if (material.isSimilar(itemStack)) {
                // true if there are not enough items in this slot
                if (removed + itemStack.getAmount() <= amount) {
                    removed += itemStack.getAmount();
                    inventory.setItem(index, null);
                } else {
                    // Just remove the items that should actually be removed, not the whole stack
                    itemStack.setAmount(itemStack.getAmount() - (amount - removed));
                    removed += amount;
                }
            }
            index++;
        }
    }

    public static boolean hasEmptySlot(Inventory inventory) {
        return inventory.firstEmpty() != -1;
    }

    public static void fillInventory(Inventory inventory, Design design, List<Placeholder> placeholders) {
        for (int i = 0; i < inventory.getSize(); i++) {
            inventory.setItem(i, ItemStackUtils.makeItem(design.filler, placeholders));
        }
        for (int slot : design.items.keySet()) {
            if (slot >= inventory.getSize()) continue;
            inventory.setItem(slot, ItemStackUtils.makeItem(design.items.get(slot), placeholders));
        }
    }

    public static void fillInventory(Inventory inventory, Design design) {
        for (int i = 0; i < inventory.getSize(); i++) {
            inventory.setItem(i, ItemStackUtils.makeItem(design.filler));
        }
        if (design.items == null) return;
        for (int slot : design.items.keySet()) {
            if (slot >= inventory.getSize()) continue;
            inventory.setItem(slot, ItemStackUtils.makeItem(design.items.get(slot)));
        }
    }
}
