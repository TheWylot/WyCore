package ir.wy.wycore.behind.entity.parser;

import org.bukkit.entity.Entity;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;
import java.util.function.Predicate;

public record EntityParserFinal(@NotNull Predicate<Entity> test,
                                @NotNull Consumer<Entity> modifier) {

    public Predicate<Entity> entityPredicate() {
        return test;
    }

    public Consumer<Entity> entityConsumer() {
        return modifier;
    }
}
