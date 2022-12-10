package ir.wy.wycore.support.hologram

import ir.wy.wycore.support.hologram.Hologram
import ir.wy.wycore.support.hologram.HologramSupport
import eu.decentsoftware.holograms.api.DHAPI
import org.bukkit.Location
import java.util.UUID

class DecentHologram : HologramSupport {
    override fun createHologram(location: Location, contents: MutableList<String>): Hologram {
        val id = UUID.randomUUID().toString()

        DHAPI.createHologram(id, location, contents)

        return HologramImplDecentHolograms(id)
    }

    override fun getPluginName(): String {
        return "DecentHolograms"
    }

    class HologramImplDecentHolograms(
        private val id: String,
    ) : Hologram {
        override fun remove() {
            DHAPI.getHologram(id)?.destroy()
        }

        override fun setContents(contents: MutableList<String>) {
            DHAPI.setHologramLines(DHAPI.getHologram(id), contents)
        }
    }
}