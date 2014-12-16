package com.box.sdk;

import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;

/**
 * Represents an event that was fired off by the Box events API.
 */
public class BoxEvent extends BoxResource {
    private BoxResource.Info sourceInfo;
    private BoxEvent.Type type;

    /**
     * Constructs a BoxEvent from a JSON string.
     * @param  api  the API connection to be used by the file.
     * @param  json the JSON encoded event.
     */
    public BoxEvent(BoxAPIConnection api, String json) {
        this(api, JsonObject.readFrom(json));
    }

    BoxEvent(BoxAPIConnection api, JsonObject jsonObject) {
        super(api, jsonObject.get("event_id").asString());

        for (JsonObject.Member member : jsonObject) {
            if (member.getValue().isNull()) {
                continue;
            }

            this.parseJsonMember(member);
        }
    }

    /**
     * Gets info about the source of this event.
     * @return info about the source of this event.
     */
    public BoxResource.Info getSourceInfo() {
        return this.sourceInfo;
    }

    /**
     * Gets the type of this event.
     * @return the type of this event.
     */
    public BoxEvent.Type getType() {
        return this.type;
    }

    void parseJsonMember(JsonObject.Member member) {
        JsonValue value = member.getValue();
        if (value.isNull()) {
            return;
        }

        switch (member.getName()) {
            case "source":
                this.sourceInfo = BoxResource.parseInfo(this.getAPI(), value.asObject());
                break;
            case "event_type":
                this.type = BoxEvent.Type.valueOf(value.asString());
                break;
            default:
                break;
        }
    }

    /**
     * Enumerates the possible types for an event.
     */
    public enum Type {
        /**
         * An file or folder was created.
         */
        ITEM_CREATE,

        /**
         * An file or folder was uploaded.
         */
        ITEM_UPLOAD,

        /**
         * A comment was created on a folder, file, or other comment.
         */
        COMMENT_CREATE,

        /**
         * An file or folder was downloaded.
         */
        ITEM_DOWNLOAD,

        /**
         * A file was previewed.
         */
        ITEM_PREVIEW,

        /**
         * A file or folder was moved.
         */
        ITEM_MOVE,

        /**
         * A file or folder was copied.
         */
        ITEM_COPY,

        /**
         * A task was assigned.
         */
        TASK_ASSIGNMENT_CREATE,

        /**
         * A file was locked.
         */
        LOCK_CREATE,

        /**
         * A file was unlocked.
         */
        LOCK_DESTROY,

        /**
         * A file or folder was deleted.
         */
        ITEM_TRASH,

        /**
         * A file or folder was recovered from the trash.
         */
        ITEM_UNDELETE_VIA_TRASH,

        /**
         * A collaborator was added to a folder.
         */
        COLLAB_ADD_COLLABORATOR,

        /**
         * A collaborator was removed from a folder.
         */
        COLLAB_REMOVE_COLLABORATOR,

        /**
         * A collaborator was invited to a folder.
         */
        COLLAB_INVITE_COLLABORATOR,

        /**
         * A folder was marked for sync.
         */
        ITEM_SYNC,

        /**
         * A folder was un-marked for sync.
         */
        ITEM_UNSYNC,

        /**
         * A file or folder was renamed.
         */
        ITEM_RENAME,

        /**
         * A file or folder was enabled for sharing.
         */
        ITEM_SHARED_CREATE,

        /**
         * A file or folder was disabled for sharing.
         */
        ITEM_SHARED_UNSHARE,

        /**
         * A folder was shared.
         */
        ITEM_SHARED,

        /**
         * A tag was added to a file or folder.
         */
        TAG_ITEM_CREATE,

        /**
         * A user logged in from a new device.
         */
        ADD_LOGIN_ACTIVITY_DEVICE,

        /**
         * A user session associated with an app was invalidated.
         */
        REMOVE_LOGIN_ACTIVITY_DEVICE,

        /**
         * An admin role changed for a user.
         */
        CHANGE_ADMIN_ROLE;
    }
}
