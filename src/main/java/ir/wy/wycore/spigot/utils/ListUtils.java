package ir.wy.wycore.spigot.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


public final class ListUtils {
    @NotNull
    public static <T> List<List<T>> create2DList(final int rows,
                                                 final int columns) {
        List<List<T>> list = new ArrayList<>(rows);
        while (list.size() < rows) {
            List<T> row = new ArrayList<>(columns);
            while (row.size() < columns) {
                row.add(null);
            }
            list.add(row);
        }

        return list;
    }

    @NotNull
    public static <T> Map<T, Integer> listToFrequencyMap(@NotNull final List<T> list) {
        Map<T, Integer> frequencyMap = new HashMap<>();
        for (T object : list) {
            if (frequencyMap.containsKey(object)) {
                frequencyMap.put(object, frequencyMap.get(object) + 1);
            } else {
                frequencyMap.put(object, 1);
            }
        }

        return frequencyMap;
    }


    @NotNull
    public static <T> List<T> toSingletonList(@Nullable final T object) {
        if (object == null) {
            return Collections.emptyList();
        } else {
            return Collections.singletonList(object);
        }
    }

    @Nullable
    public static <T> T getOrNull(@Nullable final List<T> list,
                                  final int index) {
        if (list == null) {
            return null;
        }

        return index >= 0 && index < list.size() ? list.get(index) : null;
    }

    public static boolean containsIgnoreCase(@NotNull final Iterable<String> list,
                                             @NotNull final String element) {
        for (String s : list) {
            if (s.equalsIgnoreCase(element)) {
                return true;
            }
        }

        return false;
    }

}
