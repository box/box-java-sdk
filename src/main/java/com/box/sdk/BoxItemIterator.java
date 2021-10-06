package com.box.sdk;

import com.eclipsesource.json.JsonObject;
import java.net.URL;
import java.util.Iterator;

class BoxItemIterator implements Iterator<BoxItem.Info> {
    private static final long LIMIT = 1000;

    private final BoxAPIConnection api;
    private final JsonIterator iterator;

    BoxItemIterator(BoxAPIConnection api, URL url) {
        this.api = api;

        this.iterator = new OffsetBasedJsonIterator(api, url, LIMIT);
        this.iterator.setFilter(new Filter<JsonObject>() {
            @Override
            public boolean shouldInclude(JsonObject jsonObject) {
                String type = jsonObject.get("type").asString();
                return (type.equals("file") || type.equals("folder") || type.equals("web_link"));
            }
        });
    }

    BoxItemIterator(BoxAPIConnection api, URL url, long limit, long offset) {
        this.api = api;

        this.iterator = new OffsetBasedJsonIterator(api, url, limit, offset);
        this.iterator.setFilter(new Filter<JsonObject>() {
            @Override
            public boolean shouldInclude(JsonObject jsonObject) {
                String type = jsonObject.get("type").asString();
                return (type.equals("file") || type.equals("folder") || type.equals("web_link"));
            }
        });
    }

    BoxItemIterator(BoxAPIConnection api, URL url, PagingParameters pagingParameters) {
        this.api = api;
        this.iterator = new MarkerBasedJsonIterator(api, url, pagingParameters);
        this.iterator.setFilter(new Filter<JsonObject>() {
            @Override
            public boolean shouldInclude(JsonObject jsonObject) {
                String type = jsonObject.get("type").asString();
                return (type.equals("file") || type.equals("folder") || type.equals("web_link"));
            }
        });
    }

    public boolean hasNext() {
        return this.iterator.hasNext();
    }

    public BoxItem.Info next() {
        JsonObject nextJSONObject = this.iterator.next();
        String type = nextJSONObject.get("type").asString();
        String id = nextJSONObject.get("id").asString();

        BoxItem.Info nextItemInfo;
        if (type.equals("folder")) {
            BoxFolder folder = new BoxFolder(this.api, id);
            nextItemInfo = folder.new Info(nextJSONObject);
        } else if (type.equals("file")) {
            BoxFile file = new BoxFile(this.api, id);
            nextItemInfo = file.new Info(nextJSONObject);
        } else if (type.equals("web_link")) {
            BoxWebLink link = new BoxWebLink(this.api, id);
            nextItemInfo = link.new Info(nextJSONObject);
        } else {
            assert false : "Unsupported item type: " + type;
            throw new BoxAPIException("Unsupported item type: " + type);
        }

        return nextItemInfo;
    }

    public void remove() {
        throw new UnsupportedOperationException();
    }
}
