package ir.wy.wycore.behind.entity.parser;

import ir.wy.wycore.behind.entity.DebugableEntity;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface EntityParser {

    @Nullable EntityParserFinal parseArguments(@NotNull String[] args);
}
