package ir.wy.wycore.behind;

import ir.wy.wycore.behind.event.EventManager;
import ir.wy.wycore.behind.registrable.Registrable;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class WyPlugin extends JavaPlugin implements Registrable {

    private final EventManager eventManager;

    protected WyPlugin(EventManager eventManager) {
        this.eventManager = eventManager;
    }

    public EventManager getEventManager() {
        return this.eventManager;
    }
}
