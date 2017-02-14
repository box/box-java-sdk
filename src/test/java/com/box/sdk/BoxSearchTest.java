package com.box.sdk;

import static java.net.URLEncoder.encode;
import java.io.UnsupportedEncodingException;

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
    public final WireMockRule wireMockRule = new WireMockRule(8080);

    @Test
    @Category(UnitTest.class)
    public void searchWithQueryRequestsCorrectFields() {
        String query = "A query";
        BoxAPIConnection api = new BoxAPIConnection("");
        api.setBaseURL("http://localhost:8080/");

        try {
            stubFor(get(urlPathEqualTo("/search"))
                    .withQueryParam("query", WireMock.equalTo(encode(query, "UTF-8")))
                    .withQueryParam("limit", WireMock.equalTo("10"))
                    .withQueryParam("offset", WireMock.equalTo("10"))
                    .willReturn(aResponse()
                            .withHeader("Content-Type", "application/json")
                            .withBody("{\"total_count\": 1, \"offset\": 10, \"limit\": 10, \"entries\":"
                                    + "[{\"type\": \"file\", \"id\": \"0\"}]}")));
        } catch (UnsupportedEncodingException e) { /* no op */ }

        BoxSearch boxSearch = new BoxSearch(api);
        BoxSearchParameters searchParams = new BoxSearchParameters();

        searchParams.setQuery(query);

        PartialCollection<BoxItem.Info> searchResults = boxSearch.searchRange(10, 10, searchParams);

        assertThat(searchResults.size(), is(1));
    }

    @Test
    @Category(UnitTest.class)
    public void searchWithMetadataRequestsCorrectFiltersAndFields() {
        final String filters = "%5B%7B%22templateKey%22%3A%22test%22%2C%22scope%22%3A%22enterprise%22%2C%22"
                + "filters%22%3A%7B%22number%22%3A%7B%22gt%22%3A12%2C%22lt%22%3A19%7D%2C%22test%22%3A%22"
                + "example%22%7D%7D%5D";

        BoxAPIConnection api = new BoxAPIConnection("");
        api.setBaseURL("http://localhost:8080/");

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
