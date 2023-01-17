package com.box.sdk;

import static com.box.sdk.StandardCharsets.UTF_8;
import static com.box.sdk.http.ContentType.APPLICATION_JSON;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.junit.Test;

public class BoxAPIResponseTest {

    @Test
    public void testAPIResponseHeaderIsCaseInsensitive() {
        Map<String, List<String>> headers = new TreeMap<>();
        headers.put("FOO", singletonList("bAr"));
        BoxAPIResponse response = new BoxAPIResponse(202, "GET", "https://aaa.com", headers);

        assertThat(response.getHeaders().get("foo"), contains("bAr"));
        assertThat(response.getHeaderField("foo"), is("bAr"));
        assertThat(response.getHeaders().get("fOo"), contains("bAr"));
        assertThat(response.getHeaderField("fOo"), is("bAr"));
        assertThat(response.getHeaders().get("FOO"), contains("bAr"));
        assertThat(response.getHeaderField("FOO"), is("bAr"));
    }

    @Test
    public void testWhenHeadersAreNull() {
        new BoxAPIResponse(202, "GET", "https://aaa.com", null);
    }

    @Test
    public void testReturnsFirstHeaderFromList() {
        Map<String, List<String>> headers = new TreeMap<>();
        headers.put("FOO", asList("bAr", "zab"));
        BoxAPIResponse response = new BoxAPIResponse(202, "GET", "https://aaa.com", headers);

        assertThat(response.getHeaders().get("foo"), contains("bAr", "zab"));
        assertThat(response.getHeaderField("foo"), is("bAr"));
    }

    @Test
    public void testResponseWithoutBodyToString() {
        Map<String, List<String>> headers = new TreeMap<>();
        headers.put("FOO", asList("bAr", "zab"));
        BoxAPIResponse response = new BoxAPIResponse(200, "GET", "https://aaa.com", headers);

        String str = response.toString();
        assertThat(str, is("Response\nGET https://aaa.com\nHeaders:\nfoo: [[bAr, zab]]"));
    }

    @Test
    public void testBinaryResponseToString() {
        Map<String, List<String>> headers = new TreeMap<>();
        headers.put("FOO", asList("bAr", "zab"));
        ByteArrayInputStream responseBody = new ByteArrayInputStream("This is image".getBytes(UTF_8));
        BoxAPIResponse response = new BoxAPIResponse(
            202, "GET", "https://aaa.com", headers, responseBody, "image/jpg", responseBody.available()
        );

        String str = response.toString();
        assertThat(str, is("Response\nGET https://aaa.com\nContent-Type: image/jpg\nHeaders:\nfoo: [[bAr, zab]]"));
    }

    @Test
    public void testJsonResponseToString() {
        Map<String, List<String>> headers = new TreeMap<>();
        headers.put("FOO", asList("bAr", "zab"));
        ByteArrayInputStream responseBody = new ByteArrayInputStream("{\"foo\":\"bar\"}".getBytes(UTF_8));
        BoxAPIResponse response = new BoxAPIResponse(
            202, "GET", "https://aaa.com", headers, responseBody, APPLICATION_JSON, responseBody.available()
        );

        String str = response.toString();
        assertThat(str, is(
            "Response\nGET https://aaa.com\n"
                + "Content-Type: application/json\n"
                + "Headers:\nfoo: [[bAr, zab]]\n"
                + "Body:\n{\"foo\":\"bar\"}")
        );
    }
}
