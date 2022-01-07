package de.borekking.listsAndTrees.util;

public class ComparatorUtils {

    public static <T extends Comparable<T>> boolean isEqual(T o1, T o2) {
        return o1.compareTo(o2) == 0;
    }

    public static <T extends Comparable<T>> boolean isLess(T o1, T o2) {
        return o1.compareTo(o2) < 0;
    }

    public static <T extends Comparable<T>> boolean isGreater(T o1, T o2) {
        return o1.compareTo(o2) > 0;
    }
}
