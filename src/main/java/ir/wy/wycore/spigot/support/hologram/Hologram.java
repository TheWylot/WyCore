package ir.wy.wycore.spigot.support.hologram;

import org.jetbrains.annotations.NotNull;
import java.util.List;

public interface Hologram {

    void remove();

    void setContents(@NotNull List<String> contents);
}
