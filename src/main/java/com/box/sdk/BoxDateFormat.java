package com.box.sdk;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.TimeZone;

/** Contains methods for parsing and formatting dates for use with the Box API. */
public final class BoxDateFormat {
  private static final ThreadLocal<DateFormat> THREAD_LOCAL_DATE_FORMAT_SECONDS =
      ThreadLocal.withInitial(
          () -> {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX");
            sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
            return sdf;
          });

  private static final ThreadLocal<DateFormat> THREAD_LOCAL_DATE_FORMAT_MILLISECONDS =
      ThreadLocal.withInitial(
          () -> {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX");
            sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
            return sdf;
          });

  private static final ThreadLocal<DateFormat> THREAD_LOCAL_DATE_ONLY =
      ThreadLocal.withInitial(
          () -> {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
            return sdf;
          });

  private BoxDateFormat() {}

  /**
   * Parses a date string returned by the Box API into a {@link Date} object.
   *
   * @param dateString a string containing the date.
   * @return the parsed date.
   * @throws ParseException if the string cannot be parsed into a valid date.
   */
  public static Date parse(String dateString) throws ParseException {
    try {
      return THREAD_LOCAL_DATE_FORMAT_SECONDS.get().parse(dateString);
    } catch (ParseException pe) {
      return THREAD_LOCAL_DATE_FORMAT_MILLISECONDS.get().parse(dateString);
    }
  }

  /**
   * Parses a date in format of yyyy-MM-dd.
   *
   * @param date date to parse.
   * @return parsed date.
   * @throws ParseException if the string cannot be parsed into a valid date.
   */
  public static Date parseDateOnly(String date) throws ParseException {
    return THREAD_LOCAL_DATE_ONLY.get().parse(date);
  }

  /**
   * Formats a date as a string that can be sent to the Box API.
   *
   * @param date the date to format.
   * @return a string containing the formatted date.
   */
  public static String format(Date date) {
    return THREAD_LOCAL_DATE_FORMAT_SECONDS.get().format(date);
  }

  /**
   * Formats an Instant as a string that can be sent to the Box API.
   *
   * @param instant the instant to format.
   * @return a string containing the formatted instant.
   */
  public static String format(Instant instant) {
    return THREAD_LOCAL_DATE_FORMAT_SECONDS.get().format(Date.from(instant));
  }

  /**
   * Formats a date as a string yyyy-MM-dd that can be sent to the Box API.
   *
   * @param date the date to format.
   * @return a yyyy-MM-dd string containing the formatted date.
   */
  public static String formatAsDateOnly(Date date) {
    return THREAD_LOCAL_DATE_ONLY.get().format(date);
  }
}
