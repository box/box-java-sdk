package com.box.sdk;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static java.lang.String.format;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import java.io.IOException;
import java.util.Iterator;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class BoxSearchTest {
    @Rule
    public WireMockRule wireMockRule = new WireMockRule(wireMockConfig().dynamicPort());
    private final BoxAPIConnection api = new BoxAPIConnection("");

    @Before
    public void setUpBaseUrl() {
        api.setMaxRetryAttempts(1);
        api.setBaseURL(format("http://localhost:%d", wireMockRule.port()));
    }

    @Test
    public void searchWithQueryRequestsCorrectFields() {
        String query = "A query";

        stubFor(get(urlPathEqualTo("/2.0/search"))
            .withQueryParam("query", WireMock.equalTo(query))
            .withQueryParam("limit", WireMock.equalTo("10"))
            .withQueryParam("offset", WireMock.equalTo("10"))
            .willReturn(aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody("{\"total_count\": 1, \"offset\": 10, \"limit\": 10, \"entries\":"
                    + "[{\"type\": \"file\", \"id\": \"0\"}]}")));

        BoxSearch boxSearch = new BoxSearch(api);
        BoxSearchParameters searchParams = new BoxSearchParameters();

        searchParams.setQuery(query);

        PartialCollection<BoxItem.Info> searchResults = boxSearch.searchRange(10, 10, searchParams);

        assertThat(searchResults.size(), is(1));
    }

    @Test
    public void searchWithMetadataRequestsCorrectFiltersAndFields() {

        final String filters = "[{\"templateKey\":\"test\",\"scope\":\"enterprise\","
            + "\"filters\":{\"number\":{\"gt\":12,\"lt\":19},\"test\":\"example\"}}]";

        stubFor(get(urlPathEqualTo("/2.0/search"))
            .withQueryParam("type", WireMock.equalTo("file"))
            .withQueryParam("mdfilters", WireMock.equalTo(filters))
            .withQueryParam("limit", WireMock.equalTo("10"))
            .withQueryParam("offset", WireMock.equalTo("10"))
            .willReturn(aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody("{\"total_count\": 1, \"offset\": 10, \"limit\": 10, \"entries\":"
                    + "[{\"type\": \"file\", \"id\": \"0\"}]}")));

        BoxSearch boxSearch = new BoxSearch(api);
        BoxSearchParameters searchParams = new BoxSearchParameters();

        searchParams.setType("file");
        BoxMetadataFilter metadataFilter = new BoxMetadataFilter();
        metadataFilter.setScope("enterprise");
        metadataFilter.setTemplateKey("test");
        SizeRange sizeRange = new SizeRange(12, 19);
        metadataFilter.addNumberRangeFilter("number", sizeRange);
        metadataFilter.addFilter("test", "example");
        searchParams.setMetadataFilter(metadataFilter);

        PartialCollection<BoxItem.Info> searchResults = boxSearch.searchRange(10, 10, searchParams);

        assertThat(searchResults.size(), is(1));
    }

    @Test
    public void searchIncludeSharedLinksRequestsCorrectFields() throws IOException {
        String query = "A query";

        String result = TestUtils.getFixture("BoxSearch/GetSearchItemsIncludingSharedLinks200");

        stubFor(get(urlPathEqualTo("/2.0/search"))
            .withQueryParam("query", WireMock.equalTo(query))
            .withQueryParam("include_recent_shared_links", WireMock.equalTo("true"))
            .withQueryParam("limit", WireMock.equalTo("10"))
            .withQueryParam("offset", WireMock.equalTo("10"))
            .willReturn(aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody(result)));

        BoxSearch boxSearch = new BoxSearch(api);
        BoxSearchParameters searchParams = new BoxSearchParameters();

        searchParams.setQuery(query);

        PartialCollection<BoxSearchSharedLink> searchResults = boxSearch.searchRangeIncludeSharedLinks(10,
            10, searchParams);
        Iterator<BoxSearchSharedLink> searchResultsIterator = searchResults.iterator();
        BoxSearchSharedLink searchItem = searchResultsIterator.next();

        assertThat(searchResults.size(), is(1));
        assertThat(searchItem.getType(), is("search_result"));
        assertThat(searchItem.getItem().getID(), is("12345"));
        assertThat(searchItem.getItem().getSharedLink().getURL(),
            is("https://www.box.com/s/vspke7y05sb214wjokpk"));
        assertThat(searchItem.getAccessibleViaSharedLink().toString(),
            is("https://www.box.com/s/vspke7y05sb214wjokpk"));
    }
}
