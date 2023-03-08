package ir.wy.wycore.spigot.gui;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

@Deprecated(since = "2.0.0")
public interface GUI extends InventoryHolder {
    void onInventoryClick(InventoryClickEvent event);

    void addContent(Inventory inventory);
}
