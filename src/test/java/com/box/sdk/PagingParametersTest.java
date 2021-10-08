package com.box.sdk;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThrows;

import org.junit.Test;
import org.junit.function.ThrowingRunnable;

public class PagingParametersTest {
    @Test
    public void returnsAsQueryStringBuilderForOffsetPagination() {
        PagingParameters pagingParameters = PagingParameters.offset(130, 20);

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
    public void returnsAsQueryStringBuilderWhenUsingNextMarker() {
        PagingParameters pagingParameters = PagingParameters.marker(20).nextMarker("34332423");

        QueryStringBuilder queryStringBuilder = pagingParameters.asQueryStringBuilder();
        assertThat(queryStringBuilder.toString(), is("?limit=20&usemarker=true&marker=34332423"));
    }

    @Test
    public void cannotSetOffsetLargerThan300000() {
        assertThrows(
            "The maximum offset for offset-based pagination is 300000."
                + " Marker-based pagination is recommended when a higher offset is needed.",
            IllegalArgumentException.class,
            new ThrowingRunnable() {
                @Override
                public void run() {
                    PagingParameters.offset(300001, 10);
                }
            }
        );
    }

    @Test
    public void cannotContinueWithOffsetLargerThan300000() {
        assertThrows(
            "The maximum offset for offset-based pagination is 300000."
                + " Marker-based pagination is recommended when a higher offset is needed.",
            IllegalArgumentException.class,
            new ThrowingRunnable() {
                @Override
                public void run() {
                    PagingParameters.offset(100, 10).nextOffset(300001);
                }
            }
        );
    }

    @Test
    public void cannotSwitchToOffsetPaginationWhenMarkerIsUsed() {
        assertThrows(
            "Cannot change marker paging to offset based paging. Use PagingParameters#nextMarker(String).",
            IllegalArgumentException.class,
            new ThrowingRunnable() {
                @Override
                public void run() {
                    PagingParameters.marker(20).nextOffset(100);
                }
            }
        );
    }

    @Test
    public void cannotSwitchToMarkerPaginationWhenOffsetIsUsed() {
        assertThrows(
            "Cannot change offset paging to marker based paging. Use PagingParameters#nextOffset(long).",
            IllegalArgumentException.class,
            new ThrowingRunnable() {
                @Override
                public void run() {
                    PagingParameters.offset(20, 10).nextMarker("nextMarker");
                }
            }
        );
    }
}
