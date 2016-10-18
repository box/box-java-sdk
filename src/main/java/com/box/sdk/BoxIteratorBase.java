package com.box.sdk;

import java.net.URL;
import java.util.Iterator;

abstract class BoxIteratorBase<T extends BoxResource.Info> implements Iterator<T> {
    private static final long LIMIT = 1000;

    protected final BoxAPIConnection api;
    protected final JSONIterator jsonIterator;

    BoxIteratorBase(BoxAPIConnection api, URL url) {
        this.api = api;
        this.jsonIterator = new JSONIterator(api, url, LIMIT);
    }

    public boolean hasNext() {
        return this.jsonIterator.hasNext();
    }

    abstract public T next();

    public void remove() {
        throw new UnsupportedOperationException();
    }
}
