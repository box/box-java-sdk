package com.box.sdk;
import java.net.URL;

import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;
/**
 * Represents search on Box. This class can be used to search through your box instance.
 * In addition this lets you take advantage of all the advanced search features.
 *
 * <p>Unless otherwise noted, the methods in this class can throw an unchecked {@link BoxAPIException} (unchecked
 * meaning that the compiler won't force you to handle it) if an error occurs. If you wish to implement custom error
 * handling for errors related to the Box REST API, you should capture this exception explicitly.</p>
 */
public class BoxSearch {

    private static final URLTemplate SEARCH_URL_TEMPLATE = new URLTemplate("search");
    private final BoxAPIConnection api;

    /**
     * Constructs a Search to be used by everything.
     * @param  api the API connection to be used by the search.
     */
    public BoxSearch(BoxAPIConnection api) {
        this.api = api;
    }

    /**
     * Searches all descendant folders using a given query and query parameters.
     * @param  offset is the starting position.
     * @param  limit the number of search results CANNONT Exceed 1000.
     * @param  bsp containing query and advanced search capabilities.
     * @return a PartialCollection containing the search results.
     */
    public PartialCollection<BoxItem.Info> searchRange(long offset, long limit, final BoxSearchParameters bsp) {
        QueryStringBuilder builder = bsp.getQueryParameters()
                .appendParam("limit", limit)
                .appendParam("offset", offset);
        URL url = SEARCH_URL_TEMPLATE.buildWithQuery(this.getAPI().getBaseURL(), builder.toString());
        BoxAPIRequest request = new BoxAPIRequest(this.getAPI(), url, "GET");
        BoxJSONResponse response = (BoxJSONResponse) request.send();
        JsonObject responseJSON = JsonObject.readFrom(response.getJSON());
        String totalCountString = responseJSON.get("total_count").toString();
        long fullSize = Double.valueOf(totalCountString).longValue();
        PartialCollection<BoxItem.Info> results = new PartialCollection<BoxItem.Info>(offset, limit, fullSize);
        JsonArray jsonArray = responseJSON.get("entries").asArray();
        for (JsonValue value : jsonArray) {
            JsonObject jsonObject = value.asObject();
            BoxItem.Info parsedItemInfo = (BoxItem.Info) BoxResource.parseInfo(this.getAPI(), jsonObject);
            if (parsedItemInfo != null) {
                results.add(parsedItemInfo);
            }
        }
        return results;
    }
    /**
     * Gets the API connection used by this resource.
     * @return the API connection used by this resource.
     */
    public BoxAPIConnection getAPI() {
        return this.api;
    }
}
