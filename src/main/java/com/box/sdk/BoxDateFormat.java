package com.box.sdk;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Contains methods for parsing and formatting dates for use with the Box API.
 */
public final class BoxDateFormat {
    private static final ThreadLocal<DateFormat> THREAD_LOCAL_DATE_FORMAT = new ThreadLocal<DateFormat>() {
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX");
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
        return THREAD_LOCAL_DATE_FORMAT.get().parse(dateString);
    }

    /**
     * Formats a date as a string that can be sent to the Box API.
     * @param  date the date to format.
     * @return      a string containing the formatted date.
     */
    public static String format(Date date) {
        return THREAD_LOCAL_DATE_FORMAT.get().format(date);
    }
}
