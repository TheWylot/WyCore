package ir.wy.wycore.bungee;

public interface BungeeMessage {
    static BungeeMessage getByName(BungeeListener listener, String name) {
        final BungeeMessage[] actions = listener.getActions();

        for (final BungeeMessage action : actions)
            if (action.name().equals(name))
                return action;

        return null;
    }

    Class<?>[] getContent();

    String name();
}
