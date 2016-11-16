package com.box.sdk;

import java.net.URL;
import java.util.Iterator;

import com.eclipsesource.json.JsonObject;


/**
 * An iterator object for {@link BoxTaskAssignment} object.
 * Supports offset-based paging.
 */
class BoxTaskAssignmentIterator implements Iterator<BoxTaskAssignment.Info> {

    /**
     * The limit of entries per response.
     */
    private static final long LIMIT = 100;

    /**
     * The API connection to be used.
     */
    private final BoxAPIConnection api;

    /**
     * Iterator object with paging support.
     */
    private final JSONIterator jsonIterator;

    /**
     * Creates new BoxTaskAssignment iterator.
     * @param api The API connection to be used by the iterator.
     * @param url The endpoint url.
     */
    BoxTaskAssignmentIterator(BoxAPIConnection api, URL url) {
        this.api = api;
        this.jsonIterator = new JSONIterator(api, url, LIMIT);
    }

    /**
     * @return false if current element is the last.
     */
    @Override
    public boolean hasNext() {
        return this.jsonIterator.hasNext();
    }


    /**
     * @return next BoxTaskAssignment.Info object in the list.
     */
    @Override
    public BoxTaskAssignment.Info next() {
        JsonObject nextJSONObject = this.jsonIterator.next();
        String id = nextJSONObject.get("id").asString();

        BoxTaskAssignment assignment = new BoxTaskAssignment(this.api, id);
        return assignment.new Info(nextJSONObject);
    }

    /**
     * Remove operation is not supported.
     */
    public void remove() {
        throw new UnsupportedOperationException();
    }
}
