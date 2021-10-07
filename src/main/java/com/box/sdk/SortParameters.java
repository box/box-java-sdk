package com.box.sdk;

import static com.box.sdk.BoxFolder.SortDirection.ASC;
import static com.box.sdk.BoxFolder.SortDirection.DESC;

/**
 * Represents sorting parameters.
 */
public final class SortParameters {
    private final String fieldName;
    private final BoxFolder.SortDirection sortDirection;

    /**
     * Creates sorting parameters.
     *
     * @param fieldName     Name of the field used to sort.
     * @param sortDirection Direction of the sort.
     */
    private SortParameters(String fieldName, BoxFolder.SortDirection sortDirection) {
        this.fieldName = fieldName;
        this.sortDirection = sortDirection;
    }

    /**
     * Creates ascending sorting by specified field name.
     *
     * @param fieldName Name of the field used to sort.
     * @return Sort parameters.
     */
    public static SortParameters ascending(String fieldName) {
        return new SortParameters(fieldName, ASC);
    }

    /**
     * Creates descending sorting by specified field name.
     *
     * @param fieldName Name of the field used to sort.
     * @return Sort parameters.
     */
    public static SortParameters descending(String fieldName) {
        return new SortParameters(fieldName, DESC);
    }

    QueryStringBuilder asQueryStringBuilder() {
        return new QueryStringBuilder()
            .appendParam("sort", fieldName)
            .appendParam("direction", sortDirection.name());
    }
}
