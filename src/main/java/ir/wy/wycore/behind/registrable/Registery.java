package ir.wy.wycore.behind.registrable;

import org.apache.commons.lang.Validate;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

public class Registery<T extends Registrable> implements Iterable<T> {
    private static final Pattern ID_PATTERN = Pattern.compile("[a-z0-9_]{1,100}");

    private final Map<String, T> registry = new HashMap<>();

    /**
     * If the registry is locked.
     */
    private boolean isLocked = false;


    @Nullable
    private Object locker = null;

    /**
     * Instantiate a new registry.
     */
    public Registery() {

    }

    @NotNull
    public T register(@NotNull final T element) {
        if (this.isLocked) {
            throw new IllegalStateException("Cannot add to locked registry! (ID: " + element.getID() + ")");
        }

        Validate.isTrue(ID_PATTERN.matcher(element.getID()).matches(), "ID must match pattern: " + ID_PATTERN.pattern() + " (was " + element.getID() + ")");

        registry.put(element.getID(), element);

        element.onRegister();

        return element;
    }

    public T remove(@NotNull final T element) {
        if (this.isLocked) {
            throw new IllegalStateException("Cannot remove from locked registry! (ID: " + element.getID() + ")");
        }

        element.onRemove();

        registry.remove(element.getID());

        return element;
    }

    @Nullable
    public T remove(@NotNull final String id) {
        if (this.isLocked) {
            throw new IllegalStateException("Cannot remove from locked registry! (ID: " + id + ")");
        }

        T element = registry.get(id);

        if (element != null) {
            element.onRemove();
        }

        return registry.remove(id);
    }


    @Nullable
    public T get(@NotNull final String id) {
        return registry.get(id);
    }


    public void clear() {
        for (T value : Set.copyOf(registry.values())) {
            remove(value);
        }
    }

    public Set<T> values() {
        return Set.copyOf(registry.values());
    }

    public boolean isLocked() {
        return isLocked;
    }

    public void lock(@Nullable final Object locker) {
        this.locker = locker;
        isLocked = true;
    }

    public void unlock(@Nullable final Object locker) {
        if (this.locker != locker) {
            throw new IllegalArgumentException("Cannot unlock registry!");
        }
        isLocked = false;
    }

    public boolean isEmpty() {
        return registry.isEmpty();
    }

    public boolean isNotEmpty() {
        return !isEmpty();
    }

    @NotNull
    @Override
    public Iterator<T> iterator() {
        return values().iterator();
    }

    @NotNull
    public static String tryFitPattern(@NotNull final String string) {
        return string.replace(" ", "_")
                .replace(".", "_")
                .replace("-", "_")
                .toLowerCase(Locale.ENGLISH);
    }
}
