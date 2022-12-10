package ir.wy.wycore.chat;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;

public class ChatInforms implements Listener {
    private static final List<UUID> registered = new ArrayList<>();

    private final Plugin plugin;
    private final ChatConfirmHandler handler;
    private int taskId;
    private OnClose onClose = null;
    private OnCancel onCancel = null;
    private Listener listener;

    private ChatInforms(Plugin plugin, Player player, ChatConfirmHandler handler) {
        this.plugin = plugin;
        this.handler = handler;

        registered.add(player.getUniqueId());
    }

    public static ChatInforms showPrompt(Plugin plugin, Player player, ChatConfirmHandler hander) {
        return showPrompt(plugin, player, null, hander);
    }

    public static ChatInforms showPrompt(Plugin plugin, Player player, String message, ChatConfirmHandler hander) {
        ChatInforms prompt = new ChatInforms(plugin, player, hander);
        prompt.startListener(plugin);
        player.closeInventory();

        if (message != null) {
            player.sendMessage(message);
        }

        return prompt;
    }

    public static boolean isRegistered(Player player) {
        return registered.contains(player.getUniqueId());
    }

    public static boolean unregister(Player player) {
        return registered.remove(player.getUniqueId());
    }

    public ChatInforms setOnClose(OnClose onClose) {
        this.onClose = onClose;
        return this;
    }

    public ChatInforms setOnCancel(OnCancel onCancel) {
        this.onCancel = onCancel;
        return this;
    }

    public ChatInforms setTimeOut(Player player, long ticks) {
        taskId = Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> {
            if (onClose != null) {
                plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, () ->
                        onClose.onClose(), 0L);
            }

            HandlerList.unregisterAll(listener);
            player.sendMessage("Your action has timed out.");
        }, ticks);

        return this;
    }

    private void startListener(Plugin plugin) {
        this.listener = new Listener() {
            @EventHandler(priority = EventPriority.LOWEST)
            public void onChat(AsyncPlayerChatEvent event) {
                Player player = event.getPlayer();

                if (!ChatInforms.isRegistered(player)) {
                    return;
                }

                ChatInforms.unregister(player);
                event.setCancelled(true);

                ChatConfirmEvent chatConfirmEvent = new ChatConfirmEvent(player, event.getMessage());

                player.sendMessage("\u00BB " + event.getMessage());

                try {
                    handler.onChat(chatConfirmEvent);
                } catch (Throwable t) {
                    plugin.getLogger().log(Level.SEVERE, "Failed to process chat prompt", t);
                }

                if (onClose != null) {
                    plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, () -> onClose.onClose(), 0L);
                }

                HandlerList.unregisterAll(listener);
                Bukkit.getScheduler().cancelTask(taskId);
            }

            @EventHandler(priority = EventPriority.LOWEST)
            public void onCancel(PlayerCommandPreprocessEvent event) {
                Player player = event.getPlayer();

                if (!ChatInforms.isRegistered(player)) {
                    return;
                }

                ChatInforms.unregister(player);

                if (event.getMessage().toLowerCase().startsWith("/cancel")) {
                    event.setCancelled(true);
                }

                if (onCancel != null) {
                    plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, () -> onCancel.onCancel(), 0L);
                } else if (onClose != null) {
                    plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, () -> onClose.onClose(), 0L);
                }

                HandlerList.unregisterAll(listener);
                Bukkit.getScheduler().cancelTask(taskId);
            }
        };

        Bukkit.getPluginManager().registerEvents(listener, plugin);
    }

    public interface ChatConfirmHandler {
        void onChat(ChatConfirmEvent event);
    }

    public interface OnClose {
        void onClose();
    }

    public interface OnCancel {
        void onCancel();
    }

    public static class ChatConfirmEvent {
        private final Player player;
        private final String message;

        public ChatConfirmEvent(Player player, String message) {
            this.player = player;
            this.message = message;
        }

        public Player getPlayer() {
            return player;
        }

        public String getMessage() {
            return message;
        }
    }
}
