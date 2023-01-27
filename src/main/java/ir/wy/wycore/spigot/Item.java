package ir.wy.wycore.spigot;

import com.cryptomorin.xseries.XMaterial;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@NoArgsConstructor
public class Item {
    public String headOwner;
    public String headData;
    public String displayName;
    public List<String> lore;
    public XMaterial material;
    public UUID headOwnerUUID;
    public Integer slot;
    public int amount;

    public Item(XMaterial material, int amount, String displayName, List<String> lore) {
        this.material = material;
        this.amount = amount;
        this.lore = lore;
        this.displayName = displayName;
    }

    public Item(XMaterial material, int slot, int amount, String displayName, List<String> lore) {
        this.material = material;
        this.amount = amount;
        this.lore = lore;
        this.displayName = displayName;
        this.slot = slot;
    }

    public Item(XMaterial material, int slot, String headData, int amount, String displayName, List<String> lore) {
        this.material = material;
        this.amount = amount;
        this.lore = lore;
        this.displayName = displayName;
        this.slot = slot;
        this.headData = headData;
    }

    public Item(XMaterial material, int slot, int amount, String displayName, String headOwner, List<String> lore) {
        this.material = material;
        this.amount = amount;
        this.lore = lore;
        this.displayName = displayName;
        this.headOwner = headOwner;
        this.slot = slot;
    }

    public Item(XMaterial material, int amount, String displayName, String headOwner, List<String> lore) {
        this.material = material;
        this.amount = amount;
        this.lore = lore;
        this.displayName = displayName;
        this.headOwner = headOwner;
    }

    public Item(XMaterial material, int amount, String displayName, String headOwner, UUID ownerUUID, List<String> lore) {
        this.material = material;
        this.amount = amount;
        this.lore = lore;
        this.displayName = displayName;
        this.headOwnerUUID = ownerUUID;
        this.headOwner = headOwner;
    }

}
