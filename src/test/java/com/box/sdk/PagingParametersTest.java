package com.box.sdk;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Test;

public class PagingParametersTest {
    @Test
    public void returnsAsQueryStringBuilderForOffsetPagination() {
        PagingParameters pagingParameters = PagingParameters.offset(20, 130);

        QueryStringBuilder queryStringBuilder = pagingParameters.asQueryStringBuilder();
        assertThat(queryStringBuilder.toString(), is("?limit=20&offset=130"));
    }

    @Test
    public void returnsAsQueryStringBuilderWhenStartingMarkerPagination() {
        PagingParameters pagingParameters = PagingParameters.marker(20);

        QueryStringBuilder queryStringBuilder = pagingParameters.asQueryStringBuilder();
        assertThat(queryStringBuilder.toString(), is("?limit=20&usemarker=true"));
    }

    @Test
    public void returnsAsQueryStringBuilderWhenUsingMarkerPagination() {
        PagingParameters pagingParameters = PagingParameters.marker(20, "34332423");

        QueryStringBuilder queryStringBuilder = pagingParameters.asQueryStringBuilder();
        assertThat(queryStringBuilder.toString(), is("?limit=20&usemarker=true&marker=34332423"));
    }

    @Test
    public void returnsAsQueryStringBuilderWhenUsingNextMarker() {
        PagingParameters pagingParameters = PagingParameters.marker(20).nextMarker("34332423");

        QueryStringBuilder queryStringBuilder = pagingParameters.asQueryStringBuilder();
        assertThat(queryStringBuilder.toString(), is("?limit=20&usemarker=true&marker=34332423"));
    }
}