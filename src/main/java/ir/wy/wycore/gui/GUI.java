package ir.wy.wycore.gui;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public interface GUI extends InventoryHolder {
    void onInventoryClick(InventoryClickEvent event);

    void addContent(Inventory inventory);
}
