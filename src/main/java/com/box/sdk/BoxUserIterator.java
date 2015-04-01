package com.box.sdk;

import java.net.URL;
import java.util.Iterator;

import com.eclipsesource.json.JsonObject;

class BoxUserIterator implements Iterator<BoxUser.Info> {
    private static final long LIMIT = 1000;

    private final BoxAPIConnection api;
    private final JSONIterator jsonIterator;

    BoxUserIterator(BoxAPIConnection api, URL url) {
        this.api = api;
        this.jsonIterator = new JSONIterator(api, url, LIMIT);
    }

    public boolean hasNext() {
        return this.jsonIterator.hasNext();
    }

    public BoxUser.Info next() {
        JsonObject nextJSONObject = this.jsonIterator.next();
        String id = nextJSONObject.get("id").asString();

        BoxUser user = new BoxUser(this.api, id);
        return user.new Info(nextJSONObject);
    }

    public void remove() {
        throw new UnsupportedOperationException();
    }
}
