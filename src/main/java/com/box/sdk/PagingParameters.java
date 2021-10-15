package com.box.sdk;

/**
 * Abstraction on how SDK is doing pagination. Can be used to start offset or marker based pagination.
 */
public final class PagingParameters {
    /**
     * Default limit value.
     */
    public static final long DEFAULT_LIMIT = 1000;
    private static final int MAXIMUM_ALLOWED_OFFSET = 300_000;
    private final long limit;
    private final boolean useMarker;
    private final Long offset;
    private final String marker;

    private PagingParameters(long limit, boolean useMarker, Long offset, String marker) {
        this.limit = limit;
        this.useMarker = useMarker;
        this.offset = offset;
        this.marker = marker;
    }

    /**
     * Starts marker based pagination.
     * @param limit how many elements per request should be fetched.
     * @return PagingParameters setup to start marker based pagination.
     */
    public static PagingParameters marker(long limit) {
        return new PagingParameters(limit, true, null, null);
    }

    /**
     * Starts offset based pagination.
     * @param offset where offset pagination should start. Offset cannot be larger than 300000.
     * @param limit how many elements per request should be fetched.
     * @return PagingParameters setup to start offset based pagination.
     */
    public static PagingParameters offset(long offset, long limit) {
        if (offset > MAXIMUM_ALLOWED_OFFSET) {
            throw new IllegalArgumentException(
                "The maximum offset for offset-based pagination is 300000."
                    + " Marker-based pagination is recommended when a higher offset is needed."
            );
        }
        return new PagingParameters(limit, false, offset, null);
    }

    QueryStringBuilder asQueryStringBuilder() {
        QueryStringBuilder result = new QueryStringBuilder()
            .appendParam("limit", limit);
        if (useMarker) {
            result.appendParam("usemarker", "true");
            if (marker != null) {
                result.appendParam("marker", marker);
            }
        } else {
            result.appendParam("offset", offset);
        }
        return result;
    }

    boolean isMarkerBasedPaging() {
        return useMarker;
    }

    PagingParameters nextMarker(String nextMarker) {
        if (!useMarker) {
            throw new IllegalArgumentException(
                "Cannot change offset paging to marker based paging. Use PagingParameters#nextOffset(long)."
            );
        }
        return new PagingParameters(limit, true, null, nextMarker);
    }

    PagingParameters nextOffset(long nextOffset) {
        if (useMarker) {
            throw new IllegalArgumentException(
                "Cannot change marker paging to offset based paging. Use PagingParameters#nextMarker(String)."
            );
        }
        return PagingParameters.offset(nextOffset + limit, limit);
    }

    long getLimit() {
        return limit;
    }
}
