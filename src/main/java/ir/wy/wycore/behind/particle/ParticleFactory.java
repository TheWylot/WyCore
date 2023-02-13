package ir.wy.wycore.behind.particle;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public interface ParticleFactory {

    @NotNull List<String> getNames();

    @Nullable AvailableParticle create(@NotNull String key);
}
