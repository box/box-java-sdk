package com.box.sdk;

import java.net.URL;
import java.util.Iterator;

public class BoxTrash implements Iterable<BoxItem.Info> {
    private static final long LIMIT = 1000;
    private static final URLTemplate GET_ITEMS_URL = new URLTemplate("folders/trash/items/");

    private final BoxAPIConnection api;

    public BoxTrash(BoxAPIConnection api) {
        this.api = api;
    }

    public Iterator<BoxItem.Info> iterator() {
        URL url = GET_ITEMS_URL.build(this.api.getBaseURL());
        return new BoxItemIterator(this.api, url);
    }
}
