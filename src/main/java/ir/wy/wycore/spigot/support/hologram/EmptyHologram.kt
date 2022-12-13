package ir.wy.wycore.spigot.support.hologram

import ir.wy.wycore.behind.support.hologram.Hologram

internal class EmptyHologram : Hologram {
    override fun remove() {
        // Nothing
    }

    override fun setContents(contents: List<String>) {
        // Nothing
    }
}