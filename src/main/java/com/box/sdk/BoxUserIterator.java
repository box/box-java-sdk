package com.box.sdk;

import java.net.URL;

import com.eclipsesource.json.JsonObject;

class BoxUserIterator extends BoxIteratorBase<BoxUser.Info> {
    BoxUserIterator(BoxAPIConnection api, URL url) {
        super(api, url);
    }

    public BoxUser.Info next() {
        JsonObject nextJSONObject = this.nextJsonObject();
        String id = nextJSONObject.get("id").asString();

        BoxUser user = new BoxUser(this.getAPI(), id);
        return user.new Info(nextJSONObject);
    }
}
