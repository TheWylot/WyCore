package ir.wy.wycore.proxy.behind

import org.bukkit.Bukkit
import java.util.*

open class Versions private constructor() {
    init {
        throw UnsupportedOperationException("This is a utility class and cannot be instantiated")
    }

    companion object {
        val NMS_VERSION =
            Bukkit.getServer().javaClass.getPackage().name.split("\\.".toRegex()).dropLastWhile { it.isEmpty() }
                .toTypedArray()[3]
        val SUPPORTED_VERSIONS = Arrays.asList( // i dont like to support other nms versions, maybe 1.16.5 at least
            "v1_17_R1",
            "v1_18_R1",
            "v1_18_R2",
            "v1_19_R1"
        )
    }
}