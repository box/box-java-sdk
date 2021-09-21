package com.box.sdk;

import com.eclipsesource.json.JsonObject;
import java.net.URL;
import java.util.Iterator;

class BoxGroupIterator implements Iterator<BoxGroup.Info> {
    private static final long LIMIT = 1000;

    private final BoxAPIConnection api;
    private final JSONIterator jsonIterator;

    BoxGroupIterator(BoxAPIConnection api, URL url) {
        this.api = api;
        this.jsonIterator = new JSONIterator(api, url, LIMIT);
    }

    public boolean hasNext() {
        return this.jsonIterator.hasNext();
    }

    public BoxGroup.Info next() {
        JsonObject nextJSONObject = this.jsonIterator.next();
        String id = nextJSONObject.get("id").asString();

        BoxGroup group = new BoxGroup(this.api, id);
        return group.new Info(nextJSONObject);
    }

    public void remove() {
        throw new UnsupportedOperationException();
    }
}
