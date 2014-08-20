package com.box.sdk;

import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;

public class BoxEvent extends BoxResource {
    private BoxResource source;
    private BoxEvent.Type type;

    public BoxEvent(BoxAPIConnection api, JsonObject jsonObject) {
        super(api, jsonObject.get("event_id").asString());

        for (JsonObject.Member member : jsonObject) {
            if (member.getValue().isNull()) {
                continue;
            }

            this.parseJsonMember(member);
        }
    }

    public BoxResource getSource() {
        return this.source;
    }

    public BoxEvent.Type getType() {
        return this.type;
    }

    protected void parseJsonMember(JsonObject.Member member) {
        JsonValue value = member.getValue();
        if (value.isNull()) {
            return;
        }

        switch (member.getName()) {
            case "source":
                this.source = this.parseSource(value.asObject());
                break;
            case "event_type":
                this.type = BoxEvent.Type.valueOf(value.asString());
                break;
            default:
                break;
        }
    }

    private BoxResource parseSource(JsonObject jsonObject) {
        String type = jsonObject.get("type").asString();
        switch (type) {
            case "folder":
                return new BoxFolder(this.getAPI(), jsonObject.get("id").asString());
            case "file":
                return new BoxFile(this.getAPI(), jsonObject.get("id").asString());
            default:
                throw new BoxAPIException("Unrecognized event source type.");
        }
    }

    public enum Type {
        ITEM_CREATE,
        ITEM_TRASH;

        static Type parse(String string) {
            switch (string) {
                case "ITEM_CREATE":
                    return ITEM_CREATE;
                case "ITEM_TRASH":
                    return ITEM_TRASH;
                default:
                    throw new BoxAPIException("Unrecognized event type.");
            }
        }
    }
}
