package ir.wy.wycore.spigot.support.metrics

import ir.wy.wycore.WyCore

class MetricsSupport {
    fun createMetrics(plugin: WyCore) {
        Metrics(plugin)
    }
}