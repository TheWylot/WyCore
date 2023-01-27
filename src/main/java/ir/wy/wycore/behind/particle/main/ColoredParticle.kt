package ir.wy.wycore.behind.particle.main

import ir.wy.wycore.behind.particle.ParticleFactory
import ir.wy.wycore.behind.particle.AvailableParticle
import org.bukkit.Color
import org.bukkit.Location
import org.bukkit.Particle

class ColoredParticle : ParticleFactory {
    override fun getNames() = listOf(
        "color",
        "hex",
        "rgb"
    )

    override fun create(key: String): AvailableParticle? {
        val hex = key.toIntOrNull(16) ?: return null
        val color = try {
            Color.fromRGB(hex)
        } catch (e: IllegalArgumentException) {
            return null
        }

        return AvailableParticleColor(Particle.DustOptions(color, 1.0f))
    }

    private class AvailableParticleColor(
        private val options: Particle.DustOptions
    ) : AvailableParticle {
        override fun spawn(location: Location, amount: Int) {
            val world = location.world ?: return

            world.spawnParticle(Particle.REDSTONE, location, amount, 0.0, 0.0, 0.0, 0.0, options)
        }
    }
}