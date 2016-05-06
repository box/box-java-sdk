package com.box.sdk;
import java.util.Date;

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
    public void setFilter(String key, String value) {
        this.filtersList = new JsonObject();
        this.filtersList.add(key.toLowerCase(), value);
    }
    /**
     * Set a filter to the filterList, example: key=documentType, value=special.
     * @param key the key that the filter should be looking for.
     * @param value the specific value that corresponds to the key.
     */
    public void setGreaterThanFilter(String key, String value) {
        this.filtersList = new JsonObject();
        JsonObject opObj = new JsonObject().add("gt", value);
        this.filtersList.add(key.toLowerCase(), opObj);
    }
    /**
     * Set a filter to the filterList, example: key=documentType, value=special.
     * @param key the key that the filter should be looking for.
     * @param value the specific value that corresponds to the key.
     */
    public void setLessThanFilter(String key, String value) {
        this.filtersList = new JsonObject();
        JsonObject opObj = new JsonObject().add("lt", value);
        this.filtersList.add(key.toLowerCase(), opObj);
    }
    /**
     * Set a filter to the filterList, example: key=documentNumber, gt : 5.
     * @param key the key that the filter should be looking for.
     * @param value the specific value that corresponds to the key.
     */
    public void setGreaterThanNumberFilter(String key, int value) {
        this.filtersList = new JsonObject();
        JsonObject opObj = new JsonObject().add("gt", value);
        this.filtersList.add(key.toLowerCase(), opObj);
    }
    /**
     * Set a filter to the filterList, example: key=documentNumber, lt : 5.
     * @param key the key that the filter should be looking for.
     * @param value the specific value that corresponds to the key.
     */
    public void setLessThanNumberFilter(String key, int value) {
        this.filtersList = new JsonObject();
        JsonObject opObj = new JsonObject().add("lt", value);
        this.filtersList.add(key.toLowerCase(), opObj);
    }
    /**
     * Set a filter to the filterList, example: key=documentNumber, gt : 5.
     * @param key the key that the filter should be looking for.
     * @param value the specific value that corresponds to the key.
     */
    public void setGreaterThanDateFilter(String key, Date value) {
        this.filtersList = new JsonObject();
        String dateString = BoxDateFormat.format(value).replaceAll("(\\+|-)(?!-\\|?!\\+)\\d+$", "Z");

        JsonObject opObj = new JsonObject().add("gt", dateString);
        this.filtersList.add(key.toLowerCase(), opObj);
    }
    /**
     * Set a filter to the filterList, example: key=documentNumber, lt : 5.
     * @param key the key that the filter should be looking for.
     * @param value the specific value that corresponds to the key.
     */
    public void setLessThanDateFilter(String key, Date value) {
        this.filtersList = new JsonObject();
        String dateString = BoxDateFormat.format(value).replaceAll("(\\+|-)(?!-\\|?!\\+)\\d+$", "Z");

        JsonObject opObj = new JsonObject().add("lt", dateString);
        this.filtersList.add(key.toLowerCase(), opObj);
    }
    /**
     * Set a filter to the filterList, example: key=documentNumber, gt : "", lt : "".
     * @param key the key that the filter should be looking for.
     * @param gt the date range that is start range
     * @param lt the date range that is end range
     */
    public void setDateRangeFilter(String key, Date gt, Date lt) {
        this.filtersList = new JsonObject();

        String dateGtString = BoxDateFormat.format(gt).replaceAll("(\\+|-)(?!-\\|?!\\+)\\d+$", "Z");
        String dateLtString = BoxDateFormat.format(lt).replaceAll("(\\+|-)(?!-\\|?!\\+)\\d+$", "Z");

        JsonObject opObj = new JsonObject().add("gt", dateGtString).add("lt", dateLtString);
        this.filtersList.add(key.toLowerCase(), opObj);
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
