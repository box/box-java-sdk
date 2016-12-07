package com.box.sdk;

import java.net.URL;
import java.util.Iterator;

import com.eclipsesource.json.JsonObject;

/**
 * An iterator object for {@link BoxGroupMembership} object.
 * Supports offset-based paging.
 */
class BoxGroupMembershipIterator implements Iterator<BoxGroupMembership.Info> {

    /**
     * The limit of entries per response.
     */
    private static final long LIMIT = 1000;

    /**
     * The API connection to be used.
     */
    private final BoxAPIConnection api;

    /**
     * Iterator object with paging support.
     */
    private final JSONIterator jsonIterator;

    /**
     * Creates new BoxGroupMembership iterator.
     * @param api The API connection to be used by the iterator.
     * @param url The endpoint url.
     */
    BoxGroupMembershipIterator(BoxAPIConnection api, URL url) {
        this.api = api;
        this.jsonIterator = new JSONIterator(api, url, LIMIT);
    }

    /**
     * @return false if current element is the last.
     */
    public boolean hasNext() {
        return this.jsonIterator.hasNext();
    }

    /**
     * @return next BoxGroupMembeship object in the list.
     */
    public BoxGroupMembership.Info next() {
        JsonObject nextJSONObject = this.jsonIterator.next();
        String id = nextJSONObject.get("id").asString();

        BoxGroupMembership membership = new BoxGroupMembership(this.api, id);
        return membership.new Info(nextJSONObject);
    }

    /**
     * Remove operation is not supported.
     */
    public void remove() {
        throw new UnsupportedOperationException();
    }
}
