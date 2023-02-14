package ir.wy.wycore.spigot.support.anticheat

import de.jpx3.intave.access.check.event.IntaveViolationEvent
import ir.wy.wycore.behind.support.anticheat.AntiCheatSupport
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import java.util.UUID

class IntaveAntiCheat : AntiCheatSupport, Listener{
    private val exempt: MutableSet<UUID> = HashSet()

    override fun getPluginName(): String {
        return "Intave"
    }

    override fun exempt(player: Player) {
        exempt.add(player.uniqueId)
    }

    override fun unexempt(player: Player) {
        exempt.remove(player.uniqueId)
    }

    @EventHandler(priority = EventPriority.LOWEST)
    private fun onViolate(event: IntaveViolationEvent) {
        if(!exempt.contains(event.player().uniqueId)) {
            return
        }
        event.reaction().equals(true)
    }
}