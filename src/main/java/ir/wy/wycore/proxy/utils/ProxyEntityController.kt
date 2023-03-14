package ir.wy.wycore.proxy.utils

import ir.wy.wycore.behind.entity.ai.EntityController
import org.bukkit.entity.Mob

interface ProxyEntityController {
    fun <T: Mob> createEntityController(
        entity: T
    ): EntityController<T>
}