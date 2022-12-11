package ir.wy.wycore.behind.event

import org.bukkit.event.Listener

interface EventHandling {
    fun registerListener(listener: Listener)
    fun unregisterListener(listener: Listener)
    fun unregisterAllListeners()
}