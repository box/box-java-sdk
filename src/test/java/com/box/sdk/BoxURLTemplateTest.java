package com.box.sdk;

import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.net.URL;

/**
 * Unit tests for {@link URLTemplate}.
 */
public class BoxURLTemplateTest {
    public static final URLTemplate ADD_COMMENT_URL_TEMPLATE = new URLTemplate("test/%s");
    public static final String BASE_URL = TestConfig.getAPIConnection().getBaseURL();

    /**
    * Unit test for {@link URLTemplate#build(String...)}
    */
    @Test
    @Category(UnitTest.class)
    public void testBuildSucceeds() {
        String param = "12345";
        URLTemplate template = new URLTemplate("test/%s");
        URL url = template.build(BASE_URL, param);
        String expectedURL = BASE_URL + "test/" + param;
        Assert.assertEquals(url.toString(), expectedURL);
    }

    /**
    * Unit test for {@link URLTemplate#build(String...)}
    */
    @Test
    @Category(UnitTest.class)
    public void testBuildFails() {
        URLTemplate template = new URLTemplate("test/%s");
        try {
            URL url = template.build(BASE_URL, "123dfest");
        } catch (BoxAPIException e) {
            Assert.assertEquals("An invalid path parameter passed in. It must be numeric.", e.getMessage());
            return;
        }
        Assert.fail("Never threw a BoxAPIException");
    }

    /**
    * Unit test for {@link URLTemplate#buildAlpha(String...)}
    */
    @Test
    @Category(UnitTest.class)
    public void testBuildAlphaSucceeds() {
        String param = "$123-45_67";
        URLTemplate template = new URLTemplate("test/%s");
        URL url = template.buildAlpha(BASE_URL, param);
        String expectedURL = BASE_URL + "test/" + param;
        Assert.assertEquals(url.toString(), expectedURL);
    }

    /**
    * Unit test for {@link URLTemplate#buildAlpha(String...)}
    */
    @Test
    @Category(UnitTest.class)
    public void testBuildAlphaFails() {
        URLTemplate template = new URLTemplate("test/%s");
        try {
            URL url = template.buildAlpha(BASE_URL, "1234.45/43/5");
        } catch (BoxAPIException e) {
            Assert.assertEquals("An invalid path parameter passed in. It must be alphanumeric.", e.getMessage());
            return;
        }
        Assert.fail("Never threw a BoxAPIException");
    }
}
