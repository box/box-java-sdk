package com.box.sdk;

import static java.util.Collections.singletonList;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.junit.Assert;
import org.junit.Test;

public class BoxAPIResponseTest {

    @Test
    public void testAPIResponseHeaderIsCaseInsensitive() {
        Map<String, List<String>> headers = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        headers.put("FOO", singletonList("bAr"));
        BoxAPIResponse responseObject = new BoxAPIResponse(202, "GET", "https://aaa.com", headers);

        Assert.assertTrue(responseObject.getHeaders().containsKey("foo"));
        Assert.assertTrue(responseObject.getHeaders().containsKey("fOo"));
        Assert.assertTrue(responseObject.getHeaders().containsKey("FOO"));
        Assert.assertEquals(singletonList("bAr"), responseObject.getHeaders().get("foo"));
    }
}
