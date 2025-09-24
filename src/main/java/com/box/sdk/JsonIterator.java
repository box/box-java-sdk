package com.box.sdk;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.NoSuchElementException;

class JsonIterator {
  private final BoxAPIConnection api;
  private final URL url;
  private PagingParameters pagingParameters;
  private boolean hasMorePages;
  private Iterator<JsonValue> currentPage;
  private JsonObject nextJsonObject;
  private Filter<JsonObject> filter;

  JsonIterator(BoxAPIConnection api, URL url, PagingParameters pagingParameters) {
    this.api = api;
    this.url = url;
    this.pagingParameters = pagingParameters;
  }

  public boolean hasNext() {
    if (this.nextJsonObject == null) {
      this.nextJsonObject = this.loadNextJsonObject();
    }

    return this.nextJsonObject != null;
  }

  public JsonObject next() {
    if (this.nextJsonObject == null) {
      this.nextJsonObject = this.loadNextJsonObject();
    }

    if (this.nextJsonObject == null) {
      throw new NoSuchElementException();
    }

    JsonObject next = this.nextJsonObject;
    this.nextJsonObject = null;
    return next;
  }

  public void remove() {
    throw new UnsupportedOperationException();
  }

  public void setFilter(Filter<JsonObject> filter) {
    this.filter = filter;
  }

  private void loadNextPage() {
    QueryStringBuilder builder = pagingParameters.asQueryStringBuilder();

    URL url;
    try {
      url = builder.addToURL(this.url);
    } catch (MalformedURLException e) {
      throw new BoxAPIException("Couldn't append a query string to the provided URL.");
    }

    BoxJSONRequest request = new BoxJSONRequest(this.api, url, "GET");
    try (BoxJSONResponse response = request.send()) {
      String json = response.getJSON();

      JsonObject responseObject = Json.parse(json).asObject();

      if (pagingParameters.isMarkerBasedPaging()) {
        continueAsMarkerBasedPaging(responseObject);
      } else {
        continueAsOffsetBasedPaging(responseObject);
      }

      JsonArray jsonArray = responseObject.get("entries").asArray();
      this.currentPage = jsonArray.iterator();
    }
  }

  private void continueAsOffsetBasedPaging(JsonObject response) {
    try {
      long offset = response.get("offset").asLong();
      long totalCount = response.get("total_count").asLong();
      hasMorePages = offset + pagingParameters.getLimit() < totalCount;
      this.pagingParameters = pagingParameters.nextOffset(offset);
    } catch (NullPointerException e) {
      hasMorePages = false;
    }
  }

  private void continueAsMarkerBasedPaging(JsonObject response) {
    String nextMarker = response.getString("next_marker", null);
    this.hasMorePages = nextMarker != null && nextMarker.length() > 0;
    this.pagingParameters = pagingParameters.nextMarker(nextMarker);
  }

  private JsonObject loadNextJsonObject() {
    if (this.currentPage == null) {
      this.loadNextPage();
    }

    while (this.currentPage.hasNext() || this.hasMorePages) {
      while (this.currentPage.hasNext()) {
        JsonObject jsonObject = this.currentPage.next().asObject();
        if (this.filter == null || this.filter.shouldInclude(jsonObject)) {
          return jsonObject;
        }
      }

      if (this.hasMorePages) {
        this.loadNextPage();
      }
    }

    return null;
  }
}
