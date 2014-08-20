package com.box.sdk;

import java.net.URL;
import java.util.Iterator;
import java.util.NoSuchElementException;

import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;

public class BoxItemIterator implements Iterator<BoxItem> {
    private static final long LIMIT = 1000;
    private static final URLTemplate URL_TEMPLATE = new URLTemplate("folders/%s/items?limit=%d&offset=%d");

    private final BoxAPIConnection api;
    private final String folderID;

    private long offset;
    private long totalCount;
    private boolean hasMorePages;
    private Iterator<JsonValue> currentPage;
    private JsonObject nextJsonObject;

    public BoxItemIterator(BoxAPIConnection api, String folderID) {
        this.api = api;
        this.folderID = folderID;
        this.nextJsonObject = null;
        this.loadNextPage();
    }

    public boolean hasNext() {
        if (this.nextJsonObject == null) {
            this.nextJsonObject = this.loadNextJsonObject();
        }

        return this.nextJsonObject != null;
    }

    public BoxItem next() {
        if (this.nextJsonObject == null) {
            this.nextJsonObject = this.loadNextJsonObject();
        }

        if (this.nextJsonObject == null) {
            throw new NoSuchElementException();
        }

        String type = this.nextJsonObject.get("type").asString();
        String id = this.nextJsonObject.get("id").asString();
        BoxItem nextItem;
        if (type.equals("folder")) {
            nextItem = new BoxFolder(this.api, id);
        } else if (type.equals("file")) {
            nextItem = new BoxFile(this.api, id);
        } else {
            assert false : "Unsupported item type: " + type;
            throw new BoxAPIException("Unsupported item type: " + type);
        }

        this.nextJsonObject = null;
        return nextItem;
    }

    public void remove() {
        throw new UnsupportedOperationException();
    }

    private void loadNextPage() {
        URL url = URL_TEMPLATE.build(this.api.getBaseURL(), this.folderID, LIMIT, this.offset);
        BoxAPIRequest request = new BoxAPIRequest(this.api, url, "GET");
        BoxJSONResponse response = (BoxJSONResponse) request.send();
        String json = response.getJSON();

        JsonObject jsonObject = JsonObject.readFrom(json);
        this.totalCount = jsonObject.get("total_count").asLong();
        this.offset = jsonObject.get("offset").asLong();
        this.hasMorePages = (this.offset + LIMIT) < this.totalCount;

        JsonArray jsonArray = jsonObject.get("entries").asArray();
        this.currentPage = jsonArray.iterator();
    }

    private JsonObject loadNextJsonObject() {
        while (this.currentPage.hasNext() || this.hasMorePages) {
            while (this.currentPage.hasNext()) {
                JsonObject jsonObject = this.currentPage.next().asObject();
                String type = jsonObject.get("type").asString();
                if (type.equals("file") || type.equals("folder")) {
                    return jsonObject;
                }
            }

            if (this.hasMorePages) {
                this.loadNextPage();
            }
        }

        return null;
    }
}
