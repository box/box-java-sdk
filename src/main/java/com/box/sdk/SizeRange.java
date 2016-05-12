package com.box.sdk;

/**
 * Class is used to be a range for two byte numbers. Ususally paired with varying search filters.
 *
 */
public class SizeRange {
    private int lowerBoundBytes;
    private int upperBoundBytes;

    /**
     * Used for specify a file size range to filter to be used in search.
     * @param lowerBoundBytes is the lower size in the byte range.
     * @param upperBoundBytes is the upper limit in the byte range.
     */
    public SizeRange(int lowerBoundBytes, int upperBoundBytes) {
        this.lowerBoundBytes = lowerBoundBytes;
        this.upperBoundBytes = upperBoundBytes;
    }
    /**
     * Return the lower size in the byte range.
     * @return int that represents the lower size of a file.
     */
    public int getLowerBoundBytes() {
        return this.lowerBoundBytes;
    }
    /**
     * Set the lower bound bytes in the byte file size range.
     * @param lowerBoundBytes used for the lower file size range.
     */
    public void setLowerBoundBytes(int lowerBoundBytes) {
        this.lowerBoundBytes = lowerBoundBytes;
    }
    /**
     * Get the upper bound bytes in the file size range.
     * @return int that represents the upper limit of the file size.
     */
    public int getUpperBoundBytes() {
        return this.upperBoundBytes;
    }
    /**
     * Set the upper bound bytes in the file size range.
     * @param upperBoundBytes used for the upper file size range.
     */
    public void setUpperBoundBytes(int upperBoundBytes) {
        this.upperBoundBytes = upperBoundBytes;
    }
    /**
     * Used to build out a string a http box api friendly range string.
     * @return String that is uses as a rest parameter.
     */
    public String buildRangeString() {
        String lowerBoundString = "";
        if (this.lowerBoundBytes > -1) {
            lowerBoundString = String.valueOf(this.lowerBoundBytes);
        }

        String upperBoundString = "";
        if (this.upperBoundBytes > -1) {
            upperBoundString = String.valueOf(this.upperBoundBytes);
        }
        String rangeString = String.format("%s,%s", lowerBoundString, upperBoundString);
        if (rangeString == ",") {
            rangeString = null;
        }
        return rangeString;
    }
}
