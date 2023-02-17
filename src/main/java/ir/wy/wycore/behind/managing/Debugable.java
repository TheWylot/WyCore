package ir.wy.wycore.behind.managing;

import org.jetbrains.annotations.Nullable;
import java.util.function.Predicate;

public interface Debugable<T> extends Predicate<T> {
    boolean matches(@Nullable T other);

    @Override
    default boolean test(@Nullable T other) {
        return this.matches(other);
    }
}
