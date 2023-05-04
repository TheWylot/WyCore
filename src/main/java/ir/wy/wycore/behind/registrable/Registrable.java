package ir.wy.wycore.behind.registrable;

import org.jetbrains.annotations.NotNull;


public interface Registrable {
    @NotNull
    String getID();

    default void onRegister() {
        // Do nothing by default.
    }

    default void onRemove() {
        // Do nothing by default.
    }
}
