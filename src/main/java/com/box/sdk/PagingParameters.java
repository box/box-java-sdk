package com.box.sdk;

class PagingParameters {
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

    static PagingParameters marker(long limit, String marker) {
        return new PagingParameters(limit, true, null, marker);
    }

    static PagingParameters offset(long limit, long offset) {
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
        return PagingParameters.marker(limit, nextMarker);
    }

    public long getLimit() {
        return limit;
    }

    public PagingParameters nextOffset(long nextOffset) {
        return PagingParameters.offset(limit, nextOffset + limit);
    }
}
