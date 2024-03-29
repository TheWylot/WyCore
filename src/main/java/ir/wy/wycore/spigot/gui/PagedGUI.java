package ir.wy.wycore.spigot.gui;

import ir.wy.wycore.spigot.Design;
import ir.wy.wycore.spigot.Item;
import ir.wy.wycore.spigot.utils.InventoryUtils;
import ir.wy.wycore.spigot.utils.ItemStackUtils;
import lombok.AllArgsConstructor;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@AllArgsConstructor @Deprecated(since = "2.0.0")
public abstract class PagedGUI<T> implements GUI {


    private final int size;
    private final Design design;
    private final Item previousPage;
    private final Item nextPage;
    private final Inventory previousInventory;
    private final Item backButton;
    private final Map<Integer, T> items = new HashMap<>();
    private int page;

    public PagedGUI(int page, int size, Design background, Item previousPage, Item nextPage) {
        this.page = page;
        this.size = size;
        this.design = background;
        this.previousPage = previousPage;
        this.nextPage = nextPage;
        this.previousInventory = null;
        this.backButton = null;
    }


    @Override
    public void addContent(Inventory inventory) {
        items.clear();
        InventoryUtils.fillInventory(inventory, design);

        if (isPaged()) {
            inventory.setItem(inventory.getSize() - 3, ItemStackUtils.makeItem(nextPage));
            inventory.setItem(inventory.getSize() - 7, ItemStackUtils.makeItem(previousPage));
        }

        int elementsPerPage = inventory.getSize() - (isPaged() || previousInventory != null ? 9 : 0);
        List<T> objects = getPageObjects().stream()
                .skip((long) (page - 1) * elementsPerPage)
                .limit(elementsPerPage)
                .collect(Collectors.toList());
        AtomicInteger slot = new AtomicInteger(0);
        for (T t : objects) {
            int currentSlot = slot.getAndIncrement();
            items.put(currentSlot, t);
            inventory.setItem(currentSlot, getItemStack(t));
        }
        if (previousInventory != null && backButton != null) {
            inventory.setItem(inventory.getSize() + backButton.slot, ItemStackUtils.makeItem(backButton));
        }
    }

    public abstract Collection<T> getPageObjects();

    public abstract ItemStack getItemStack(T t);

    public T getItem(int slot) {
        return items.get(slot);
    }

    public int getSize() {
        int newSize = size;
        if (size <= 0) {
            newSize = (int) (Math.ceil(getPageObjects().size() / 9.0) * 9);
        }
        return Math.max(Math.min(newSize, 54), 9);
    }

    public boolean isPaged() {
        return getPageObjects().size() > getSize();
    }

    @Override
    public void onInventoryClick(InventoryClickEvent event) {
        if (isPaged()) {
            if (event.getSlot() == getInventory().getSize() - 7) {
                if (page > 1) {
                    page--;
                    event.getWhoClicked().openInventory(getInventory());
                }
            } else if (previousInventory != null && backButton != null) {
                if (event.getSlot() == event.getInventory().getSize() + backButton.slot) {
                    event.getWhoClicked().openInventory(previousInventory);
                }
            } else if (event.getSlot() == getInventory().getSize() - 3) {
                if ((event.getInventory().getSize() - 9) * page < getPageObjects().size()) {
                    page++;
                    event.getWhoClicked().openInventory(getInventory());
                }
            } else if (previousInventory != null && backButton != null && event.getSlot() == (event.getInventory().getSize() + backButton.slot)) {
                event.getWhoClicked().openInventory(previousInventory);
            }
        }
    }
}
