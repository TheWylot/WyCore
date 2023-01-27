package ir.wy.wycore.spigot.support

import org.bukkit.Bukkit
import org.bukkit.plugin.Plugin
import java.util.*
import java.util.stream.Collectors

class SupportManager(
    val pluginName: String,
    private val runnable: Runnable
) {

    fun loadIfPresent() {
        if (LOADED_PLUGINS.contains(pluginName.lowercase(Locale.getDefault()))) {
            load()
        }
    }

    fun load() {
        runnable.run()
    }

    companion object {
        private val LOADED_PLUGINS = Arrays.stream(Bukkit.getPluginManager().plugins)
            .map { obj: Plugin -> obj.name }
            .map { obj: String -> obj.lowercase(Locale.getDefault()) }
            .collect(Collectors.toSet())
    }
}