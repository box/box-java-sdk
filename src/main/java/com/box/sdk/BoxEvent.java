package com.box.sdk;

import java.text.ParseException;
import java.util.Date;

import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;

/**
 * Represents an event that was fired off by the Box events API.
 */
@BoxResourceType("event")
public class BoxEvent extends BoxResource {
    private BoxResource.Info sourceInfo;
    private BoxEvent.Type type;
    private JsonObject sourceJSON;
    private Date createdAt;
    private String ipAddress;
    private JsonObject additionalDetails;
    private BoxCollaborator.Info accessibleBy;
    private BoxUser.Info createdBy;
    private String sessionID;

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
     *
     * <p>Note that there is a bug in the enterprise event stream where certain event sources don't correctly map to a
     * BoxResource.Info. In the case where the event source JSON cannot be mapped to a BoxResource.Info, you can use the
     * {@link #getSourceJSON} method to access the raw JSON representation of the event source.</p>
     *
     * @return info about the source of this event.
     */
    public BoxResource.Info getSourceInfo() {
        return this.sourceInfo;
    }

    /**
     * Gets the raw JSON object containing information about the source of this event.
     *
     * <p>This method can be used to work around bugs in the enterprise events API where some enterprise event sources
     * don't correctly map to a BoxResource.Info. In this case, this method can be used to access the raw JSON
     * directly.</p>
     *
     * @return the JSON representation of the source of this event.
     */
    public JsonObject getSourceJSON() {
        return this.sourceJSON;
    }

    /**
     * Gets the type of this event.
     * @return the type of this event.
     */
    public BoxEvent.Type getType() {
        return this.type;
    }


    /**
     * Gets the time that this event was created.
     * @return the time that this event was created.
     */
    public Date getCreatedAt() {
        return this.createdAt;
    }

    /**
     * Gets the IP address of the user that triggered this event.
     * @return the IP address of the user that triggered this event.
     */
    public String getIPAddress() {
        return this.ipAddress;
    }

    /**
     * Gets a JSON object containing additional details about this event.
     *
     * <p>The fields and data within the returned JSON object will vary depending on the type of the event.</p>
     *
     * @return a JSON object containing additional details about this event.
     */
    public JsonObject getAdditionalDetails() {
        return this.additionalDetails;
    }

    /**
     * Gets info about the collaborator who was given access to a folder within the current enterprise.
     *
     * <p>This field is only populated when the event is related to a collaboration that occurred within an enterprise.
     * </p>
     *
     * @return info about the collaborator who was given access to a folder within the current enterprise.
     */
    public BoxCollaborator.Info getAccessibleBy() {
        return this.accessibleBy;
    }

    /**
     * Gets info about the user that triggered this event.
     * @return info about the user that triggered this event.
     */
    public BoxUser.Info getCreatedBy() {
        return this.createdBy;
    }

    /**
     * Gets the session ID of the user that triggered this event.
     * @return the session ID of the user that triggered this event.
     */
    public String getSessionID() {
        return this.sessionID;
    }

    void parseJsonMember(JsonObject.Member member) {
        JsonValue value = member.getValue();
        if (value.isNull()) {
            return;
        }

        String memberName = member.getName();
        if (memberName.equals("source")) {
            // Parsing the source might fail due to a bug in the enterprise event stream where the API returns JSON that
            // doesn't correctly map to a BoxResource.Info. If this happens, we set the sourceInfo to null and expect
            // the caller to use the getSourceJSON() method instead.
            try {
                this.sourceInfo = BoxResource.parseInfo(this.getAPI(), value.asObject());
            } catch (Exception e) {
                this.sourceInfo = null;
            }
            this.sourceJSON = JsonObject.unmodifiableObject(value.asObject());
        } else if (memberName.equals("event_type")) {
            String stringValue = value.asString();
            for (Type t : Type.values()) {
                if (t.name().equals(stringValue)) {
                    this.type = t;
                    break;
                }
            }

            if (this.type == null) {
                this.type = Type.UNKNOWN;
            }
        } else if (memberName.equals("created_at")) {
            try {
                this.createdAt = BoxDateFormat.parse(value.asString());
            } catch (ParseException e) {
                assert false : "A ParseException indicates a bug in the SDK.";
            }
        } else if (memberName.equals("ip_address")) {
            this.ipAddress = value.asString();
        } else if (memberName.equals("additional_details")) {
            this.additionalDetails = value.asObject();
        } else if (memberName.equals("accessible_by")) {
            this.accessibleBy = (BoxCollaborator.Info) BoxResource.parseInfo(this.getAPI(), value.asObject());
        } else if (memberName.equals("created_by")) {
            this.createdBy = (BoxUser.Info) BoxResource.parseInfo(this.getAPI(), value.asObject());
        } else if (memberName.equals("session_id")) {
            this.sessionID = value.asString();
        }
    }

    /**
     * Enumerates the possible types for an event.
     */
    public enum Type {
        /**
         * The type of the event is unknown.
         */
        UNKNOWN,

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
         * A collaborator's role was change in a folder.
         */
        COLLAB_ROLE_CHANGE,

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
        CHANGE_ADMIN_ROLE,

        /**
         * A user was added to a group. This is an enterprise-only event.
         */
        GROUP_ADD_USER,

        /**
         * A user was created. This is an enterprise-only event.
         */
        NEW_USER,

        /**
         * A group was created. This is an enterprise-only event.
         */
        GROUP_CREATION,

        /**
         * A group was deleted. This is an enterprise-only event.
         */
        GROUP_DELETION,

        /**
         * A user was deleted. This is an enterprise-only event.
         */
        DELETE_USER,

        /**
         * A group was edited. This is an enterprise-only event.
         */
        GROUP_EDITED,

        /**
         * A user was edited. This is an enterprise-only event.
         */
        EDIT_USER,

        /**
         * A group was granted access to a folder. This is an enterprise-only event.
         */
        GROUP_ADD_FOLDER,

        /**
         * A user was removed from a group. This is an enterprise-only event.
         */
        GROUP_REMOVE_USER,

        /**
         * A group had its access to a folder removed. This is an enterprise-only event.
         */
        GROUP_REMOVE_FOLDER,

        /**
         * An administrator logged in. This is an enterprise-only event.
         */
        ADMIN_LOGIN,

        /**
         * A device was associated with a user. This is an enterprise-only event.
         */
        ADD_DEVICE_ASSOCIATION,

        /**
         * There was a failed login attempt. This is an enterprise-only event.
         */
        FAILED_LOGIN,

        /**
         * There was a successful login. This is an enterprise-only event.
         */
        LOGIN,

        /**
         * A user's OAuth2 access token was refreshed. This is an enterprise-only event.
         */
        USER_AUTHENTICATE_OAUTH2_TOKEN_REFRESH,

        /**
         * A device was disassociated with a user. This is an enterprise-only event.
         */
        REMOVE_DEVICE_ASSOCIATION,

        /**
         * A user agreed to the terms of service. This is an enterprise-only event.
         */
        TERMS_OF_SERVICE_AGREE,

        /**
         * A user rejected the terms of service. This is an enterprise-only event.
         */
        TERMS_OF_SERVICE_REJECT,

        /**
         * An item was copied. This is an enterprise-only event.
         */
        COPY,

        /**
         * An item was deleted. This is an enterprise-only event.
         */
        DELETE,

        /**
         * An item was downloaded. This is an enterprise-only event.
         */
        DOWNLOAD,

        /**
         * An item was edited. This is an enterprise-only event.
         */
        EDIT,

        /**
         * An item was locked. This is an enterprise-only event.
         */
        LOCK,

        /**
         * An item was moved. This is an enterprise-only event.
         */
        MOVE,

        /**
         * An item was previewed. This is an enterprise-only event.
         */
        PREVIEW,

        /**
         * An item was renamed. This is an enterprise-only event.
         */
        RENAME,

        /**
         * An item was set to be auto-deleted. This is an enterprise-only event.
         */
        STORAGE_EXPIRATION,

        /**
         * An item was undeleted. This is an enterprise-only event.
         */
        UNDELETE,

        /**
         * An item was unlocked. This is an enterprise-only event.
         */
        UNLOCK,

        /**
         * An item was uploaded. This is an enterprise-only event.
         */
        UPLOAD,

        /**
         * An shared link was created for an item. This is an enterprise-only event.
         */
        SHARE,

        /**
         * The shared link for an item was updated. This is an enterprise-only event.
         */
        ITEM_SHARED_UPDATE,

        /**
         * The expiration time for a shared link was extended. This is an enterprise-only event.
         */
        UPDATE_SHARE_EXPIRATION,

        /**
         * The expiration time was set for a shared link. This is an enterprise-only event.
         */
        SHARE_EXPIRATION,

        /**
         * The shared link for an item was REMOVE_DEVICE_ASSOCIATION. This is an enterprise-only event.
         */
        UNSHARE,

        /**
         * A user accepted a collaboration invite. This is an enterprise-only event.
         */
        COLLABORATION_ACCEPT,

        /**
         * A user's collaboration role was changed. This is an enterprise-only event.
         */
        COLLABORATION_ROLE_CHANGE,

        /**
         * The expiration time for a collaboration was extended. This is an enterprise-only event.
         */
        UPDATE_COLLABORATION_EXPIRATION,

        /**
         * A collaboration was removed from a folder. This is an enterprise-only event.
         */
        COLLABORATION_REMOVE,

        /**
         * A user was invited to collaborate on a folder. This is an enterprise-only event.
         */
        COLLABORATION_INVITE,

        /**
         * An expiration time was set for a collaboration. This is an enterprise-only event.
         */
        COLLABORATION_EXPIRATION;
    }
}
