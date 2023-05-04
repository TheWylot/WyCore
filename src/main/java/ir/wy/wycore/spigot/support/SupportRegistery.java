package ir.wy.wycore.spigot.support;

import ir.wy.wycore.WyCore;
import ir.wy.wycore.behind.WyBase;
import ir.wy.wycore.behind.registrable.Registery;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class SupportRegistery<T extends Support> extends Registery<T> {
    @Override
    public @NotNull T register(@NotNull final T element) {
        return executeSafely(() -> super.register(element), element);
    }

    public void forEachSafely(@NotNull final Consumer<T> action) {
        for (T integration : new HashSet<>(this.values())) {
            executeSafely(() -> action.accept(integration), integration);
        }
    }

    public boolean anySafely(@NotNull final Predicate<T> predicate) {
        for (T integration : new HashSet<>(this.values())) {
            Boolean result = executeSafely(() -> predicate.test(integration), integration);
            if (result != null && result) {
                return true;
            }
        }
        return false;
    }

    @NotNull
    public <R> R firstSafely(@NotNull final Function<T, R> function,
                             @NotNull final R defaultValue) {
        if (this.isEmpty()) {
            return defaultValue;
        }

        T integration = this.iterator().next();

        return executeSafely(() -> function.apply(integration), integration, defaultValue);
    }

    private void executeSafely(@NotNull final Runnable action,
                               @NotNull final T integration) {
        executeSafely(() -> {
            action.run();
            return null;
        }, integration);
    }

    private <R> R executeSafely(@NotNull final Supplier<R> action,
                                @NotNull final T integration) {
        return executeSafely(action, integration, null);
    }

    private <R> R executeSafely(@NotNull final Supplier<R> action,
                                @NotNull final T integration,
                                @Nullable final R defaultValue) {
        try {
            return action.get();
        } catch (final Exception e) {
            e.printStackTrace();
            this.remove(integration);
            return defaultValue;
        }
    }

    public boolean allSafely(@NotNull final Predicate<T> predicate) {
        return !this.anySafely(predicate.negate());
    }
}
