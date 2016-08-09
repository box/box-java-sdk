package com.box.sdk;

import java.net.URL;
import java.util.Iterator;

import com.eclipsesource.json.JsonObject;

/**
 * {@link BoxWebHook}-s iterator.
 */
class BoxWebHookIterator implements Iterator<BoxWebHook.Info> {

    /**
     * Limit for page size.
     */
    private static final long LIMIT = 100;

    /**
     * The API connection to be used by the resource.
     */
    private final BoxAPIConnection api;

    /**
     * Iterator support.
     */
    private final JSONIterator jsonIterator;

    /**
     * Constructor.
     *
     * @param api
     *            the API connection to be used by the resource
     * @param url
     *            for {@link BoxWebHook}-s
     */
    BoxWebHookIterator(BoxAPIConnection api, URL url) {
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
    public BoxWebHook.Info next() {
        JsonObject nextJSONObject = this.jsonIterator.next();
        String id = nextJSONObject.get("id").asString();

        BoxWebHook webHook = new BoxWebHook(this.api, id);
        return webHook.new Info(nextJSONObject);
    }

    /**
     * @throws UnsupportedOperationException - read-only.
     */
    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

}
