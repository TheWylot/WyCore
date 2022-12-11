package ir.wy.wycore.spigot.support.afk

import com.Zrips.CMI.CMI
import org.bukkit.entity.Player

class CMIAFK : AFKSupport {
    override fun getPluginName(): String {
        return "CMI"
    }

    override fun isAfk(player: Player): Boolean {
        return CMI.getInstance().playerManager.getUser(player)?.isAfk ?: false
    }
}