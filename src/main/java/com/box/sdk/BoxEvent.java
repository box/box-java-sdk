package com.box.sdk;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents an event that was fired off by the Box events API.
 */
@BoxResourceType("event")
public class BoxEvent extends BoxResource {
    private BoxResource.Info sourceInfo;
    private BoxEvent.EventType eventType;
    private String typeName;
    private JsonObject sourceJSON;
    private Date createdAt;
    private String ipAddress;
    private JsonObject additionalDetails;
    private BoxCollaborator.Info accessibleBy;
    private BoxUser.Info createdBy;
    private String sessionID;
    private BoxUser.Info actionBy;

    /**
     * Constructs a BoxEvent from a JSON string.
     *
     * @param api  the API connection to be used by the file.
     * @param json the JSON encoded event.
     */
    public BoxEvent(BoxAPIConnection api, String json) {
        this(api, Json.parse(json).asObject());
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
     *
     * @return the type of this event.
     */
    public BoxEvent.EventType getEventType() {
        return this.eventType;
    }

    /**
     * Gets the type name as String. Every BoxEvent will have typeName, some will have
     * eventType set to specific value and some will have eventType = UNKNOWN.
     *
     * @return the name of the type of this event.
     */
    public String getTypeName() {
        return this.typeName;
    }

    /**
     * Gets the time that this event was created.
     *
     * @return the time that this event was created.
     */
    public Date getCreatedAt() {
        return this.createdAt;
    }

    /**
     * Gets the IP address of the user that triggered this event.
     *
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
     *
     * @return info about the user that triggered this event.
     */
    public BoxUser.Info getCreatedBy() {
        return this.createdBy;
    }

    /**
     * Gets the session ID of the user that triggered this event.
     *
     * @return the session ID of the user that triggered this event.
     */
    public String getSessionID() {
        return this.sessionID;
    }

    /**
     * Gets the user that performed the action for this event.
     *
     * @return info about the user that performed that action for this event.
     */
    public BoxUser.Info getActionBy() {
        return this.actionBy;
    }

    void parseJsonMember(JsonObject.Member member) {
        JsonValue value = member.getValue();
        if (value.isNull()) {
            return;
        }

        String memberName = member.getName();
        switch (memberName) {
            case "source":
                // Parsing the source might fail due to a bug in the enterprise event stream where the API returns
                // JSON that doesn't correctly map to a BoxResource.Info. If this happens, we set the sourceInfo to null
                // and expect the caller to use the getSourceJSON() method instead.
                try {
                    this.sourceInfo = BoxResource.parseInfo(this.getAPI(), value.asObject());
                } catch (Exception e) {
                    this.sourceInfo = null;
                }
                this.sourceJSON = JsonObject.unmodifiableObject(value.asObject());
                break;
            case "event_type":
                String stringValue = value.asString();
                this.typeName = stringValue;
                this.eventType = EventType.lookupByValue(stringValue);
                if (this.eventType == null) {
                    this.eventType = EventType.UNKNOWN;
                }
                break;
            case "created_at":
                try {
                    this.createdAt = BoxDateFormat.parse(value.asString());
                } catch (ParseException e) {
                    assert false : "A ParseException indicates a bug in the SDK.";
                }
                break;
            case "ip_address":
                this.ipAddress = value.asString();
                break;
            case "additional_details":
                this.additionalDetails = value.asObject();
                break;
            case "accessible_by":
                this.accessibleBy = (BoxCollaborator.Info) BoxResource.parseInfo(this.getAPI(), value.asObject());
                break;
            case "created_by":
                this.createdBy = (BoxUser.Info) BoxResource.parseInfo(this.getAPI(), value.asObject());
                break;
            case "session_id":
                this.sessionID = value.asString();
                break;
            case "action_by":
                this.actionBy = (BoxUser.Info) BoxResource.parseInfo(this.getAPI(), value.asObject());
                break;
            default:
                break;
        }
    }

    /**
     * Enumerates the possible types for an event.
     */
    public enum EventType {
        /**
         * The type of the event is unknown.
         */
        UNKNOWN("UNKNOWN"),

        /**
         * An file or folder was created.
         */
        ITEM_CREATE("ITEM_CREATE"),

        /**
         * An file or folder was uploaded.
         */
        ITEM_UPLOAD("ITEM_UPLOAD"),

        /**
         * A comment was created on a folder, file, or other comment.
         */
        COMMENT_CREATE("COMMENT_CREATE"),

        /**
         * A comment was deleted on a folder, file, or other comment.
         */
        COMMENT_DELETE("COMMENT_DELETE"),

        /**
         * An file or folder was downloaded.
         */
        ITEM_DOWNLOAD("ITEM_DOWNLOAD"),

        /**
         * A file was previewed.
         */
        ITEM_PREVIEW("ITEM_PREVIEW"),

        /**
         * A file or folder was moved.
         */
        ITEM_MOVE("ITEM_MOVE"),

        /**
         * A file or folder was copied.
         */
        ITEM_COPY("ITEM_COPY"),

        /**
         * A task was assigned.
         */
        TASK_ASSIGNMENT_CREATE("TASK_ASSIGNMENT_CREATE"),

        /**
         * A task was assignment was completed.
         */
        TASK_ASSIGNMENT_COMPLETE("TASK_ASSIGNMENT_COMPLETE"),

        /**
         * A task was assignment was updated.
         */
        TASK_ASSIGNMENT_UPDATE("TASK_ASSIGNMENT_UPDATE"),

        /**
         * A task was created.
         */
        TASK_CREATE("TASK_CREATE"),

        /**
         * A file was locked.
         */
        LOCK_CREATE("LOCK_CREATE"),

        /**
         * A file was unlocked.
         */
        LOCK_DESTROY("LOCK_DESTROY"),

        /**
         * A file or folder was deleted.
         */
        ITEM_TRASH("ITEM_TRASH"),

        /**
         * A file or folder was recovered from the trash.
         */
        ITEM_UNDELETE_VIA_TRASH("ITEM_UNDELETE_VIA_TRASH"),

        /**
         * A collaborator was added to a folder.
         */
        COLLAB_ADD_COLLABORATOR("COLLAB_ADD_COLLABORATOR"),

        /**
         * A collaborator's role was change in a folder.
         */
        COLLAB_ROLE_CHANGE("COLLAB_ROLE_CHANGE"),

        /**
         * A collaborator was invited to a folder.
         */
        COLLAB_INVITE_COLLABORATOR("COLLAB_INVITE_COLLABORATOR"),

        /**
         * A collaborator was removed from a folder.
         */
        COLLAB_REMOVE_COLLABORATOR("COLLAB_REMOVE_COLLABORATOR"),

        /**
         * A folder was marked for sync.
         */
        ITEM_SYNC("ITEM_SYNC"),

        /**
         * A folder was un-marked for sync.
         */
        ITEM_UNSYNC("ITEM_UNSYNC"),

        /**
         * A file or folder was renamed.
         */
        ITEM_RENAME("ITEM_RENAME"),

        /**
         * A file or folder was enabled for sharing.
         */
        ITEM_SHARED_CREATE("ITEM_SHARED_CREATE"),

        /**
         * A file or folder was disabled for sharing.
         */
        ITEM_SHARED_UNSHARE("ITEM_SHARED_UNSHARE"),

        /**
         * A folder was shared.
         */
        ITEM_SHARED("ITEM_SHARED"),

        /**
         * A previous version of a file was promoted to the current version.
         */
        ITEM_MAKE_CURRENT_VERSION("ITEM_MAKE_CURRENT_VERSION"),

        /**
         * A tag was added to a file or folder.
         */
        TAG_ITEM_CREATE("TAG_ITEM_CREATE"),

        /**
         * 2 factor authentication enabled by user.
         */
        ENABLE_TWO_FACTOR_AUTH("ENABLE_TWO_FACTOR_AUTH"),

        /**
         * Free user accepts invitation to become a managed user.
         */
        ADMIN_INVITE_ACCEPT("MASTER_INVITE_ACCEPT"),

        /**
         * Free user rejects invitation to become a managed user.
         */
        ADMIN_INVITE_REJECT("MASTER_INVITE_REJECT"),

        /**
         * Granted Box access to account.
         */
        ACCESS_GRANTED("ACCESS_GRANTED"),

        /**
         * Revoke Box access to account.
         */
        ACCESS_REVOKED("ACCESS_REVOKED"),

        /**
         * A user logged in from a new device.
         */
        ADD_LOGIN_ACTIVITY_DEVICE("ADD_LOGIN_ACTIVITY_DEVICE"),

        /**
         * A user session associated with an app was invalidated.
         */
        REMOVE_LOGIN_ACTIVITY_DEVICE("REMOVE_LOGIN_ACTIVITY_DEVICE"),

        /**
         * An admin role changed for a user.
         */
        CHANGE_ADMIN_ROLE("CHANGE_ADMIN_ROLE"),

        /**
         * A user was added to a group. This is an enterprise-only event.
         */
        GROUP_ADD_USER("GROUP_ADD_USER"),

        /**
         * A user was created. This is an enterprise-only event.
         */
        NEW_USER("NEW_USER"),

        /**
         * A group was created. This is an enterprise-only event.
         */
        GROUP_CREATION("GROUP_CREATION"),

        /**
         * A group was deleted. This is an enterprise-only event.
         */
        GROUP_DELETION("GROUP_DELETION"),

        /**
         * A user was deleted. This is an enterprise-only event.
         */
        DELETE_USER("DELETE_USER"),

        /**
         * A group was edited. This is an enterprise-only event.
         */
        GROUP_EDITED("GROUP_EDITED"),

        /**
         * A user was edited. This is an enterprise-only event.
         */
        EDIT_USER("EDIT_USER"),

        /**
         * A group was granted access to a folder. This is an enterprise-only event.
         */
        GROUP_ADD_FOLDER("GROUP_ADD_FOLDER"),

        /**
         * A group was granted access to a file. This is an enterprise-only event.
         */
        GROUP_ADD_FILE("GROUP_ADD_FILE"),

        /**
         * A user was removed from a group. This is an enterprise-only event.
         */
        GROUP_REMOVE_USER("GROUP_REMOVE_USER"),

        /**
         * A group had its access to a folder removed. This is an enterprise-only event.
         */
        GROUP_REMOVE_FOLDER("GROUP_REMOVE_FOLDER"),

        /**
         * A group had its access to a file removed. This is an enterprise-only event.
         */
        GROUP_REMOVE_FILE("GROUP_REMOVE_FILE"),

        /**
         * An administrator logged in. This is an enterprise-only event.
         */
        ADMIN_LOGIN("ADMIN_LOGIN"),

        /**
         * A device was associated with a user. This is an enterprise-only event.
         */
        ADD_DEVICE_ASSOCIATION("ADD_DEVICE_ASSOCIATION"),

        /**
         * There was a failed login attempt. This is an enterprise-only event.
         */
        FAILED_LOGIN("FAILED_LOGIN"),

        /**
         * There was a successful login. This is an enterprise-only event.
         */
        LOGIN("LOGIN"),

        /**
         * A user's OAuth2 access token was refreshed. This is an enterprise-only event.
         */
        USER_AUTHENTICATE_OAUTH2_TOKEN_REFRESH("USER_AUTHENTICATE_OAUTH2_TOKEN_REFRESH"),

        /**
         * A device was disassociated with a user. This is an enterprise-only event.
         */
        REMOVE_DEVICE_ASSOCIATION("REMOVE_DEVICE_ASSOCIATION"),

        /**
         * A user agreed to the terms of service. This is an enterprise-only event.
         */
        TERMS_OF_SERVICE_AGREE("TERMS_OF_SERVICE_AGREE"),

        /**
         * A user rejected the terms of service. This is an enterprise-only event.
         */
        TERMS_OF_SERVICE_REJECT("TERMS_OF_SERVICE_REJECT"),

        /**
         * Virus found on a file. Event is only received by enterprises that have opted in to be notified.
         * This is an enterprise-only event.
         */
        FILE_MARKED_MALICIOUS("FILE_MARKED_MALICIOUS"),

        /**
         * An item was copied. This is an enterprise-only event.
         */
        COPY("COPY"),

        /**
         * An item was deleted. This is an enterprise-only event.
         */
        DELETE("DELETE"),

        /**
         * An item was downloaded. This is an enterprise-only event.
         */
        DOWNLOAD("DOWNLOAD"),

        /**
         * An item was edited. This is an enterprise-only event.
         */
        EDIT("EDIT"),

        /**
         * An item was locked. This is an enterprise-only event.
         */
        LOCK("LOCK"),

        /**
         * An item was moved. This is an enterprise-only event.
         */
        MOVE("MOVE"),

        /**
         * An item was previewed. This is an enterprise-only event.
         */
        PREVIEW("PREVIEW"),

        /**
         * An item was renamed. This is an enterprise-only event.
         */
        RENAME("RENAME"),

        /**
         * An item was set to be auto-deleted. This is an enterprise-only event.
         */
        STORAGE_EXPIRATION("STORAGE_EXPIRATION"),

        /**
         * An item was undeleted. This is an enterprise-only event.
         */
        UNDELETE("UNDELETE"),

        /**
         * An item was unlocked. This is an enterprise-only event.
         */
        UNLOCK("UNLOCK"),

        /**
         * An item was uploaded. This is an enterprise-only event.
         */
        UPLOAD("UPLOAD"),

        /**
         * An shared link was created for an item. This is an enterprise-only event.
         */
        SHARE("SHARE"),

        /**
         * The shared link for an item was updated. This is an enterprise-only event.
         */
        ITEM_SHARED_UPDATE("ITEM_SHARED_UPDATE"),

        /**
         * The expiration time for a shared link was extended. This is an enterprise-only event.
         */
        UPDATE_SHARE_EXPIRATION("UPDATE_SHARE_EXPIRATION"),

        /**
         * The expiration time was set for a shared link. This is an enterprise-only event.
         */
        SHARE_EXPIRATION("SHARE_EXPIRATION"),

        /**
         * The shared link for an item was REMOVE_DEVICE_ASSOCIATION. This is an enterprise-only event.
         */
        UNSHARE("UNSHARE"),

        /**
         * A user accepted a collaboration invite. This is an enterprise-only event.
         */
        COLLABORATION_ACCEPT("COLLABORATION_ACCEPT"),

        /**
         * A user's collaboration role was changed. This is an enterprise-only event.
         */
        COLLABORATION_ROLE_CHANGE("COLLABORATION_ROLE_CHANGE"),

        /**
         * The expiration time for a collaboration was extended. This is an enterprise-only event.
         */
        UPDATE_COLLABORATION_EXPIRATION("UPDATE_COLLABORATION_EXPIRATION"),

        /**
         * A collaboration was removed from a folder. This is an enterprise-only event.
         */
        COLLABORATION_REMOVE("COLLABORATION_REMOVE"),

        /**
         * A user was invited to collaborate on a folder. This is an enterprise-only event.
         */
        COLLABORATION_INVITE("COLLABORATION_INVITE"),

        /**
         * An expiration time was set for a collaboration. This is an enterprise-only event.
         */
        COLLABORATION_EXPIRATION("COLLABORATION_EXPIRATION"),

        /**
         * Creation of metadata instance. This is an enterprise-only event.
         */
        METADATA_INSTANCE_CREATE("METADATA_INSTANCE_CREATE"),

        /**
         * Update of metadata instance. This is an enterprise-only event.
         */
        METADATA_INSTANCE_UPDATE("METADATA_INSTANCE_UPDATE"),

        /**
         * Deletion of metadata instance. This is an enterprise-only event.
         */
        METADATA_INSTANCE_DELETE("METADATA_INSTANCE_DELETE"),

        /**
         * Content Workflow upload policy violation. This is an enterprise-only event.
         */
        CONTENT_WORKFLOW_UPLOAD_POLICY_VIOLATION("CONTENT_WORKFLOW_UPLOAD_POLICY_VIOLATION"),

        /**
         * Edit the permissions on a folder.  This is an enterprise-only-event.
         */
        CHANGE_FOLDER_PERMISSION("CHANGE_FOLDER_PERMISSION"),

        /**
         * A task assignment is deleted.  This is an enterprise-only event.
         */
        TASK_ASSIGNMENT_DELETE("TASK_ASSIGNMENT_DELETE"),

        /**
         * Retention is removed.  This is an enterprise-only event.
         */
        DATA_RETENTION_REMOVE_RETENTION("DATA_RETENTION_REMOVE_RETENTION"),

        /**
         * Retention is created.  This is an enterprise-only event.
         */
        DATA_RETENTION_CREATE_RETENTION("DATA_RETENTION_CREATE_RETENTION"),

        /**
         * A retention policy assignment is added.  This is an enterprise-only event.
         */
        RETENTION_POLICY_ASSIGNMENT_ADD("RETENTION_POLICY_ASSIGNMENT_ADD"),

        /**
         * A legal hold assignment is created.  This is an enterprise-only event.
         */
        LEGAL_HOLD_ASSIGNMENT_CREATE("LEGAL_HOLD_ASSIGNMENT_CREATE"),

        /**
         * A legal hold assignment is deleted.  This is an enterprise-only event.
         */
        LEGAL_HOLD_ASSIGNMENT_DELETE("LEGAL_HOLD_ASSIGNMENT_DELETE"),

        /**
         * A legal hold policy is deleted.  This is an enterprise-only event.
         */
        LEGAL_HOLD_POLICY_DELETE("LEGAL_HOLD_POLICY_DELETE"),

        /**
         * There is a sharing policy violation.  This is an enterprise-only event.
         */
        CONTENT_WORKFLOW_SHARING_POLICY_VIOLATION("CONTENT_WORKFLOW_SHARING_POLICY_VIOLATION"),

        /**
         * An application public key is added.  This is an enterprise-only event.
         */
        APPLICATION_PUBLIC_KEY_ADDED("APPLICATION_PUBLIC_KEY_ADDED"),

        /**
         * An application public key is deleted.  This is an enterprise-only event.
         */
        APPLICATION_PUBLIC_KEY_DELETED("APPLICATION_PUBLIC_KEY_DELETED"),

        /**
         * A content policy is added.  This is an enterprise-only event.
         */
        CONTENT_WORKFLOW_POLICY_ADD("CONTENT_WORKFLOW_POLICY_ADD"),

        /**
         * An automation is added.  This is an enterprise-only event.
         */
        CONTENT_WORKFLOW_AUTOMATION_ADD("CONTENT_WORKFLOW_AUTOMATION_ADD"),

        /**
         * An automation is deleted.  This is an enterprise-only event.
         */
        CONTENT_WORKFLOW_AUTOMATION_DELETE("CONTENT_WORKFLOW_AUTOMATION_DELETE"),

        /**
         * A user email alias is confirmed.  This is an enterprise-only event.
         */
        EMAIL_ALIAS_CONFIRM("EMAIL_ALIAS_CONFIRM"),

        /**
         * A user email alias is removed.  This is an enterprise-only event.
         */
        EMAIL_ALIAS_REMOVE("EMAIL_ALIAS_REMOVE"),

        /**
         * A watermark is added to a file.  This is an enterprise-only event.
         */
        WATERMARK_LABEL_CREATE("WATERMARK_LABEL_CREATE"),

        /**
         * A watermark is removed from a file.  This is an enterprise-only event.
         */
        WATERMARK_LABEL_DELETE("WATERMARK_LABEL_DELETE"),

        /**
         * Creation of metadata template instance.  This is an enterprise-only event.
         */
        METADATA_TEMPLATE_CREATE("METADATA_TEMPLATE_CREATE"),

        /**
         * Update of metadata template instance.  This is an enterprise-only event.
         */
        METADATA_TEMPLATE_UPDATE("METADATA_TEMPLATE_UPDATE"),

        /**
         * Deletion of metadata template instance.  This is an enterprise-only event.
         */
        METADATA_TEMPLATE_DELETE("METADATA_TEMPLATE_DELETE"),

        /**
         * Item was opened.  This is an enterprise-only event.
         */
        ITEM_OPEN("ITEM_OPEN"),

        /**
         * Item was modified.  This is an enterprise-only event.
         */
        ITEM_MODIFY("ITEM_MODIFY"),

        /**
         * When a policy set in the Admin console is triggered.  This is an enterprise-only event,
         */
        CONTENT_WORKFLOW_ABNORMAL_DOWNLOAD_ACTIVITY("CONTENT_WORKFLOW_ABNORMAL_DOWNLOAD_ACTIVITY"),

        /**
         * Folders were removed from a group in the Admin console.  This is an enterprise-only event.
         */
        GROUP_REMOVE_ITEM("GROUP_REMOVE_ITEM"),

        /**
         * Folders were added to a group in the Admin console.  This is an enterprise-only event.
         */
        GROUP_ADD_ITEM("GROUP_ADD_ITEM"),

        /**
         * An OAuth2 access token was created for a user.  This is an enterprise-only event.
         */
        USER_AUTHENTICATE_OAUTH2_ACCESS_TOKEN_CREATE("USER_AUTHENTICATE_OAUTH2_ACCESS_TOKEN_CREATE"),

        /**
         * Event for file tag updates.
         */
        CONTENT_ACCESS("CONTENT_ACCESS"),

        /**
         * A Shield justification is approved.
         */
        SHIELD_JUSTIFICATION_APPROVAL("SHIELD_JUSTIFICATION_APPROVAL"),

        /**
         * A task's comment is edited.
         */
        TASK_UPDATE("TASK_UPDATE"),

        /**
         * A file is retored to previous version.
         */
        FILE_VERSION_RESTORE("FILE_VERSION_RESTORE"),

        /**
         * Advanced settings of a folder are updated.
         */
        ADVANCED_FOLDER_SETTINGS_UPDATE("ADVANCED_FOLDER_SETTINGS_UPDATE"),

        /**
         * A new application is created in the Box Developer Console.
         */
        APPLICATION_CREATED("APPLICATION_CREATED"),

        /**
         * Device Trust check failed.
         */
        DEVICE_TRUST_CHECK_FAILED("DEVICE_TRUST_CHECK_FAILED"),

        /**
         * When a JWT application has been authorized or reauthorized.
         */
        ENTERPRISE_APP_AUTHORIZATION_UPDATE("ENTERPRISE_APP_AUTHORIZATION_UPDATE"),

        /**
         * A watermarked file is downloaded.
         */
        FILE_WATERMARKED_DOWNLOAD("FILE_WATERMARKED_DOWNLOAD"),

        /**
         * A legal hold policy is created.
         */
        LEGAL_HOLD_POLICY_CREATE("LEGAL_HOLD_POLICY_CREATE"),

        /**
         * A legal hold policy is updated.
         */
        LEGAL_HOLD_POLICY_UPDATE("LEGAL_HOLD_POLICY_UPDATE"),

        /**
         * Shield detected an anomalous download, session, location, or malicious content based on
         * enterprise Shield rules. See shield alert events for more information.
         */
        SHIELD_ALERT("SHIELD_ALERT"),

        /**
         * Access to an external collaboration is blocked.
         */
        SHIELD_EXTERNAL_COLLAB_ACCESS_BLOCKED("SHIELD_EXTERNAL_COLLAB_ACCESS_BLOCKED"),

        /**
         * Access to an external collaboration is blocked due to missing a justification.
         */
        SHIELD_EXTERNAL_COLLAB_ACCESS_BLOCKED_MISSING_JUSTIFICATION(
                "SHIELD_EXTERNAL_COLLAB_ACCESS_BLOCKED_MISSING_JUSTIFICATION"),

        /**
         * An invite to externally collaborate is blocked.
         */
        SHIELD_EXTERNAL_COLLAB_INVITE_BLOCKED("SHIELD_EXTERNAL_COLLAB_INVITE_BLOCKED"),

        /**
         * An invite to externally collaborate is blocked due to missing a justification.
         */
        SHIELD_EXTERNAL_COLLAB_INVITE_BLOCKED_MISSING_JUSTIFICATION(
                "SHIELD_EXTERNAL_COLLAB_INVITE_BLOCKED_MISSING_JUSTIFICATION"),

        /**
         * A sign request was sent to a signer.
         */
        SIGN_DOCUMENT_ASSIGNED("SIGN_DOCUMENT_ASSIGNED"),

        /**
         * A sign request was cancelled via API or UI.
         */
        SIGN_DOCUMENT_CANCELLED("SIGN_DOCUMENT_CANCELLED"),

        /**
         * A sign request was signed by all signers.
         */
        SIGN_DOCUMENT_COMPLETED("SIGN_DOCUMENT_COMPLETED"),

        /**
         * A sign request was converted to a .pdf for signing.
         */
        SIGN_DOCUMENT_CONVERTED("SIGN_DOCUMENT_CONVERTED"),

        /**
         * A sign request was created via API or UI. The document is not yet sent to signers.
         */
        SIGN_DOCUMENT_CREATED("SIGN_DOCUMENT_CREATED"),

        /**
         * A sign request was declined by a signer.
         */
        SIGN_DOCUMENT_DECLINED("SIGN_DOCUMENT_DECLINED"),

        /**
         * A sign request expired with incomplete signatures.
         */
        SIGN_DOCUMENT_EXPIRED("SIGN_DOCUMENT_EXPIRED"),

        /**
         * A sign request was signed by a signer.
         */
        SIGN_DOCUMENT_SIGNED("SIGN_DOCUMENT_SIGNED"),

        /**
         * A signer clicked on Review Document in the signer email or visited the signing URL.
         */
        SIGN_DOCUMENT_VIEWED_BY_SIGNER("SIGN_DOCUMENT_VIEWED_BY_SIGNER"),

        /**
         * A signer downloaded the signing document.
         */
        SIGNER_DOWNLOADED("SIGNER_DOWNLOADED"),

        /**
         * A signer forwarded the signing document.
         */
        SIGNER_FORWARDED("SIGNER_FORWARDED"),

        /**
         * Accepted terms.
         */
        TERMS_OF_SERVICE_ACCEPT("TERMS_OF_SERVICE_ACCEPT");

        /**
         * Static map of all EventTypes.
         */
        private static final Map<String, BoxEvent.EventType> EVENT_TYPE_MAP = new HashMap<>(EventType.values().length);

        /*
          EVENT_TYPE_MAP initialization.
         */
        static {
            for (BoxEvent.EventType event : BoxEvent.EventType.values()) {
                EVENT_TYPE_MAP.put(event.jsonValue, event);
            }
        }

        /**
         * String representation of the eventType.
         */
        private final String jsonValue;

        /**
         * Constructor.
         *
         * @param jsonValue string representation of the eventType.
         */
        EventType(String jsonValue) {
            this.jsonValue = jsonValue;
        }

        /**
         * Custom implementation of valueOf().
         *
         * @param jsonValue of the EventType.
         * @return EventType.
         */
        static BoxEvent.EventType lookupByValue(String jsonValue) {
            return EVENT_TYPE_MAP.get(jsonValue);
        }

        /**
         * @return string representation of the eventType.
         */
        String toJSONString() {
            return this.jsonValue;
        }
    }
}
