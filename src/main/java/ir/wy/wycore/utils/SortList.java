package ir.wy.wycore.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class SortList<T> extends ArrayList<T> {
    private final Comparator<T> comparator;

    public SortList(final Comparator<T> comparator) {
        this.comparator = comparator;
    }

    @Override
    public boolean add (T t) {
        int index = Collections.binarySearch(this, t, comparator);
        if (index < 0) index = ~index;
        super.add(index, t);
        return true;
    }
}
