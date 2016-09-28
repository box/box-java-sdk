package com.box.sdk;

import com.eclipsesource.json.JsonObject;
/**
 * <p>BoxMetadataFilter is used to help organize the request for when making metadata filter request
 * in conjuction with search. The translation will look something like this:
 * [{"templateKey":"marketingCollateral", "scope":"enterprise", "filters":{"documentType": "datasheet"}}]</p>
 *
 *
 */
public class BoxMetadataFilter {
    private String templateKey;
    private String scope = "enterprise";
    private JsonObject filtersList;

    /**
     * Constructor for BoxMetadataFilter that initizlizes the JSON Object.
     */
    public BoxMetadataFilter() {
        this.filtersList = new JsonObject();
    }
    /**
     * Returns the template key that currently set.
     * @return this.String template key.
     */
    public String getTemplateKey() {
        return this.templateKey;
    }
    /**
     * Set the current template key for the search filter.
     * @param templateKey must be a metadata template key.
     */
    public void setTemplateKey(String templateKey) {
        this.templateKey = templateKey;
    }
    /**
     * return this.a list of the current filters that are being set.
     * @return this.JsonObject filterList.
     */
    public JsonObject getFiltersList() {
        return this.filtersList;
    }
    /**
     * Set a filter to the filterList, example: key=documentType, value=special.
     * @param key the key that the filter should be looking for.
     * @param value the specific value that corresponds to the key.
     */
    public void addFilter(String key, String value) {
        this.filtersList.add(key, value);
    }
    /**
     * Set a NumberRanger filter to the filter numbers, example: key=documentNumber, lt : 20, gt : 5.
     * @param key the key that the filter should be looking for.
     * @param sizeRange the specific value that corresponds to the key.
     */
    public void addNumberRangeFilter(String key, SizeRange sizeRange) {
        JsonObject opObj = new JsonObject();

        if (sizeRange.getLowerBoundBytes() != 0) {
            opObj.add("gt", sizeRange.getLowerBoundBytes());
        }
        if (sizeRange.getUpperBoundBytes() != 0) {
            opObj.add("lt", sizeRange.getUpperBoundBytes());
        }

        this.filtersList.add(key, opObj);
    }
    /**
     * Set a filter to the filterList, example: key=documentNumber, gt : "", lt : "".
     * @param key the key that the filter should be looking for.
     * @param dateRange the date range that is start and end dates
     */
    public void addDateRangeFilter(String key, DateRange dateRange) {

        JsonObject opObj = new JsonObject();

        if (dateRange.getFromDate() != null) {
            String dateGtString = BoxDateFormat.format(dateRange.getFromDate());
            //workaround replacing + and - 000 at the end with 'Z'
            dateGtString = dateGtString.replaceAll("(\\+|-)(?!-\\|?!\\+)\\d+$", "Z");
            opObj.add("gt", dateGtString);
        }
        if (dateRange.getToDate() != null) {
            String dateLtString = BoxDateFormat.format(dateRange.getToDate());
            //workaround replacing + and - 000 at the end with 'Z'
            dateLtString = dateLtString.replaceAll("(\\+|-)(?!-\\|?!\\+)\\d+$", "Z");
            opObj.add("lt", dateLtString);
        }

        this.filtersList.add(key, opObj);
    }
    /**
     * return this.the current scope being used.
     * @return this.String scope.
     */
    public String getScope() {
        return this.scope;
    }
    /**
     * Set the scope for the key, currently only "enterprise" and "global" are allowed.
     * @param scope the scope on which to find the template.
     */
    public void setScope(String scope) {
        this.scope = scope;
    }
}
