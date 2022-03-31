package ir.wy.wycore.gui;

import ir.wy.wycore.Design;
import ir.wy.wycore.Item;
import ir.wy.wycore.utils.InventoryUtils;
import ir.wy.wycore.utils.ItemStackUtils;
import lombok.AllArgsConstructor;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

@AllArgsConstructor
public abstract class BackGUI implements GUI {
    private final Design background;
    private final Inventory previousInventory;
    private final Item backButton;

    @Override
    public void addContent(Inventory inventory) {
        InventoryUtils.fillInventory(inventory, background);
        if (previousInventory != null) {
            inventory.setItem(inventory.getSize() + backButton.slot, ItemStackUtils.makeItem(backButton));
        }
    }

    @Override
    public void onInventoryClick(InventoryClickEvent event) {
        if (previousInventory != null && event.getSlot() == (event.getInventory().getSize() + backButton.slot)) {
            event.getWhoClicked().openInventory(previousInventory);
        }
    }
}
