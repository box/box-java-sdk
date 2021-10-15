package com.box.sdk;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Test;

public class SortParametersTest {
    @Test
    public void returnsAsQueryStringBuilderForAscendingSorting() {
        SortParameters sortParameters = SortParameters.ascending("field_123");

        QueryStringBuilder queryStringBuilder = sortParameters.asQueryStringBuilder();
        assertThat(queryStringBuilder.toString(), is("?sort=field_123&direction=ASC"));
    }

    @Test
    public void returnsAsQueryStringBuilderForDescendingSorting() {
        SortParameters sortParameters = SortParameters.descending("field_123");

        QueryStringBuilder queryStringBuilder = sortParameters.asQueryStringBuilder();
        assertThat(queryStringBuilder.toString(), is("?sort=field_123&direction=DESC"));
    }
}
