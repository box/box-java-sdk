package com.box.sdk;

import java.net.URL;
import java.util.Iterator;

import com.eclipsesource.json.JsonObject;

abstract class BoxIteratorBase<T extends BoxResource.Info> implements Iterator<T> {
    private static final long LIMIT = 1000;

    private final BoxAPIConnection api;
    private final JSONIterator jsonIterator;

    BoxIteratorBase(BoxAPIConnection api, URL url) {
        this.api = api;
        this.jsonIterator = new JSONIterator(api, url, LIMIT);
    }

    protected BoxAPIConnection getAPI() {
        return this.api;
    }

    public boolean hasNext() {
        return this.jsonIterator.hasNext();
    }

    protected JsonObject nextJsonObject() {
        return this.jsonIterator.next();
    }

    public abstract T next();

    public void remove() {
        throw new UnsupportedOperationException();
    }
}
