package ir.wy.wycore.behind.event

import ir.wy.wycore.WyCore
import org.bukkit.Bukkit
import org.bukkit.event.HandlerList
import org.bukkit.event.Listener

class EventManager(private val plugin: WyCore) : EventHandling {
    override fun registerListener(listener: Listener) {
        Bukkit.getPluginManager().registerEvents(listener, plugin)
    }

    override fun unregisterListener(listener: Listener) {
        HandlerList.unregisterAll(listener)
    }

    override fun unregisterAllListeners() {
        HandlerList.unregisterAll(plugin)
    }
}