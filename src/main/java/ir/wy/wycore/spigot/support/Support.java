package ir.wy.wycore.spigot.support;

import ir.wy.wycore.behind.registrable.Registery;
import ir.wy.wycore.behind.registrable.Registrable;
import org.jetbrains.annotations.NotNull;

public interface Support extends Registrable {
    String getPluginName();

    @Override
    default @NotNull String getID() {
        return Registery.tryFitPattern(this.getPluginName());
    }
}
