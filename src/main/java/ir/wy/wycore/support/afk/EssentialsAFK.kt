package ir.wy.wycore.support.afk

import com.earth2me.essentials.Essentials
import ir.wy.wycore.support.afk.AFKSupport
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin

class EssentialsAFK : AFKSupport {
    private val ess = JavaPlugin.getPlugin(Essentials::class.java)

    override fun isAfk(player: Player): Boolean {
        return ess.getUser(player) != null && ess.getUser(player).isAfk
    }

    override fun getPluginName(): String {
        return "Essentials"
    }
}