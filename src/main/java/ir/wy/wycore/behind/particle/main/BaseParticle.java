package ir.wy.wycore.behind.particle.main;

import ir.wy.wycore.behind.particle.AvailableParticle;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.jetbrains.annotations.NotNull;

public final class BaseParticle implements AvailableParticle {

    private final Particle particle;

    public BaseParticle(@NotNull final Particle particle) {
        this.particle = particle;
    }

    @Override
    public void spawn(@NotNull final Location location,
                      final int amount) {
        World world = location.getWorld();

        if (world == null) {
            return;
        }
        world.spawnParticle(particle, location, amount, 0, 0, 0, 0, null);
    }
}
