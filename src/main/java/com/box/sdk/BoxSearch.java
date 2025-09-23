package com.box.sdk;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;
import java.net.URL;

/**
 * Represents search on Box. This class can be used to search through your box instance. In addition
 * this lets you take advantage of all the advanced search features.
 *
 * <p>Unless otherwise noted, the methods in this class can throw an unchecked {@link
 * BoxAPIException} (unchecked meaning that the compiler won't force you to handle it) if an error
 * occurs. If you wish to implement custom error handling for errors related to the Box REST API,
 * you should capture this exception explicitly.
 */
public class BoxSearch {

  /** Search URL Template. */
  public static final URLTemplate SEARCH_URL_TEMPLATE = new URLTemplate("search");

  private final BoxAPIConnection api;

  /**
   * Constructs a Search to be used by everything.
   *
   * @param api the API connection to be used by the search.
   */
  public BoxSearch(BoxAPIConnection api) {
    this.api = api;
  }

  /**
   * Searches all descendant folders using a given query and query parameters.
   *
   * @param offset is the starting position.
   * @param limit the maximum number of items to return. The default is 30 and the maximum is 200.
   * @param bsp containing query and advanced search capabilities.
   * @return a PartialCollection containing the search results.
   */
  public PartialCollection<BoxItem.Info> searchRange(
      long offset, long limit, final BoxSearchParameters bsp) {
    QueryStringBuilder builder =
        bsp.getQueryParameters().appendParam("limit", limit).appendParam("offset", offset);
    URL url = SEARCH_URL_TEMPLATE.buildWithQuery(this.getAPI().getBaseURL(), builder.toString());
    BoxJSONRequest request = new BoxJSONRequest(this.getAPI(), url, "GET");
    try (BoxJSONResponse response = request.send()) {
      JsonObject responseJSON = Json.parse(response.getJSON()).asObject();
      String totalCountString = responseJSON.get("total_count").toString();
      long fullSize = Double.valueOf(totalCountString).longValue();
      PartialCollection<BoxItem.Info> results = new PartialCollection<>(offset, limit, fullSize);
      JsonArray jsonArray = responseJSON.get("entries").asArray();
      for (JsonValue value : jsonArray) {
        JsonObject jsonObject = value.asObject();
        BoxItem.Info parsedItemInfo =
            (BoxItem.Info) BoxResource.parseInfo(this.getAPI(), jsonObject);
        if (parsedItemInfo != null) {
          results.add(parsedItemInfo);
        }
      }
      return results;
    }
  }

  /**
   * Searches all descendant folders using a given query and query parameters.
   *
   * @param offset is the starting position.
   * @param limit the maximum number of items to return. The default is 30 and the maximum is 200.
   * @param bsp containing query and advanced search capabilities.
   * @return a PartialCollection containing the search results.
   */
  public PartialCollection<BoxSearchSharedLink> searchRangeIncludeSharedLinks(
      long offset, long limit, final BoxSearchParameters bsp) {
    QueryStringBuilder builder =
        bsp.getQueryParameters()
            .appendParam("include_recent_shared_links", "true")
            .appendParam("limit", limit)
            .appendParam("offset", offset);
    URL url = SEARCH_URL_TEMPLATE.buildWithQuery(this.getAPI().getBaseURL(), builder.toString());
    BoxJSONRequest request = new BoxJSONRequest(this.getAPI(), url, "GET");
    try (BoxJSONResponse response = request.send()) {
      JsonObject responseJSON = Json.parse(response.getJSON()).asObject();
      String totalCountString = responseJSON.get("total_count").toString();
      long fullSize = Double.valueOf(totalCountString).longValue();
      PartialCollection<BoxSearchSharedLink> results =
          new PartialCollection<>(offset, limit, fullSize);
      JsonArray jsonArray = responseJSON.get("entries").asArray();
      for (JsonValue value : jsonArray) {
        JsonObject jsonObject = value.asObject();
        BoxSearchSharedLink parsedItem = new BoxSearchSharedLink(jsonObject, this.getAPI());
        results.add(parsedItem);
      }
      return results;
    }
  }

  /**
   * Gets the API connection used by this resource.
   *
   * @return the API connection used by this resource.
   */
  public BoxAPIConnection getAPI() {
    return this.api;
  }
}
