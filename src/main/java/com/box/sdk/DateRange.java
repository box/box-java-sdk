package com.box.sdk;

import java.util.Date;

/**
 * Class is used to be a range for two dates. Ususally paired with varying search filters.
 *
 */
public class DateRange {
    private Date from;
    private Date to;

    /**
     * Used for specify a date range to filter to be used in search.
     * @param from is the start date in a range.
     * @param to is the end date in a range.
     */
    public DateRange(Date from, Date to) {
        this.from = from;
        this.to = to;
    }
    /**
     * Returns the from date which is the start date.
     * @return Date this is start date.
     */
    public Date getFromDate() {
        return this.from;
    }
    /**
     * Set the from date which is equivalent to the start date.
     * @param from date which is the starting point.
     */
    public void setFrom(Date from) {
        this.from = from;
    }
    /**
     * Returns the to date which is the end date.
     * @return Date this is the end date.
     */
    public Date getToDate() {
        return this.to;
    }
    /**
     * Set the to date which is equivalent to the start date.
     * @param to date which is the end point.
     */
    public void setTo(Date to) {
        this.to = to;
    }
    /**
     * Used to build out a string a http box api friendly range string.
     * @return String that is uses as a rest parameter.
     */
    public String buildRangeString() {

        String fromString = BoxDateFormat.format(this.from);
        String toString = BoxDateFormat.format(this.to);


        String rangeString = String.format("%s,%s", fromString, toString);
        if (rangeString == ",") {
            rangeString = null;
        }
        return rangeString;
    }
}
