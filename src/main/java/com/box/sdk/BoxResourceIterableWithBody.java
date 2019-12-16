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
public abstract class BoxResourceIterableWithBody<T> implements Iterable<T> {

    /**
     * Parameter for max page size.
     */
    public static final String PARAMETER_LIMIT = "limit";

    /**
     * Parameter for marker for the beginning of next page.
     */
    public static final String PARAMETER_MARKER = "marker";

    /**
     * Body Parameter for marker for the beginning of next page.
     */
    public static final String BODY_PARAMETER_MARKER_NEXT = "next_marker";

    /**
     * Body parameter for page entries.
     */
    public static final String BODY_PARAMETER_ENTRIES = "entries";

    /**
     * The API connection to be used by the resource.
     */
    private final BoxAPIConnection api;

    /**
     * To end-point with paging support.
     */
    private final URL url;

    /**
     * The maximum number of items to return in a page.
     */
    private final int limit;

    /**
     * The body to include in the request.
     */
    private final String body;

    /**
     * Construtor.
     *
     * @param api  the API connection to be used by the resource.
     * @param url  to endpoint with paging support.
     * @param limit the maximum number of items to return in a page.
     * @param body the body to send to the requested endpoint.
     */
    public BoxResourceIterableWithBody(BoxAPIConnection api, URL url, int limit, String body) {
        this.api = api;
        this.url = url;
        this.limit = limit;
        this.body = body;
    }

    /**
     * Factory to build a new instance for a received JSON item.
     *
     * @param jsonObject
     *            of the item
     * @return the item instance
     */
    protected abstract T factory(JsonObject jsonObject);

    /**
     * Builds internal read-only iterator over {@link BoxResource}-s.
     *
     * @return iterator implementation
     * @see Iterable#iterator()
     */
    @Override
    public Iterator<T> iterator() {
        return new IteratorImpl();
    }

    /**
     * Paging implementation.
     */
    private class IteratorImpl implements Iterator<T> {

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
            request.addHeader("Content-Type", "application/json");
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



        /**
         * {@inheritDoc}
         */
        @Override
        public boolean hasNext() {
            if (this.pageCursor < this.page.size()) {
                return true;
            }
            if (this.markerNext == null || this.markerNext.isEmpty()) {
                return false;
            }
            this.loadNextPage();
            return !this.page.isEmpty();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public T next() {
            if (!this.hasNext()) {
                throw new NoSuchElementException();
            }

            JsonObject entry = this.page.get(this.pageCursor++).asObject();
            return BoxResourceIterableWithBody.this.factory(entry);
        }

        /**
         * @throws UnsupportedOperationException
         */
        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
