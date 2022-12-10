package ir.wy.wycore.support.placeholder

import ir.wy.wycore.support.placeholder.PlaceholderSupport
import me.clip.placeholderapi.PlaceholderAPI
import org.bukkit.entity.Player
import java.util.regex.Pattern

class PAPISupport : PlaceholderSupport {
    private val pattern = Pattern.compile("[%]([^% ]+)[%]")

    override fun registerSupport() {
        // - lmao nothing
    }

    override fun getPluginName(): String {
        return "PlaceholderAPI"
    }

    override fun translate(text: String, player: Player?): String {
        return PlaceholderAPI.setPlaceholders(player, text)
    }

    override fun findPlaceholdersIn(text: String): MutableList<String> {
        val placeholders = mutableListOf<String>()
        val matcher = pattern.matcher(text)
        while (matcher.find()) {
            placeholders.add(matcher.group())
        }
        return placeholders
    }
}