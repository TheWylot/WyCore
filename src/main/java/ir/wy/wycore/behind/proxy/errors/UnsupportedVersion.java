package ir.wy.wycore.behind.proxy.errors;

import ir.wy.wycore.behind.proxy.Versions;
import org.bukkit.Bukkit;

public class UnsupportedVersion extends Versions {
    public UnsupportedVersion() {
        super();
        Bukkit.getConsoleSender().sendMessage("You're running an unsupported server version: " + Versions.Companion.getNMS_VERSION());
    }
}
