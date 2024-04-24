package com.box.sdk;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import org.junit.Test;

public class BoxDateFormatTest {

    @Test
    public void testParseWorksWithOffsetTimezone() throws ParseException {

        Date date = BoxDateFormat.parse("2019-04-06T15:57:01-07:00");
        Date expectedDate = new Date(1554591421000L);
        assertEquals(expectedDate, date);
    }

    @Test
    public void testParseWorksWithZuluTimezone() throws ParseException {

        Date date = BoxDateFormat.parse("2019-04-06T22:58:49Z");
        Date expectedDate = new Date(1554591529000L);
        assertEquals(expectedDate, date);
    }

    @Test
    public void testParseWorksWithZeroOffsetTimezone() throws ParseException {

        Date date = BoxDateFormat.parse("2019-04-06T22:58:49+00:00");
        Date expectedDate = new Date(1554591529000L);
        assertEquals(expectedDate, date);
    }

    @Test
    public void testParseWorksWithMinusZeroOffsetTimezone() throws ParseException {

        Date date = BoxDateFormat.parse("2019-04-06T22:58:49-00:00");
        Date expectedDate = new Date(1554591529000L);
        assertEquals(expectedDate, date);
    }

    @Test
    public void testParseWorksWithRFC822ZeroOffsetTimezone() throws ParseException {

        Date date = BoxDateFormat.parse("2019-04-06T22:58:49+0000");
        Date expectedDate = new Date(1554591529000L);
        assertEquals(expectedDate, date);
    }

    @Test
    public void testFormatOutputsZuluTimezone() {
        Date date = new Date(1554591421000L);
        String expectedString = "2019-04-06T22:57:01Z";
        assertEquals(expectedString, BoxDateFormat.format(date));
    }

    @Test
    public void testParseWorksWithMilisecondsResolution() throws ParseException {

        Date date = BoxDateFormat.parse("2019-04-06T22:58:49.123+01:00");
        Date expectedDate = new Date(1554587929123L);
        assertEquals(expectedDate, date);
    }

    @Test
    public void testFormatDateToDateOnlyString() {
        Date date = Date.from(LocalDateTime.of(2020, 5, 14, 10, 15, 12)
            .toInstant(ZoneOffset.UTC));

        String formattedDate = BoxDateFormat.formatAsDateOnly(date);

        assertEquals(formattedDate, "2020-05-14");
    }
}
