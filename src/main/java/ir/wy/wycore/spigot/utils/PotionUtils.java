package ir.wy.wycore.spigot.utils;

import org.bukkit.potion.PotionData;
import org.jetbrains.annotations.NotNull;

public final class PotionUtils {
    public static int getDuration(@NotNull final PotionData data) {
        if (data.isExtended()) {
            return switch (data.getType()) {
                case INSTANT_DAMAGE, INSTANT_HEAL:
                    yield 0;
                case POISON, REGEN:
                    yield 1800;
                case SLOW_FALLING, WEAKNESS, SLOWNESS:
                    yield 4800;
                case TURTLE_MASTER:
                    yield 800;
                default:
                    yield 9600;
            };
        }

        if (data.isUpgraded()) {
            return switch (data.getType()) {
                case INSTANT_DAMAGE, INSTANT_HEAL:
                    yield 0;
                case POISON, REGEN:
                    yield 420;
                case SLOW_FALLING, WEAKNESS, SLOWNESS:
                    yield 440;
                case TURTLE_MASTER:
                    yield 400;
                default:
                    yield 1800;
            };
        }

        return switch (data.getType()) {
            case INSTANT_DAMAGE, INSTANT_HEAL:
                yield 0;
            case POISON, REGEN:
                yield 900;
            case SLOW_FALLING, WEAKNESS, SLOWNESS:
                yield 400;
            case TURTLE_MASTER:
                yield 1800;
            default:
                yield 3600;
        };
    }
}
