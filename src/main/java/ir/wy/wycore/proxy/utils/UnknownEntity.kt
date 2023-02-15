package ir.wy.wycore.proxy.utils

import org.bukkit.Location
import org.bukkit.entity.Entity

interface UnknownEntity {
    fun createUnknownEntity(
        location: Location
    ): Entity
}