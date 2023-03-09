package ir.wy.wycore.spigot.support.anticheat

import ir.wy.wycore.behind.support.anticheat.AntiCheatSupport
import org.bukkit.entity.Player
import org.bukkit.event.Listener
import java.util.*
import kotlin.collections.HashSet

class PolarAntiCheat : AntiCheatSupport, Listener {
    private val exempt: MutableSet<UUID> = HashSet();

    override fun getPluginName(): String {
        return "Polar"
    }

    override fun exempt(player: Player) {
        exempt.add(player.uniqueId)
    }

    override fun unexempt(player: Player) {
        exempt.remove(player.uniqueId)
    }
}