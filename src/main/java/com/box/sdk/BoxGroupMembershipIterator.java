package com.box.sdk;

import java.net.URL;
import java.util.Iterator;

import com.eclipsesource.json.JsonObject;

class BoxGroupMembershipIterator implements Iterator<BoxGroupMembership.Info> {
    private static final long LIMIT = 1000;

    private final BoxAPIConnection api;
    private final JSONIterator jsonIterator;

    BoxGroupMembershipIterator(BoxAPIConnection api, URL url) {
        this.api = api;
        this.jsonIterator = new JSONIterator(api, url, LIMIT);
    }

    public boolean hasNext() {
        return this.jsonIterator.hasNext();
    }

    public BoxGroupMembership.Info next() {
        JsonObject nextJSONObject = this.jsonIterator.next();
        String id = nextJSONObject.get("id").asString();

        BoxGroupMembership group = new BoxGroupMembership(this.api, id);
        return group.new Info(nextJSONObject);
    }

    public void remove() {
        throw new UnsupportedOperationException();
    }
}
