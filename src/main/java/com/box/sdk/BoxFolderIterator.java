package com.box.sdk;

import java.io.IOException;
import java.util.Iterator;

public class BoxFolderIterator implements Iterator<BoxFolder> {
    private static final long LIMIT = 1000;
    private static final URLTemplate URL_TEMPLATE = new URLTemplate("folders/%s/items?limit=%d&offset=%d");

    private BoxAPIConnection api;
    private long offset;

    public BoxFolderIterator(BoxAPIConnection api) throws IOException {
        this.api = api;
        this.offset = 0;

        BoxAPIRequest request = new BoxAPIRequest(this.api, URL_TEMPLATE.build(), "GET");
        BoxJSONResponse response = (BoxJSONResponse) request.send();
    }

    public boolean hasNext() {
        return false;
    }

    public BoxFolder next() {
        return null;
    }

    public void remove() {
        throw new UnsupportedOperationException();
    }
}
