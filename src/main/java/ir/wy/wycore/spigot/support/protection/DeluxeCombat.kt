package ir.wy.wycore.spigot.support.protection

import ir.wy.wycore.behind.support.protection.ProtectionSupport

import nl.marido.deluxecombat.api.DeluxeCombatAPI
import org.bukkit.Location
import org.bukkit.block.Block
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player

class DeluxeCombat : ProtectionSupport {
    override fun getPluginName(): String {
        return "DeluxeCombat"
    }

    override fun canBreakBlock(player: Player, block: Block): Boolean {
        return true
    }

    override fun canCreateExplosion(player: Player, location: Location): Boolean {
        return true
    }

    override fun canPlaceBlock(player: Player, block: Block): Boolean {
        return true
    }

    override fun canInjure(player: Player, victim: LivingEntity): Boolean {
        val api = DeluxeCombatAPI()
        return when(victim) {
            is Player -> {
                if (api.hasProtection(player) || !api.hasPvPEnabled(player)) false
                else !api.hasProtection(victim) && api.hasPvPEnabled(victim) || api.isInCombat(victim)}
            else -> true
        }
    }

    override fun canPickupItem(player: Player, location: Location): Boolean {
        return true
    }
}