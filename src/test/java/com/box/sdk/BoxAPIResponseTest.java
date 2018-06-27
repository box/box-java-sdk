package com.box.sdk;

import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.util.Map;
import java.util.TreeMap;

/**
 *
 */
public class BoxAPIResponseTest {

    @Test
    @Category(UnitTest.class)
    public void testAPIResponseHeaderIsCaseInsensitive() {
        Map<String, String> headers = new TreeMap<String, String>(String.CASE_INSENSITIVE_ORDER);
        headers.put("FOO", "bAr");
        BoxAPIResponse responseObject = new BoxAPIResponse(202, headers);

        Assert.assertTrue(responseObject.getHeaders().containsKey("foo"));
        Assert.assertTrue(responseObject.getHeaders().containsKey("fOo"));
        Assert.assertTrue(responseObject.getHeaders().containsKey("FOO"));
        Assert.assertEquals("bAr", responseObject.getHeaders().get("foo"));
    }

}
