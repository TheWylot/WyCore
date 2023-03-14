package ir.wy.wycore.proxy.utils

import org.bukkit.Location;
import org.bukkit.entity.Entity;

interface EmptyEntity {
    fun createEmptyEntity(
        location: Location
    ): Entity

}