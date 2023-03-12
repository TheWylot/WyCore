package ir.wy.wycore.spigot.utils;

import com.cryptomorin.xseries.XMaterial;
import de.tr7zw.changeme.nbtapi.NBTCompound;
import de.tr7zw.changeme.nbtapi.NBTItem;
import de.tr7zw.changeme.nbtapi.NBTListCompound;
import ir.wy.wycore.WyCore;
import ir.wy.wycore.spigot.Item;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Base64;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@SuppressWarnings("ALL")
public class ItemStackUtils {

    private static final boolean supports = XMaterial.supports(16);

    public static ItemStack makeItem(XMaterial material, int amount, String name, List<String> lore) {
        ItemStack itemStack = material.parseItem();
        if (itemStack == null) return null;
        itemStack.setAmount(amount);
        ItemMeta itemMeta = itemStack.getItemMeta();
        if (itemMeta != null) {
            itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
            itemMeta.setLore(StringUtils.color(lore));
            itemMeta.setDisplayName(StringUtils.color(name));
            itemStack.setItemMeta(itemMeta);
        }
        return itemStack;
    }

    public static ItemStack makeItem(Item item, List<Placeholder> placeholders) {
        ItemStack itemStack = makeItem(item.material, item.amount, StringUtils.processMultiplePlaceholders(item.displayName, placeholders), StringUtils.processMultiplePlaceholders(item.lore, placeholders));
        if (item.material == XMaterial.PLAYER_HEAD && item.headData != null) {
            itemStack = setHeadData(item.headData, itemStack);
        } else if (item.material == XMaterial.PLAYER_HEAD && item.headOwner != null) {
            UUID uuid;
            if (item.headOwnerUUID == null) {
                uuid = SkinUtils.getUUID(StringUtils.processMultiplePlaceholders(item.headOwner, placeholders));
            } else {
                uuid = item.headOwnerUUID;
            }
            itemStack = setHeadData(SkinUtils.getHeadData(uuid), itemStack);
        }

        return itemStack;
    }

    public static ItemStack makeItem(Item item) {
        return makeItem(item, Collections.emptyList());
    }

    public static String serialize(ItemStack itemStack) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            BukkitObjectOutputStream bukkitObjectOutputStream = new BukkitObjectOutputStream(byteArrayOutputStream);
            bukkitObjectOutputStream.writeObject(itemStack);
            bukkitObjectOutputStream.flush();

            return Base64.getEncoder().encodeToString(byteArrayOutputStream.toByteArray());
        } catch (Exception e) {
            return "";
        }
    }

    public static ItemStack deserialize(String string) {
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(Base64.getDecoder().decode(string));
            BukkitObjectInputStream bukkitObjectInputStream = new BukkitObjectInputStream(byteArrayInputStream);
            return (ItemStack) bukkitObjectInputStream.readObject();
        } catch (Exception exception) {
            return XMaterial.AIR.parseItem();
        }
    }

    private static ItemStack setHeadData(String headData, ItemStack itemStack) {
        if (WyCore.getInstance().isTesting()) return itemStack;
        if (headData == null) return itemStack;

        NBTItem nbtItem = new NBTItem(itemStack);
        NBTCompound skull = nbtItem.addCompound("SkullOwner");
        if (supports) {
            skull.setUUID("Id", UUID.randomUUID());
        } else {
            skull.setString("Id", UUID.randomUUID().toString());
        }

        NBTListCompound texture = skull.addCompound("Properties").getCompoundList("textures").addCompound();
        texture.setString("Value", headData);
        return nbtItem.getItem();
    }

}