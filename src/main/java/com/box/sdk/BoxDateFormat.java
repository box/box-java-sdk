package com.box.sdk;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Contains methods for parsing and formatting dates for use with the Box API.
 */
public final class BoxDateFormat {
    private static final ThreadLocal<DateFormat> THREAD_LOCAL_DATE_FORMAT = new ThreadLocal<DateFormat>() {
        @Override
        protected DateFormat initialValue() {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
            sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
            return sdf;
        }
    };

    private BoxDateFormat() { }

    /**
     * Parses a date string returned by the Box API into a {@link Date} object.
     * @param  dateString     a string containing the date.
     * @return                the parsed date.
     * @throws ParseException if the string cannot be parsed into a valid date.
     */
    public static Date parse(String dateString) throws ParseException {
        return THREAD_LOCAL_DATE_FORMAT.get().parse(fixIso8601TimeZone(dateString));
    }

    /**
     * Formats a date as a string that can be sent to the Box API.
     * @param  date the date to format.
     * @return      a string containing the formatted date.
     */
    public static String format(Date date) {
        return THREAD_LOCAL_DATE_FORMAT.get().format(date);
    }

    /**
     * Helper function to handle ISO 8601 strings of the following format:
     * "2008-03-01T13:00:00+01:00".  Note that the final colon (":") in the
     * time zone is not supported by SimpleDateFormat's "Z" token.
     *
     * @param dateString a string containing the date.
     * @return a date string that matches the date format.
     */
    private static String fixIso8601TimeZone(String dateString) {
        if (dateString.length() >= 24 && dateString.charAt(22) == ':') {
            return dateString.substring(0, 22) + dateString.substring(23);
        }
        return dateString;
    }
}
