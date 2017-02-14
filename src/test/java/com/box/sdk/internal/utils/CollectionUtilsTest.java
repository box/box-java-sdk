package com.box.sdk.internal.utils;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;



/**
 * Unit tests for {@link CollectionUtils}.
 */
public class CollectionUtilsTest {

    /**
     * Unit tests for {@link CollectionUtils#map(java.util.Collection, com.box.sdk.utils.CollectionUtils.Mapper)}.
     */
    @Test
    public void testMap() {
        Integer[] expected = new Integer[] {1, 2};
        Integer[] actual = CollectionUtils.map(Arrays.asList(expected), new CollectionUtils.Mapper<Integer, Integer>() {

            @Override
            public Integer map(Integer input) {
                return input + 1;
            }

        }).toArray(new Integer[2]);

        Assert.assertEquals(2, actual.length);
        Assert.assertEquals((Integer) (expected[0] + 1), actual[0]);
        Assert.assertEquals((Integer) (expected[1] + 1), actual[1]);
    }

}
