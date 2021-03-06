package ir.wy.wycore;

import ir.wy.wycore.gui.*;
import ir.wy.wycore.Heart;
import io.papermc.lib.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.entity.HumanEntity;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.java.JavaPluginLoader;
import org.bukkit.scheduler.BukkitTask;

import java.io.File;

@Getter
@NoArgsConstructor
public class WyCore extends JavaPlugin {
    private BukkitTask saveTask;
    private boolean isTesting = false;

    private Heart heart;
    private static WyCore instance;

    // Testing & Debug Logger
    public WyCore(JavaPluginLoader loader, PluginDescriptionFile description, File dataFolder, File file) {
        super(loader, description, dataFolder, file);
        this.isTesting = true;
        getLogger().setFilter(record -> false);
    }

    @Override
    public void onLoad() {
        getDataFolder().mkdirs();

        this.heart = new Heart(Heart.HeartType.YAML, this);
        loadConfigs();
        saveConfigs();
    }
    @Override
    public void onEnable() {
        instance = this;

        if(!PaperLib.isSpigot() && !isTesting) {
            getLogger().warning("Use Spigot and other forks etc Paper instead of craftbukkit or sponge.");
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }

        registerListeners();

        if (isTesting) return;

        // Saving Data
        saveTask = Bukkit.getScheduler().runTaskTimerAsynchronously(this, this::saveData, 0, 20 * 60 * 5);

        // Inventory Handler & Auto Updater
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, () -> Bukkit.getServer().getOnlinePlayers().forEach(player -> {
            InventoryHolder inventoryHolder = player.getOpenInventory().getTopInventory().getHolder();
            if (inventoryHolder instanceof GUI) {
                ((GUI) inventoryHolder).addContent(player.getOpenInventory().getTopInventory());
            }
        }), 0, 1);
    }

    @Override
    public void onDisable() {
        if (isTesting) return;
        if (saveTask != null) saveTask.cancel();
        saveData();
        Bukkit.getOnlinePlayers().forEach(HumanEntity::closeInventory);
        getLogger().info("-------------------------------");
        getLogger().info("");
        getLogger().info(getDescription().getName() + " Ghat Shode.");
        getLogger().info("");
        getLogger().info("-------------------------------");
    }

    public void registerListeners() {
    }

    public void saveData(){
    }

    public void loadConfigs() {
    }

    public void saveConfigs() {
    }

    public static WyCore getInstance() {
        return instance;
    }
}
