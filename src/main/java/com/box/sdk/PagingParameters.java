package com.box.sdk;

class PagingParameters {
    private static final int MAXIMUM_ALLOWED_OFFSET = 300_000;
    private final long limit;
    private final boolean useMarker;
    private final Long offset;
    private final String marker;

    PagingParameters(long limit, boolean useMarker, Long offset, String marker) {
        this.limit = limit;
        this.useMarker = useMarker;
        this.offset = offset;
        this.marker = marker;
    }

    static PagingParameters marker(long limit) {
        return new PagingParameters(limit, true, null, null);
    }

    static PagingParameters marker(String marker, long limit) {
        return new PagingParameters(limit, true, null, marker);
    }

    static PagingParameters offset(long offset, long limit) {
        if (offset > MAXIMUM_ALLOWED_OFFSET) {
            throw new IllegalArgumentException(
                "The maximum offset for offset-based pagination is 300000."
                    + " Marker-based pagination is recommended when a higher offset is needed."
            );
        }
        return new PagingParameters(limit, false, offset, null);
    }

    public QueryStringBuilder asQueryStringBuilder() {
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

    public boolean isMarkerBasedPaging() {
        return useMarker;
    }

    public PagingParameters nextMarker(String nextMarker) {
        if (!useMarker) {
            throw new IllegalArgumentException(
                "Cannot change offset paging to marker based paging. Use PagingParameters#nextOffset(long)."
            );
        }
        return PagingParameters.marker(nextMarker, limit);
    }

    public PagingParameters nextOffset(long nextOffset) {
        if (useMarker) {
            throw new IllegalArgumentException(
                "Cannot change marker paging to offset based paging. Use PagingParameters#nextMarker(String)."
            );
        }
        return PagingParameters.offset(nextOffset + limit, limit);
    }

    public long getLimit() {
        return limit;
    }
}
