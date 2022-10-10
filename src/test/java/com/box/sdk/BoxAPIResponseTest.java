package com.box.sdk;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.junit.Test;

public class BoxAPIResponseTest {

    @Test
    public void testAPIResponseHeaderIsCaseInsensitive() {
        Map<String, List<String>> headers = new TreeMap<>();
        headers.put("FOO", singletonList("bAr"));
        BoxAPIResponse responseObject = new BoxAPIResponse(202, "GET", "https://aaa.com", headers);

        assertThat(responseObject.getHeaders().get("foo"), contains("bAr"));
        assertThat(responseObject.getHeaderField("foo"), is("bAr"));
        assertThat(responseObject.getHeaders().get("fOo"), contains("bAr"));
        assertThat(responseObject.getHeaderField("fOo"), is("bAr"));
        assertThat(responseObject.getHeaders().get("FOO"), contains("bAr"));
        assertThat(responseObject.getHeaderField("FOO"), is("bAr"));
    }

    @Test
    public void testWhenHeadersAreNull() {
        new BoxAPIResponse(202, "GET", "https://aaa.com", null);
    }

    @Test
    public void testReturnsFirstHeaderFromList() {
        Map<String, List<String>> headers = new TreeMap<>();
        headers.put("FOO", asList("bAr", "zab"));
        BoxAPIResponse responseObject = new BoxAPIResponse(202, "GET", "https://aaa.com", headers);

        assertThat(responseObject.getHeaders().get("foo"), contains("bAr", "zab"));
        assertThat(responseObject.getHeaderField("foo"), is("bAr"));
    }
}
