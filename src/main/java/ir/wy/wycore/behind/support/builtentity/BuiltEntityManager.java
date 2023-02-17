package ir.wy.wycore.behind.support.builtentity;

import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;
public class BuiltEntityManager {

    private static final Set<BuiltEntitySupport> REGISTERED = new HashSet<>();

    public static void register(@NotNull final BuiltEntitySupport Support) {
        REGISTERED.removeIf(it -> it.getPluginName().equalsIgnoreCase(Support.getPluginName()));
        REGISTERED.add(Support);
    }

    public static void registerAllEntities() {
        for (BuiltEntitySupport integration : REGISTERED) {
            integration.registerAllEntities();
        }
    }
}
