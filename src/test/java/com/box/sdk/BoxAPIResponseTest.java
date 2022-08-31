package com.box.sdk;

import java.util.Map;
import java.util.TreeMap;
import org.junit.Assert;
import org.junit.Test;

public class BoxAPIResponseTest {

    @Test
    public void testAPIResponseHeaderIsCaseInsensitive() {
        Map<String, String> headers = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        headers.put("FOO", "bAr");
        BoxAPIResponse responseObject = new BoxAPIResponse(202, "GET", "https://aaa.com", headers);

        Assert.assertTrue(responseObject.getHeaders().containsKey("foo"));
        Assert.assertTrue(responseObject.getHeaders().containsKey("fOo"));
        Assert.assertTrue(responseObject.getHeaders().containsKey("FOO"));
        Assert.assertEquals("bAr", responseObject.getHeaders().get("foo"));
    }
}
