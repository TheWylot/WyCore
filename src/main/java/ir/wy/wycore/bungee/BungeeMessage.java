package ir.wy.wycore.bungee;

public interface BungeeMessage {
    Class<?>[] getContent();
    String name();

    static BungeeMessage getByName(BungeeListener listener, String name) {
        final BungeeMessage[] actions = listener.getActions();

        for (final BungeeMessage action : actions)
            if (action.name().equals(name))
                return action;

            return null;
    }
}
