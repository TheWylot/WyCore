package ir.wy.wycore.support.afk

import com.Zrips.CMI.CMI
import ir.wy.wycore.support.afk.AFKSupport
import org.bukkit.entity.Player

class CMIAFK : AFKSupport {
    override fun getPluginName(): String {
        return "CMI"
    }

    override fun isAfk(player: Player): Boolean {
        return CMI.getInstance().playerManager.getUser(player)?.isAfk ?: false
    }
}