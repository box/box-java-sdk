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
                return null;
        }
    }

    public enum Type {
        ITEM_CREATE,
        ITEM_UPLOAD,
        COMMENT_CREATE,
        ITEM_DOWNLOAD,
        ITEM_PREVIEW,
        ITEM_MOVE,
        ITEM_COPY,
        TASK_ASSIGNMENT_CREATE,
        LOCK_CREATE,
        LOCK_DESTROY,
        ITEM_TRASH,
        ITEM_UNDELETE_VIA_TRASH,
        COLLAB_ADD_COLLABORATOR,
        COLLAB_INVITE_COLLABORATOR,
        ITEM_SYNC,
        ITEM_UNSYNC,
        ITEM_RENAME,
        ITEM_SHARED_CREATE,
        ITEM_SHARED_UNSHARE,
        ITEM_SHARED,
        TAG_ITEM_CREATE,
        ADD_LOGIN_ACTIVITY_DEVICE,
        REMOVE_LOGIN_ACTIVITY_DEVICE,
        CHANGE_ADMIN_ROLE;
    }
}
