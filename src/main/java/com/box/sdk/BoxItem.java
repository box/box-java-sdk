package com.box.sdk;

import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;

/**
 * The abstract base class for items in a user's file tree (files, folders, etc.).
 */
public abstract class BoxItem extends BoxResource {
    /**
     * An array of all possible file fields that can be requested when calling {@link #getInfo()}.
     */
    public static final String[] ALL_FIELDS = {"type", "id", "sequence_id", "etag", "sha1", "name", "description",
        "size", "path_collection", "created_at", "modified_at", "trashed_at", "purged_at", "content_created_at",
        "content_modified_at", "created_by", "modified_by", "owned_by", "shared_link", "parent", "item_status",
        "version_number", "comment_count", "permissions", "tags", "lock", "extension", "is_package",
        "folder_upload_email", "item_collection", "sync_state", "has_collaborations", "can_non_owners_invite",
        "file_version"};

    private static final URLTemplate SHARED_ITEM_URL_TEMPLATE = new URLTemplate("shared_items");

    /**
     * Constructs a BoxItem for an item with a given ID.
     * @param  api the API connection to be used by the item.
     * @param  id  the ID of the item.
     */
    public BoxItem(BoxAPIConnection api, String id) {
        super(api, id);
    }

    /**
     * Gets an item that was shared with a shared link.
     * @param  api        the API connection to be used by the shared item.
     * @param  sharedLink the shared link to the item.
     * @return            info about the shared item.
     */
    public static BoxItem.Info getSharedItem(BoxAPIConnection api, String sharedLink) {
        return getSharedItem(api, sharedLink, null);
    }

    /**
     * Gets an item that was shared with a password-protected shared link.
     * @param  api        the API connection to be used by the shared item.
     * @param  sharedLink the shared link to the item.
     * @param  password   the password for the shared link.
     * @return            info about the shared item.
     */
    public static BoxItem.Info getSharedItem(BoxAPIConnection api, String sharedLink, String password) {
        BoxAPIConnection newAPI = new SharedLinkAPIConnection(api, sharedLink, password);
        URL url = SHARED_ITEM_URL_TEMPLATE.build(newAPI.getBaseURL());
        BoxAPIRequest request = new BoxAPIRequest(newAPI, url, "GET");
        BoxJSONResponse response = (BoxJSONResponse) request.send();
        JsonObject json = JsonObject.readFrom(response.getJSON());
        return (BoxItem.Info) BoxResource.parseInfo(newAPI, json);
    }

    /**
     * Copies this item to another folder.
     * @param  destination the destination folder.
     * @return             info about the copied item.
     */
    public abstract BoxItem.Info copy(BoxFolder destination);

    /**
     * Copies this item to another folder and gives it a new name. If the destination is the same folder as the item's
     * current parent, then newName must be a new, unique name.
     * @param  destination the destination folder.
     * @param  newName     a new name for the copied item.
     * @return             info about the copied item.
     */
    public abstract BoxItem.Info copy(BoxFolder destination, String newName);

    /**
     * Moves this item to another folder.
     * @param  destination the destination folder.
     * @return             info about the moved item.
     */
    public abstract BoxItem.Info move(BoxFolder destination);

    /**
     * Moves this item to another folder and gives it a new name.
     * @param  destination the destination folder.
     * @param  newName     a new name for the moved item.
     * @return             info about the moved item.
     */
    public abstract BoxItem.Info move(BoxFolder destination, String newName);

    /**
     * Creates a new shared link for this item.
     *
     * <p>This method is a convenience method for manually creating a new shared link and applying it to this item with
     * {@link Info#setSharedLink}. You may want to create the shared link manually so that it can be updated along with
     * other changes to the item's info in a single network request, giving a boost to performance.</p>
     *
     * @param  access      the access level of the shared link.
     * @param  unshareDate the date and time at which the link will expire. Can be null to create a non-expiring link.
     * @param  permissions the permissions of the shared link. Can be null to use the default permissions.
     * @return             the created shared link.
     */
    public abstract BoxSharedLink createSharedLink(BoxSharedLink.Access access, Date unshareDate,
        BoxSharedLink.Permissions permissions);

    /**
     * Gets information about this item.
     * @return info about this item.
     */
    public abstract BoxItem.Info getInfo();

    /**
     * Gets information about this item that's limited to a list of specified fields.
     * @param  fields the fields to retrieve.
     * @return        info about this item containing only the specified fields.
     */
    public abstract BoxItem.Info getInfo(String... fields);

    /**
     * Contains information about a BoxItem.
     */
    public abstract class Info extends BoxResource.Info {
        private String sequenceID;
        private String etag;
        private String name;
        private Date createdAt;
        private Date modifiedAt;
        private String description;
        private long size;
        private List<BoxFolder> pathCollection;
        private BoxUser.Info createdBy;
        private BoxUser.Info modifiedBy;
        private Date trashedAt;
        private Date purgedAt;
        private Date contentCreatedAt;
        private Date contentModifiedAt;
        private BoxUser.Info ownedBy;
        private BoxSharedLink sharedLink;
        private List<String> tags;
        private BoxFolder.Info parent;
        private String itemStatus;

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

        /**
         * Constructs an Info object using an already parsed JSON object.
         * @param  jsonObject the parsed JSON object.
         */
        Info(JsonObject jsonObject) {
            super(jsonObject);
        }

        /**
         * Gets a unique string identifying the version of the item.
         * @return a unique string identifying the version of the item.
         */
        public String getEtag() {
            return this.etag;
        }

        /**
         * Gets the name of the item.
         * @return the name of the item.
         */
        public String getName() {
            return this.name;
        }

        /**
         * Sets the name of the item.
         * @param name the new name of the item.
         */
        public void setName(String name) {
            this.name = name;
            this.addPendingChange("name", name);
        }

        /**
         * Gets the time the item was created.
         * @return the time the item was created.
         */
        public Date getCreatedAt() {
            return this.createdAt;
        }

        /**
         * Gets the time the item was last modified.
         * @return the time the item was last modified.
         */
        public Date getModifiedAt() {
            return this.modifiedAt;
        }

        /**
         * Gets the description of the item.
         * @return the description of the item.
         */
        public String getDescription() {
            return this.description;
        }

        /**
         * Sets the description of the item.
         * @param description the new description of the item.
         */
        public void setDescription(String description) {
            this.description = description;
            this.addPendingChange("description", description);
        }

        /**
         * Gets the size of the item in bytes.
         * @return the size of the item in bytes.
         */
        public long getSize() {
            return this.size;
        }

        /**
         * Gets the path of folders to the item, starting at the root.
         * @return the path of folders to the item.
         */
        public List<BoxFolder> getPathCollection() {
            return this.pathCollection;
        }

        /**
         * Gets info about the user who created the item.
         * @return info about the user who created the item.
         */
        public BoxUser.Info getCreatedBy() {
            return this.createdBy;
        }

        /**
         * Gets info about the user who last modified the item.
         * @return info about the user who last modified the item.
         */
        public BoxUser.Info getModifiedBy() {
            return this.modifiedBy;
        }

        /**
         * Gets the time that the item was trashed.
         * @return the time that the item was trashed.
         */
        public Date getTrashedAt() {
            return this.trashedAt;
        }

        /**
         * Gets the time that the item was purged from the trash.
         * @return the time that the item was purged from the trash.
         */
        public Date getPurgedAt() {
            return this.purgedAt;
        }

        /**
         * Gets the time that the item was created according to the uploader.
         * @return the time that the item was created according to the uploader.
         */
        public Date getContentCreatedAt() {
            return this.contentCreatedAt;
        }

        /**
         * Gets the time that the item was last modified according to the uploader.
         * @return the time that the item was last modified according to the uploader.
         */
        public Date getContentModifiedAt() {
            return this.contentModifiedAt;
        }

        /**
         * Gets info about the user who owns the item.
         * @return info about the user who owns the item.
         */
        public BoxUser.Info getOwnedBy() {
            return this.ownedBy;
        }

        /**
         * Gets the shared link for the item.
         * @return the shared link for the item.
         */
        public BoxSharedLink getSharedLink() {
            return this.sharedLink;
        }

        /**
         * Sets a shared link for the item.
         * @param sharedLink the shared link for the item.
         */
        public void setSharedLink(BoxSharedLink sharedLink) {
            if (this.sharedLink == sharedLink) {
                return;
            }

            this.removeChildObject("shared_link");
            this.sharedLink = sharedLink;
            this.addChildObject("shared_link", sharedLink);
        }

        /**
         * Gets a unique ID for use with the {@link EventStream}.
         * @return a unique ID for use with the EventStream.
         */
        public String getSequenceID() {
            return this.sequenceID;
        }

        /**
         * Gets a list of all the tags applied to the item.
         *
         * <p>Note that this field isn't populated by default and must be specified as a field parameter when getting
         * Info about the item.</p>
         *
         * @return a list of all the tags applied to the item.
         */
        public List<String> getTags() {
            return this.tags;
        }

        /**
         * Gets info about the parent folder of the item.
         * @return info abou thte parent folder of the item.
         */
        public BoxFolder.Info getParent() {
            return this.parent;
        }

        /**
         * Gets the status of the item.
         * @return the status of the item.
         */
        public String getItemStatus() {
            return this.itemStatus;
        }

        @Override
        protected void parseJSONMember(JsonObject.Member member) {
            super.parseJSONMember(member);

            try {
                JsonValue value = member.getValue();
                String memberName = member.getName();
                if (memberName.equals("sequence_id")) {
                    this.sequenceID = value.asString();
                } else if (memberName.equals("etag")) {
                    this.etag = value.asString();
                } else if (memberName.equals("name")) {
                    this.name = value.asString();
                } else if (memberName.equals("created_at")) {
                    this.createdAt = BoxDateFormat.parse(value.asString());
                } else if (memberName.equals("modified_at")) {
                    this.modifiedAt = BoxDateFormat.parse(value.asString());
                } else if (memberName.equals("description")) {
                    this.description = value.asString();
                } else if (memberName.equals("size")) {
                    this.size = Double.valueOf(value.toString()).longValue();
                } else if (memberName.equals("trashed_at")) {
                    this.trashedAt = BoxDateFormat.parse(value.asString());
                } else if (memberName.equals("purged_at")) {
                    this.purgedAt = BoxDateFormat.parse(value.asString());
                } else if (memberName.equals("content_created_at")) {
                    this.contentCreatedAt = BoxDateFormat.parse(value.asString());
                } else if (memberName.equals("content_modified_at")) {
                    this.contentModifiedAt = BoxDateFormat.parse(value.asString());
                } else if (memberName.equals("path_collection")) {
                    this.pathCollection = this.parsePathCollection(value.asObject());
                } else if (memberName.equals("created_by")) {
                    this.createdBy = this.parseUserInfo(value.asObject());
                } else if (memberName.equals("modified_by")) {
                    this.modifiedBy = this.parseUserInfo(value.asObject());
                } else if (memberName.equals("owned_by")) {
                    this.ownedBy = this.parseUserInfo(value.asObject());
                } else if (memberName.equals("shared_link")) {
                    if (this.sharedLink == null) {
                        this.setSharedLink(new BoxSharedLink(value.asObject()));
                    } else {
                        this.sharedLink.update(value.asObject());
                    }
                } else if (memberName.equals("tags")) {
                    this.tags = this.parseTags(value.asArray());
                } else if (memberName.equals("parent")) {
                    JsonObject jsonObject = value.asObject();
                    if (this.parent == null) {
                        String id = jsonObject.get("id").asString();
                        BoxFolder parentFolder = new BoxFolder(getAPI(), id);
                        this.parent = parentFolder.new Info(jsonObject);
                    } else {
                        this.parent.update(jsonObject);
                    }
                } else if (memberName.equals("item_status")) {
                    this.itemStatus = value.asString();
                }
            } catch (ParseException e) {
                assert false : "A ParseException indicates a bug in the SDK.";
            }
        }

        private List<BoxFolder> parsePathCollection(JsonObject jsonObject) {
            int count = jsonObject.get("total_count").asInt();
            List<BoxFolder> pathCollection = new ArrayList<BoxFolder>(count);
            JsonArray entries = jsonObject.get("entries").asArray();
            for (JsonValue value : entries) {
                JsonObject entry = value.asObject();
                String id = entry.get("id").asString();
                pathCollection.add(new BoxFolder(getAPI(), id));
            }

            return pathCollection;
        }

        private BoxUser.Info parseUserInfo(JsonObject jsonObject) {
            String userID = jsonObject.get("id").asString();
            BoxUser user = new BoxUser(getAPI(), userID);
            return user.new Info(jsonObject);
        }

        private List<String> parseTags(JsonArray jsonArray) {
            List<String> tags = new ArrayList<String>(jsonArray.size());
            for (JsonValue value : jsonArray) {
                tags.add(value.asString());
            }

            return tags;
        }
    }
}
