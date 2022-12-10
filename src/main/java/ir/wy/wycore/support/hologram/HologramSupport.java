package ir.wy.wycore.support.hologram;

import ir.wy.wycore.support.Support;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface HologramSupport extends Support {

    Hologram createHologram(@NotNull Location location, @NotNull List<String> contents);
}
