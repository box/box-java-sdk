package com.box.sdk;

import java.net.URL;

import com.eclipsesource.json.JsonObject;

class BoxGroupIterator extends BoxIteratorBase<BoxGroup.Info> {
    BoxGroupIterator(BoxAPIConnection api, URL url) {
        super(api, url);
    }

    public BoxGroup.Info next() {
        JsonObject nextJSONObject = this.jsonIterator.next();
        String id = nextJSONObject.get("id").asString();

        BoxGroup group = new BoxGroup(this.api, id);
        return group.new Info(nextJSONObject);
    }
}
