package com.box.sdk;

import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;

/**
 * Represents a collaboration between a user and another user or group. Collaborations are Box's equivalent of access
 * control lists. They can be used to set and apply permissions for users or groups to folders.
 */
public class BoxCollaboration extends BoxResource {
    private static final URLTemplate COLLABORATIONS_URL_TEMPLATE = new URLTemplate("collaborations");
    private static final URLTemplate PENDING_COLLABORATIONS_URL = new URLTemplate("collaborations?status=pending");
    private static final URLTemplate COLLABORATION_URL_TEMPLATE = new URLTemplate("collaborations/%s");

    /**
     * Constructs a BoxCollaboration for a collaboration with a given ID.
     * @param  api the API connection to be used by the collaboration.
     * @param  id  the ID of the collaboration.
     */
    public BoxCollaboration(BoxAPIConnection api, String id) {
        super(api, id);
    }

    /**
     * Gets all pending collaboration invites for the current user.
     * @param  api the API connection to use.
     * @return     a collection of pending collaboration infos.
     */
    public static Collection<Info> getPendingCollaborations(BoxAPIConnection api) {
        URL url = PENDING_COLLABORATIONS_URL.build(api.getBaseURL());

        BoxAPIRequest request = new BoxAPIRequest(api, url, "GET");
        BoxJSONResponse response = (BoxJSONResponse) request.send();
        JsonObject responseJSON = JsonObject.readFrom(response.getJSON());

        int entriesCount = responseJSON.get("total_count").asInt();
        Collection<BoxCollaboration.Info> collaborations = new ArrayList<BoxCollaboration.Info>(entriesCount);
        JsonArray entries = responseJSON.get("entries").asArray();
        for (JsonValue entry : entries) {
            JsonObject entryObject = entry.asObject();
            BoxCollaboration collaboration = new BoxCollaboration(api, entryObject.get("id").asString());
            BoxCollaboration.Info info = collaboration.new Info(entryObject);
            collaborations.add(info);
        }

        return collaborations;
    }

    /**
     * Gets information about this collaboration.
     * @return info about this collaboration.
     */
    public Info getInfo() {
        BoxAPIConnection api = this.getAPI();
        URL url = COLLABORATIONS_URL_TEMPLATE.build(api.getBaseURL());

        BoxAPIRequest request = new BoxAPIRequest(api, url, "GET");
        BoxJSONResponse response = (BoxJSONResponse) request.send();
        JsonObject jsonObject = JsonObject.readFrom(response.getJSON());
        return new Info(jsonObject);
    }

    /**
     * Updates the information about this collaboration with any info fields that have been modified locally.
     * @param info the updated info.
     */
    public void updateInfo(Info info) {
        BoxAPIConnection api = this.getAPI();
        URL url = COLLABORATION_URL_TEMPLATE.build(api.getBaseURL(), this.getID());

        BoxJSONRequest request = new BoxJSONRequest(api, url, "PUT");
        request.setBody(info.getPendingChanges());
        BoxJSONResponse response = (BoxJSONResponse) request.send();
        JsonObject jsonObject = JsonObject.readFrom(response.getJSON());
        info.update(jsonObject);
    }

    /**
     * Deletes this collaboration.
     */
    public void delete() {
        BoxAPIConnection api = this.getAPI();
        URL url = COLLABORATION_URL_TEMPLATE.build(api.getBaseURL(), this.getID());

        BoxAPIRequest request = new BoxAPIRequest(api, url, "DELETE");
        BoxAPIResponse response = request.send();
        response.disconnect();
    }

    /**
     * Contains information about a BoxCollaboration.
     */
    public class Info extends BoxResource.Info {
        private BoxUser.Info createdBy;
        private Date createdAt;
        private Date modifiedAt;
        private Date expiresAt;
        private Status status;
        private BoxCollaborator.Info accessibleBy;
        private Role role;
        private Date acknowledgedAt;
        private BoxFolder.Info item;

        /**
         * Constructs an empty Info object.
         */
        public Info() {
            super();
        }

        /**
         * Constructs an Info object by parsing information from a JSON string.
         * @param  json the JSON string to parse.
         */
        public Info(String json) {
            super(json);
        }

        Info(JsonObject jsonObject) {
            super(jsonObject);
        }

        /**
         * Gets the user who created the collaboration.
         * @return the user who created the collaboration.
         */
        public BoxUser.Info getCreatedBy() {
            return this.createdBy;
        }

        /**
         * Gets the time the collaboration was created.
         * @return the time the collaboration was created.
         */
        public Date getCreatedAt() {
            return this.createdAt;
        }

        /**
         * Gets the time the collaboration was last modified.
         * @return the time the collaboration was last modified.
         */
        public Date getModifiedAt() {
            return this.modifiedAt;
        }

        /**
         * Gets the time the collaboration will expire.
         * @return the time the collaboration will expire.
         */
        public Date getExpiresAt() {
            return this.expiresAt;
        }

        /**
         * Gets the status of the collaboration.
         * @return the status of the collaboration.
         */
        public Status getStatus() {
            return this.status;
        }

        /**
         * Sets the status of the collaboration in order to accept or reject the collaboration if it's pending.
         * @param status the new status of the collaboration.
         */
        public void setStatus(Status status) {
            this.status = status;
            this.addPendingChange("status", status.name().toLowerCase());
        }

        /**
         * Gets the collaborator who this collaboration applies to.
         * @return the collaborator who this collaboration applies to.
         */
        public BoxCollaborator.Info getAccessibleBy() {
            return this.accessibleBy;
        }

        /**
         * Gets the level of access the collaborator has.
         * @return the level of access the collaborator has.
         */
        public Role getRole() {
            return this.role;
        }

        /**
         * Sets the level of access the collaborator has.
         * @param role the new level of access to give the collaborator.
         */
        public void setRole(Role role) {
            this.role = role;
            this.addPendingChange("role", role.toJSONString());
        }

        /**
         * Gets the time the collaboration's status was changed.
         * @return the time the collaboration's status was changed.
         */
        public Date getAcknowledgedAt() {
            return this.acknowledgedAt;
        }

        /**
         * Gets the folder the collaboration is related to.
         * @return the folder the collaboration is related to.
         */
        public BoxFolder.Info getItem() {
            return this.item;
        }

        @Override
        public BoxCollaboration getResource() {
            return BoxCollaboration.this;
        }

        @Override
        protected void parseJSONMember(JsonObject.Member member) {
            super.parseJSONMember(member);

            String memberName = member.getName();
            JsonValue value = member.getValue();
            try {
                switch (memberName) {
                    case "created_by":
                        JsonObject userJSON = value.asObject();
                        if (this.createdBy == null) {
                            String userID = userJSON.get("id").asString();
                            BoxUser user = new BoxUser(getAPI(), userID);
                            this.createdBy = user.new Info(userJSON);
                        } else {
                            this.createdBy.update(userJSON);
                        }
                        break;
                    case "created_at":
                        this.createdAt = BoxDateParser.parse(value.asString());
                        break;
                    case "modified_at":
                        this.modifiedAt = BoxDateParser.parse(value.asString());
                        break;
                    case "expires_at":
                        this.expiresAt = BoxDateParser.parse(value.asString());
                        break;
                    case "status":
                        String statusString = value.asString().toUpperCase();
                        this.status = Status.valueOf(statusString);
                        break;
                    case "accessible_by":
                        userJSON = value.asObject();
                        if (this.accessibleBy == null) {
                            String userID = userJSON.get("id").asString();
                            BoxUser user = new BoxUser(getAPI(), userID);
                            BoxUser.Info userInfo = user.new Info(userJSON);
                            this.accessibleBy = userInfo;
                        } else {
                            this.accessibleBy.update(userJSON);
                        }
                        break;
                    case "role":
                        this.role = Role.fromJSONString(value.asString());
                        break;
                    case "acknowledged_at":
                        this.acknowledgedAt = BoxDateParser.parse(value.asString());
                        break;
                    case "item":
                        JsonObject folderJSON = value.asObject();
                        if (this.item == null) {
                            String folderID = folderJSON.get("id").asString();
                            BoxFolder folder = new BoxFolder(getAPI(), folderID);
                            this.item = folder.new Info(folderJSON);
                        } else {
                            this.item.update(folderJSON);
                        }
                        break;
                    default:
                        break;
                }
            } catch (ParseException e) {
                assert false : "A ParseException indicates a bug in the SDK.";
            }
        }
    }

    /**
     * Enumerates the possible statuses that a collaboration can have.
     */
    public enum Status {
        /**
         * The collaboration has been accepted.
         */
        ACCEPTED,

        /**
         * The collaboration is waiting to be accepted or rejected.
         */
        PENDING,

        /**
         * The collaboration has been rejected.
         */
        REJECTED;
    }

    /**
     * Enumerates the possible access levels that a collaborator can have.
     */
    public enum Role {
        /**
         * An Editor has full read/write access to a folder. Once invited to a folder, they will be able to view,
         * download, upload, edit, delete, copy, move, rename, generate shared links, make comments, assign tasks,
         * create tags, and invite/remove collaborators. They will not be able to delete or move root level folders.
         */
        EDITOR ("editor"),

        /**
         * The viewer role has full read access to a folder. Once invited to a folder, they will be able to preview,
         * download, make comments, and generate shared links.  They will not be able to add tags, invite new
         * collaborators, upload, edit, or delete items in the folder.
         */
        VIEWER ("viewer"),

        /**
         * The previewer role has limited read access to a folder. They will only be able to preview the items in the
         * folder using the integrated content viewer. They will not be able to share, upload, edit, or delete any
         * content. This role is only available to enterprise accounts.
         */
        PREVIEWER ("previewer"),

        /**
         * The uploader has limited write access to a folder. They will only be able to upload and see the names of the
         * items in a folder. They will not able to download or view any content. This role is only available to
         * enterprise accounts.
         */
        UPLOADER ("uploader"),

        /**
         * The previewer-uploader role is a combination of previewer and uploader. A user with this access level will be
         * able to preview files using the integrated content viewer as well as upload items into the folder. They will
         * not be able to download, edit, or share, items in the folder. This role is only available to enterprise
         * accounts.
         */
        PREVIEWER_UPLOADER ("previewer uploader"),

        /**
         * The viewer-uploader role is a combination of viewer and uploader. A viewer-uploader has full read access to a
         * folder and limited write access. They are able to preview, download, add comments, generate shared links, and
         * upload content to the folder. They will not be able to add tags, invite new collaborators, edit, or delete
         * items in the folder. This role is only available to enterprise accounts.
         */
        VIEWER_UPLOADER ("viewer uploader"),

        /**
         * The co-owner role has all of the functional read/write access that an editor does. This permission level has
         * the added ability of being able to manage users in the folder. A co-owner can add new collaborators, change
         * access levels of existing collaborators, and remove collaborators. However, they will not be able to
         * manipulate the owner of the folder or transfer ownership to another user. This role is only available to
         * enterprise accounts.
         */
        CO_OWNER ("co-owner"),

        /**
         * The owner role has all of the functional capabilities of a co-owner. However, they will be able to manipulate
         * the owner of the folder or transfer ownership to another user. This role is only available to enterprise
         * accounts.
         */
        OWNER ("owner");

        private final String jsonValue;

        private Role(String jsonValue) {
            this.jsonValue = jsonValue;
        }

        static Role fromJSONString(String jsonValue) {
            switch (jsonValue) {
                case "editor":
                    return EDITOR;
                case "viewer":
                    return VIEWER;
                case "previewer":
                    return PREVIEWER;
                case "uploader":
                    return UPLOADER;
                case "previewer uploader":
                    return PREVIEWER_UPLOADER;
                case "viewer uploader":
                    return VIEWER_UPLOADER;
                case "co-owner":
                    return CO_OWNER;
                case "owner":
                    return OWNER;
                default:
                    throw new IllegalArgumentException("The provided JSON value isn't a valid Role.");
            }
        }

        String toJSONString() {
            return this.jsonValue;
        }
    }
}
