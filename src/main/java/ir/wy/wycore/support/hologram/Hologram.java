package ir.wy.wycore.support.hologram;

import org.jetbrains.annotations.NotNull;
import java.util.List;

public interface Hologram {

    void remove();

    void setContents(@NotNull List<String> contents);
}
