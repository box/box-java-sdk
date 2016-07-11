package com.box.sdk.internal.utils;

import java.util.Arrays;
import java.util.Collections;

import org.junit.Assert;
import org.junit.Test;

/**
 * {@link StringUtils} related tests.
 *
 * @author Stanislav Dvorscak
 *
 */
public class StringUtilsTest {

    /**
     * Unit tests for {@link StringUtils#join(String, java.util.Collection)}.
     */
    @Test
    public void testJoin() {
        String delimiter = ",";

        Assert.assertEquals(null, StringUtils.join(delimiter, null));
        Assert.assertEquals(null, StringUtils.join(delimiter, Collections.<String>emptyList()));

        Assert.assertEquals("test", StringUtils.join(delimiter, Collections.singletonList("test")));
        Assert.assertEquals("test_1,test_2",
             StringUtils.join(delimiter, Arrays.asList(new String[] {"test_1", "test_2"})));
    }

}
