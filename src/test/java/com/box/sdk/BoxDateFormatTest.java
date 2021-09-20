package com.box.sdk;

import java.text.ParseException;
import java.util.Date;
import org.junit.Assert;
import org.junit.Test;

public class BoxDateFormatTest {

    @Test
    public void testParseWorksWithOffsetTimezone() throws ParseException {

        Date date = BoxDateFormat.parse("2019-04-06T15:57:01-07:00");
        Date expectedDate = new Date(1554591421000L);
        Assert.assertEquals(expectedDate, date);
    }

    @Test
    public void testParseWorksWithZuluTimezone() throws ParseException {

        Date date = BoxDateFormat.parse("2019-04-06T22:58:49Z");
        Date expectedDate = new Date(1554591529000L);
        Assert.assertEquals(expectedDate, date);
    }

    @Test
    public void testParseWorksWithZeroOffsetTimezone() throws ParseException {

        Date date = BoxDateFormat.parse("2019-04-06T22:58:49+00:00");
        Date expectedDate = new Date(1554591529000L);
        Assert.assertEquals(expectedDate, date);
    }

    @Test
    public void testParseWorksWithMinusZeroOffsetTimezone() throws ParseException {

        Date date = BoxDateFormat.parse("2019-04-06T22:58:49-00:00");
        Date expectedDate = new Date(1554591529000L);
        Assert.assertEquals(expectedDate, date);
    }

    @Test
    public void testParseWorksWithRFC822ZeroOffsetTimezone() throws ParseException {

        Date date = BoxDateFormat.parse("2019-04-06T22:58:49+0000");
        Date expectedDate = new Date(1554591529000L);
        Assert.assertEquals(expectedDate, date);
    }

    @Test
    public void testFormatOutputsZuluTimezone() {
        Date date = new Date(1554591421000L);
        String expectedString = "2019-04-06T22:57:01Z";
        Assert.assertEquals(expectedString, BoxDateFormat.format(date));
    }
}
