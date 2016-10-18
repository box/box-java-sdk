package com.box.sdk;

import java.net.URL;

import com.eclipsesource.json.JsonObject;

class BoxUserIterator extends BoxIteratorBase<BoxUser.Info> {
    BoxUserIterator(BoxAPIConnection api, URL url) {
        super(api, url);
    }

    public BoxUser.Info next() {
        JsonObject nextJSONObject = this.jsonIterator.next();
        String id = nextJSONObject.get("id").asString();

        BoxUser user = new BoxUser(this.api, id);
        return user.new Info(nextJSONObject);
    }
}
