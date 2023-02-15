package ir.wy.wycore.behind.packet;

import org.jetbrains.annotations.NotNull;

public interface PacketSorting {

    boolean NORMAL = true;

    default void onSend(@NotNull final PacketEvent event) {
        // Override when needed.
    }

    default void onReceive(@NotNull final PacketEvent event) {
        // Override when needed.
    }


    default boolean getPriority() {
        return PacketSorting.NORMAL;
    }
}
