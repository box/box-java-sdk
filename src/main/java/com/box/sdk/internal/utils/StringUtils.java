package com.box.sdk.internal.utils;

import java.util.Collection;
import java.util.Iterator;

/**
 * {@link String} related utilities.
 *
 * @author Stanislav Dvorscak
 *
 */
public class StringUtils {

    /**
     * Only static members.
     */
    protected StringUtils() {
    }

    /**
     * Joins a provided collection of {@link String}-s via provided delimiter.
     *
     * @param delimiter
     *            for join
     * @param values
     *            for joining
     * @return joined {@link String} / null in case that collection is null or empty
     */
    public static String join(String delimiter, Collection<String> values) {
        if (values == null || values.isEmpty()) {
            return null;
        }

        Iterator<String> valuesIterator = values.iterator();

        StringBuilder result = new StringBuilder();

        // first value is without delimiter
        result.append(valuesIterator.next());

        // join next values via delimiter
        while (valuesIterator.hasNext()) {
            result.append(delimiter).append(valuesIterator.next());
        }

        return result.toString();
    }

}
