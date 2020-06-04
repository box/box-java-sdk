package com.box.sdk;

import java.net.URL;
import java.util.Iterator;

import com.eclipsesource.json.JsonObject;

class BoxCollectionIterator implements Iterator<BoxCollection.Info> {
    private static final long LIMIT = 100;
    private final BoxAPIConnection api;
    private final JSONIterator jsonIterator;

    BoxCollectionIterator(BoxAPIConnection api, URL url) {
        this.api = api;
        this.jsonIterator = new JSONIterator(api, url, LIMIT);
    }

    @Override
    public boolean hasNext() {
        return this.jsonIterator.hasNext();
    }

    @Override
    public BoxCollection.Info next() {
        JsonObject nextJSONObject = this.jsonIterator.next();
        String id = nextJSONObject.get("id").asString();

        BoxCollection collection = new BoxCollection(this.api, id);
        return collection.new Info(nextJSONObject);
    }

    public void remove() {
        throw new UnsupportedOperationException();
    }
}
