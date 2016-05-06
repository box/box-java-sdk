package com.box.sdk;
import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
/**
 * <p>BoxMetadataFilterRequest class was designed to wrap the BoxMetadataFilter close.
 * Best practice is to use the two in conjuction.</p>
 *
 */
public class BoxMetadataFilterRequest {
    private JsonArray boxMetadataFilterRequestArray = new JsonArray();
    /**
     * Add BoxMetaDataFilter to the JsonArray boxMetadataFilterRequestArray.
     * @param bmf accepts a filter that has templateKey, scope, and filters populated.
     */
    public void addBoxMetadataFilter(BoxMetadataFilter bmf) {
        JsonObject boxMetadataFilter = new JsonObject()
                .add("templateKey", bmf.getTemplateKey())
                .add("scope", bmf.getScope())
                .add("filters", bmf.getFiltersList());
        this.boxMetadataFilterRequestArray.add(boxMetadataFilter);
    }
    /**
     * Return the Array of BoxMetaDataFilters.
     * @return JsonArray boxMetadataFilterRequestArray.
     */
    public JsonArray getBoxMetadataFilterRequestArray() {
        return this.boxMetadataFilterRequestArray;
    }
}
