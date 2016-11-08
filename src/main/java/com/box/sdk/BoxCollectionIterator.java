package com.box.sdk;

import java.net.URL;
import java.util.Iterator;

import com.eclipsesource.json.JsonObject;

/**
 * Paging implementation for {@link BoxCollection#getAllCollections(BoxAPIConnection)}.
 */
class BoxCollectionIterator implements Iterator<BoxCollection.Info> {

    /**
     * Default size of page.
     */
    private static final long LIMIT = 100;

    /**
     * The API connection to be used by the collection.
     */
    private final BoxAPIConnection api;

    /**
     * Paging iteration support.
     */
    private final JSONIterator jsonIterator;

    /**
     * Constructor.
     *
     * @param api
     *            the API connection to be used by the collection.
     * @param url
     *            for paging
     */
    BoxCollectionIterator(BoxAPIConnection api, URL url) {
        this.api = api;
        this.jsonIterator = new JSONIterator(api, url, LIMIT);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasNext() {
        return this.jsonIterator.hasNext();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BoxCollection.Info next() {
        JsonObject nextJSONObject = this.jsonIterator.next();
        String id = nextJSONObject.get("id").asString();

        BoxCollection collection = new BoxCollection(this.api, id);
        return collection.new Info(nextJSONObject);
    }

    /**
     * @throws UnsupportedOperationException
     */
    public void remove() {
        throw new UnsupportedOperationException();
    }
}
