package ir.wy.wycore.behind.proxy.errors;

import org.jetbrains.annotations.NotNull;

public class ProxyExcp extends Error {

    public ProxyExcp(@NotNull final String message, @NotNull final Throwable cause) {
        super(message, cause);
    }
}
