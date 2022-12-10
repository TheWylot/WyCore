package ir.wy.wycore.support.anticheat


import me.konsolas.aac.api.AACExemption;
import me.konsolas.aac.api.AACAPI
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.event.Listener

class AACAntiCheat : AntiCheatSupport, Listener {
    private val wyAACExemption = AACExemption("wy")
    private val api = Bukkit.getServicesManager().load(AACAPI::class.java)!!

    override fun getPluginName(): String {
        return "AAC"
    }

    override fun exempt(player: Player) {
        api.addExemption(player, wyAACExemption)
    }

    override fun unexempt(player: Player) {
        api.removeExemption(player, wyAACExemption)
    }
}