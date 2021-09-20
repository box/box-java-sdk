package com.box.sdk.internal.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * {@link Collection} related utlities.
 */
public final class CollectionUtils {

    /**
     * Only static members.
     */
    private CollectionUtils() {
    }

    /**
     * Re-maps a provided collection.
     *
     * @param <T_Result> type of result
     * @param <T_Source> type of source
     * @param source     for mapping
     * @param mapper     element mapper
     * @return mapped source
     */
    public static <T_Result, T_Source> List<T_Result> map(Collection<T_Source> source,
                                                          Mapper<T_Result, T_Source> mapper) {
        List<T_Result> result = new LinkedList<T_Result>();
        for (T_Source element : source) {
            result.add(mapper.map(element));
        }
        return result;
    }

    /**
     * Creates list from iterable.
     *
     * @param iterable Iterable that will be used to create list.
     * @param <T>      Collection element
     * @return List with all items from iterable. Elements are in the same order as iterable is returning them.
     */
    public static <T> List<T> createListFrom(Iterable<T> iterable) {
        List<T> result = new ArrayList<T>();
        for (T element : iterable) {
            result.add(element);
        }
        return result;
    }

    public static <T> List<T> iterableToList(Iterable<T> iterable) {
        List<T> result = new ArrayList<T>();
        for (T i : iterable) {
            result.add(i);
        }
        return result;
    }

    /**
     * Contract for {@link Collection}-s mapping.
     *
     * @param <T_Result> type of result
     * @param <T_Source> type of source
     */
    public interface Mapper<T_Result, T_Source> {

        /**
         * Maps a provided value.
         *
         * @param input for mapping
         * @return mapped value
         */
        T_Result map(T_Source input);

    }
}
