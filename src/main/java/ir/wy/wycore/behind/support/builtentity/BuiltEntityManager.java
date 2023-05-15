package ir.wy.wycore.behind.support.builtentity;

import ir.wy.wycore.spigot.support.SupportRegistery;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;
public final class BuiltEntityManager {

    private static final SupportRegistery<BuiltEntitySupport> REGISTERY = new SupportRegistery<>();


    @Deprecated
    private static final Set<BuiltEntitySupport> REGISTERED = new HashSet<>();

    public static void register(@NotNull final BuiltEntitySupport Support) {
        REGISTERY.register(Support);
    }

    public static void registerAllEntities() {
        REGISTERY.forEachSafely(BuiltEntitySupport::registerAllEntities);
    }
}
