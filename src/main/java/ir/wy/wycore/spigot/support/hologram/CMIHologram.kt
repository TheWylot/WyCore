package ir.wy.wycore.spigot.support.hologram

import com.Zrips.CMI.CMI
import com.Zrips.CMI.Modules.Holograms.CMIHologram
import ir.wy.wycore.behind.support.hologram.Hologram
import ir.wy.wycore.behind.support.hologram.HologramSupport
import net.Zrips.CMILib.Container.CMILocation
import org.bukkit.Location
import java.util.UUID

@Suppress("DEPRECATION")
class CMIHologram : HologramSupport {

    override fun createHologram(location: Location, contents: MutableList<String>): Hologram {
        val cmiHolo = CMIHologram(UUID.randomUUID().toString(), CMILocation(location))
        CMI.getInstance().hologramManager.addHologram(cmiHolo)

        val holo = HologramImplCMI(cmiHolo)
        holo.setContents(contents)

        cmiHolo.enable()

        return holo
    }

    override fun getPluginName(): String {
        return "CMI"
    }

    class HologramImplCMI(
        private val handle: CMIHologram
    ) : Hologram {
        override fun remove() {
            CMI.getInstance().hologramManager.removeHolo(handle)
        }

        override fun setContents(contents: MutableList<String>) {
            handle.lines = contents
        }
    }
}