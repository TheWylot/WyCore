package ir.wy.wycore.spigot.utils;

import org.bukkit.entity.Arrow;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.MetadataValue;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ArrowUtils {
    @Nullable
    public static ItemStack getBow(@NotNull final Arrow arrow) {
        List<MetadataValue> values = arrow.getMetadata("shot-from");

        if (values.isEmpty()) {
            return null;
        }

        if (!(values.get(0).value() instanceof ItemStack)) {
            return null;
        }

        return (ItemStack) values.get(0).value();
    }

}
