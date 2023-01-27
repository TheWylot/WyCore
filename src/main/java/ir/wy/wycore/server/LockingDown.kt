package ir.wy.wycore.server

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerLoginEvent

class LockingDown : Listener {
    private var lockReason: String? = null

    @Suppress("DEPRECATION")
    @EventHandler
    fun handle(event: PlayerLoginEvent) {
        if (lockReason != null) {
            event.disallow(
                PlayerLoginEvent.Result.KICK_OTHER,
                lockReason!!
            )
        }
    }

    fun lock(reason: String) {
        lockReason = reason
    }

    fun unlock() {
        lockReason = null
    }
}