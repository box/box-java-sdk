package com.box.sdk;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

import java.net.URL;
import org.junit.Assert;
import org.junit.Test;

/**
 * Unit tests for {@link URLTemplate}.
 */
public class BoxURLTemplateTest {
    public static final String BASE_URL = new BoxAPIConnection("").getBaseURL();

    /**
     * Unit test for {@link URLTemplate#build(String, Object...)}
     */
    @Test
    public void testBuildSucceeds() {
        String param = "12345";
        URLTemplate template = new URLTemplate("test/%s");
        URL url = template.build(BASE_URL, param);
        String expectedURL = BASE_URL + "test/" + param;
        assertThat(url.toString(), is(expectedURL));
    }

    /**
     * Unit test for {@link URLTemplate#build(String, Object...)}
     */
    @Test
    public void testBuildFails() {
        URLTemplate template = new URLTemplate("test/%s");
        try {
            URL url = template.build(BASE_URL, "123dfest");
        } catch (BoxAPIException e) {
            assertEquals("An invalid path parameter passed in. It must be numeric.", e.getMessage());
            return;
        }
        Assert.fail("Never threw a BoxAPIException");
    }

    /**
     * Unit test for {@link URLTemplate#buildAlpha(String, Object...)}
     */
    @Test
    public void testBuildAlphaSucceeds() {
        String param = "$123-45_67";
        URLTemplate template = new URLTemplate("test/%s");
        URL url = template.buildAlpha(BASE_URL, param);
        String expectedURL = BASE_URL + "test/" + param;
        assertThat(url.toString(), is(expectedURL));
    }

    /**
     * Unit test for {@link URLTemplate#buildAlpha(String, Object...)}
     */
    @Test
    public void testBuildAlphaFails() {
        URLTemplate template = new URLTemplate("test/%s");
        try {
            URL url = template.buildAlpha(BASE_URL, "1234.45/43/5");
        } catch (BoxAPIException e) {
            assertEquals("An invalid path parameter passed in. It must be alphanumeric.", e.getMessage());
            return;
        }
        Assert.fail("Never threw a BoxAPIException");
    }
}
