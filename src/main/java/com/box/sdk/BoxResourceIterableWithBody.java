package com.box.sdk;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.NoSuchElementException;

import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;

/**
 * Overriden implementation of marker based paging for sending request body.
 *
 * @param <T>
 *           type of iterated entity
 */
public abstract class BoxResourceIterableWithBody<T> extends BoxResourceIterable<T> {

    /**
     * The API connection to be used by the resource.
     */
    private final BoxAPIConnection api;

    /**
     * To end-point with paging support.
     */
    private final URL url;

    /**
     * The body to include in the request.
     */
    private final String body;

    /**
     * The maximum number of items to return in a page.
     */
    private final int limit;

    /**
     * Construtor.
     *
     * @param api  the API connection to be used by the resource.
     * @param url  to endpoint with paging support.
     * @param body the body to send to the requested endpoint.
     * @param limit the maximum number of items to return in a page.
     */
    public BoxResourceIterableWithBody(BoxAPIConnection api, URL url, String body, int limit) {
        super(api, url, limit);
        this.api = api;
        this.url = url;
        this.body = body;
        this.limit = limit;
    }

    /**
     * Paging implementation.
     */
    private abstract class IteratorImpl implements Iterator<T> {

        /**
         * Base 64 encoded string that represents where the paging should being. It should be left blank to begin
         * paging.
         */
        private String markerNext;

        /**
         * Current loaded page.
         */
        private JsonArray page;

        /**
         * Cursor within the page (index of a next item for read).
         */
        private int pageCursor;

        /**
         * Constructor.
         */
        IteratorImpl() {
            this.loadNextPage();
        }

        /**
         * Loads next page.
         */
        private void loadNextPage() {
            String existingQuery = BoxResourceIterableWithBody.this.url.getQuery();
            QueryStringBuilder builder = new QueryStringBuilder(existingQuery);
            builder.appendParam(PARAMETER_LIMIT, BoxResourceIterableWithBody.this.limit);
            if (this.markerNext != null) {
                builder.appendParam(PARAMETER_MARKER, this.markerNext);
            }

            URL url;
            try {
                url = builder.addToURL(BoxResourceIterableWithBody.this.url);
            } catch (MalformedURLException e) {
                throw new BoxAPIException("Couldn't append a query string to the provided URL.");
            }

            BoxAPIRequest request = new BoxAPIRequest(BoxResourceIterableWithBody.this.api, url, "POST");
            request.setBody(body);
            BoxJSONResponse response = (BoxJSONResponse) request.send();
            JsonObject pageBody = JsonObject.readFrom(response.getJSON());

            JsonValue markerNextValue = pageBody.get(BODY_PARAMETER_MARKER_NEXT);
            if (markerNextValue != null && markerNextValue.isString()) {
                this.markerNext = markerNextValue.asString();
            } else {
                this.markerNext = null;
            }

            this.page = pageBody.get(BODY_PARAMETER_ENTRIES).asArray();
            this.pageCursor = 0;
        }
    }
}
