package com.box.sdk;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockRule;

public class BoxSearchTest {
    @Rule
    public final WireMockRule wireMockRule = new WireMockRule(53620);

    @Test
    @Category(UnitTest.class)
    public void searchWithQueryRequestsCorrectFields() {
        String query = "A query";
        BoxAPIConnection api = new BoxAPIConnection("");
        api.setBaseURL("http://localhost:53620/");

        stubFor(get(urlPathEqualTo("/search"))
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
    @Category(UnitTest.class)
    public void searchWithMetadataRequestsCorrectFiltersAndFields() {

        final String filters = "[{\"templateKey\":\"test\",\"scope\":\"enterprise\","
                + "\"filters\":{\"number\":{\"gt\":12,\"lt\":19},\"test\":\"example\"}}]";


        BoxAPIConnection api = new BoxAPIConnection("");
        api.setBaseURL("http://localhost:53620/");

        stubFor(get(urlPathEqualTo("/search"))
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
}
