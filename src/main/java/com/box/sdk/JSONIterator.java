package com.box.sdk;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.NoSuchElementException;

import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;

class JSONIterator implements Iterator<JsonObject> {
    private final BoxAPIConnection api;
    private final URL url;

    private long limit;
    private long offset;
    private long totalCount;
    private boolean hasMorePages;
    private Iterator<JsonValue> currentPage;
    private JsonObject nextJsonObject;
    private Filter<JsonObject> filter;

    public JSONIterator(BoxAPIConnection api, URL url, long limit) {
        this.api = api;
        this.url = url;
        this.limit = limit;
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
        String offsetString;
        String existingQuery = this.url.getQuery();
        QueryStringBuilder builder = new QueryStringBuilder(existingQuery);
        builder.appendParam("limit", this.limit);
        builder.appendParam("offset", this.offset);

        URL url;
        try {
            url = builder.addToURL(this.url);
        } catch (MalformedURLException e) {
            throw new BoxAPIException("Couldn't append a query string to the provided URL.");
        }

        BoxAPIRequest request = new BoxAPIRequest(this.api, url, "GET");
        BoxJSONResponse response = (BoxJSONResponse) request.send();
        String json = response.getJSON();

        JsonObject jsonObject = JsonObject.readFrom(json);
        String totalCountString = jsonObject.get("total_count").toString();
        this.totalCount = Double.valueOf(totalCountString).longValue();

        try {
            offsetString = jsonObject.get("offset").toString();
            this.hasMorePages = (this.offset + this.limit) < this.totalCount;
            this.offset = Double.valueOf(offsetString).longValue() + this.limit;

        } catch (NullPointerException e) {
            this.hasMorePages = false;
        }

        JsonArray jsonArray = jsonObject.get("entries").asArray();
        this.currentPage = jsonArray.iterator();
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
