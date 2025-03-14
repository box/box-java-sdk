package com.box.sdk;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * The abstract base class for items in a user's file tree (files, folders, etc.).
 */
public abstract class BoxItem extends BoxResource {
    /**
     * An array of all possible file fields that can be requested when calling {@link #getInfo(String...)}.
     */
    public static final String[] ALL_FIELDS = {"type", "id", "sequence_id", "etag", "sha1", "name", "description",
        "size", "path_collection", "created_at", "modified_at", "trashed_at", "purged_at", "content_created_at",
        "content_modified_at", "created_by", "modified_by", "owned_by", "shared_link", "parent", "item_status",
        "version_number", "comment_count", "permissions", "tags", "lock", "extension", "is_package",
        "folder_upload_email", "item_collection", "sync_state", "has_collaborations", "can_non_owners_invite",
        "file_version", "collections", "expires_at"};
    /**
     * Shared Item URL Template.
     */
    public static final URLTemplate SHARED_ITEM_URL_TEMPLATE = new URLTemplate("shared_items");

    /**
     * Url template for operations with watermarks.
     */
    public static final URLTemplate WATERMARK_URL_TEMPLATE = new URLTemplate("/watermark");

    /**
     * Constructs a BoxItem for an item with a given ID.
     *
     * @param api the API connection to be used by the item.
     * @param id  the ID of the item.
     */
    public BoxItem(BoxAPIConnection api, String id) {
        super(api, id);
    }

    /**
     * Gets an item that was shared with a shared link.
     *
     * @param api        the API connection to be used by the shared item.
     * @param sharedLink the shared link to the item.
     * @return info about the shared item.
     */
    public static BoxItem.Info getSharedItem(BoxAPIConnection api, String sharedLink) {
        return getSharedItem(api, sharedLink, null);
    }

    /**
     * Gets an item that was shared with a password-protected shared link.
     *
     * @param api        the API connection to be used by the shared item.
     * @param sharedLink the shared link to the item.
     * @param password   the password for the shared link. Use `null` if shared link has no password.
     * @param fields     the fields to retrieve.
     * @return info about the shared item.
     */
    public static BoxItem.Info getSharedItem(
            BoxAPIConnection api, String sharedLink, String password, String... fields
    ) {
        QueryStringBuilder builder = new QueryStringBuilder();
        if (fields.length > 0) {
            builder.appendParam("fields", fields);
        }
        URL url = SHARED_ITEM_URL_TEMPLATE.buildWithQuery(api.getBaseURL(), builder.toString());
        BoxJSONRequest request = new BoxJSONRequest(api, url, "GET");

        request.addHeader("BoxApi", BoxSharedLink.getSharedLinkHeaderValue(sharedLink, password));

        try (BoxJSONResponse response = request.send()) {
            JsonObject json = Json.parse(response.getJSON()).asObject();
            return (BoxItem.Info) BoxResource.parseInfo(api, json);
        }
    }

    /**
     * @return URL for the current object, constructed as base URL pus an item specifier.
     */
    protected URL getItemURL() {
        return new URLTemplate("").build(this.getAPI().getBaseURL());
    }

    /**
     * Used to retrieve the watermark for the item.
     * If the item does not have a watermark applied to it, a 404 Not Found will be returned by API.
     *
     * @param itemUrl url template for the item.
     * @param fields  the fields to retrieve.
     * @return the watermark associated with the item.
     */
    protected BoxWatermark getWatermark(URLTemplate itemUrl, String... fields) {
        URL watermarkUrl = itemUrl.build(this.getAPI().getBaseURL(), this.getID());
        QueryStringBuilder builder = new QueryStringBuilder();
        if (fields.length > 0) {
            builder.appendParam("fields", fields);
        }
        URL url = WATERMARK_URL_TEMPLATE.buildWithQuery(watermarkUrl.toString(), builder.toString());
        BoxJSONRequest request = new BoxJSONRequest(this.getAPI(), url, "GET");
        try (BoxJSONResponse response = request.send()) {
            return new BoxWatermark(response.getJSON());
        }
    }

    /**
     * Used to apply or update the watermark for the item.
     *
     * @param itemUrl url template for the item.
     * @param imprint the value must be "default", as custom watermarks is not yet supported.
     * @return the watermark associated with the item.
     */
    protected BoxWatermark applyWatermark(URLTemplate itemUrl, String imprint) {
        URL watermarkUrl = itemUrl.build(this.getAPI().getBaseURL(), this.getID());
        URL url = WATERMARK_URL_TEMPLATE.build(watermarkUrl.toString());
        BoxJSONRequest request = new BoxJSONRequest(this.getAPI(), url, "PUT");
        JsonObject body = new JsonObject()
            .add(BoxWatermark.WATERMARK_JSON_KEY, new JsonObject()
                .add(BoxWatermark.WATERMARK_IMPRINT_JSON_KEY, imprint));
        request.setBody(body.toString());
        try (BoxJSONResponse response = request.send()) {
            return new BoxWatermark(response.getJSON());
        }
    }

    /**
     * Removes a watermark from the item.
     * If the item did not have a watermark applied to it, a 404 Not Found will be returned by API.
     *
     * @param itemUrl url template for the item.
     */
    protected void removeWatermark(URLTemplate itemUrl) {
        URL watermarkUrl = itemUrl.build(this.getAPI().getBaseURL(), this.getID());
        URL url = WATERMARK_URL_TEMPLATE.build(watermarkUrl.toString());
        BoxAPIRequest request = new BoxAPIRequest(this.getAPI(), url, "DELETE");
        request.send().close();
    }

    /**
     * Copies this item to another folder.
     *
     * @param destination the destination folder.
     * @return info about the copied item.
     */
    public abstract BoxItem.Info copy(BoxFolder destination);

    /**
     * Copies this item to another folder and gives it a new name. If the destination is the same folder as the item's
     * current parent, then newName must be a new, unique name.
     *
     * @param destination the destination folder.
     * @param newName     a new name for the copied item.
     * @return info about the copied item.
     */
    public abstract BoxItem.Info copy(BoxFolder destination, String newName);

    /**
     * Moves this item to another folder.
     *
     * @param destination the destination folder.
     * @return info about the moved item.
     */
    public abstract BoxItem.Info move(BoxFolder destination);

    /**
     * Moves this item to another folder and gives it a new name.
     *
     * @param destination the destination folder.
     * @param newName     a new name for the moved item.
     * @return info about the moved item.
     */
    public abstract BoxItem.Info move(BoxFolder destination, String newName);

    /**
     * Gets information about this item that's limited to a list of specified fields.
     *
     * @param fields the fields to retrieve.
     * @return info about this item containing only the specified fields.
     */
    public abstract BoxItem.Info getInfo(String... fields);

    /**
     * Sets the collections that this item belongs to.
     *
     * @param collections the collections that this item should belong to.
     * @return info about the item, including the collections it belongs to.
     */
    public abstract BoxItem.Info setCollections(BoxCollection... collections);

    /**
     * Contains information about a BoxItem.
     */
    public abstract class Info extends BoxResource.Info {
        private String type;
        private String sequenceID;
        private String etag;
        private String name;
        private Date createdAt;
        private Date modifiedAt;
        private String description;
        private long size;
        private List<BoxFolder.Info> pathCollection;
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
        private Date expiresAt;
        private Set<BoxCollection.Info> collections;
        private String downloadUrl;

        /**
         * Constructs an empty Info object.
         */
        public Info() {
            super();
        }

        /**
         * Constructs an Info object by parsing information from a JSON string.
         *
         * @param json the JSON string to parse.
         */
        public Info(String json) {
            super(json);
        }

        /**
         * Constructs an Info object using an already parsed JSON object.
         *
         * @param jsonObject the parsed JSON object.
         */
        Info(JsonObject jsonObject) {
            super(jsonObject);
        }

        /**
         * Gets the item type.
         *
         * @return the item's type.
         */
        public String getType() {
            return this.type;
        }

        /**
         * Gets a unique string identifying the version of the item.
         *
         * @return a unique string identifying the version of the item.
         */
        public String getEtag() {
            return this.etag;
        }

        /**
         * Gets the name of the item.
         *
         * @return the name of the item.
         */
        public String getName() {
            return this.name;
        }

        /**
         * Sets the name of the item.
         *
         * @param name the new name of the item.
         */
        public void setName(String name) {
            this.name = name;
            this.addPendingChange("name", name);
        }

        /**
         * Gets the time the item was created.
         *
         * @return the time the item was created.
         */
        public Date getCreatedAt() {
            return this.createdAt;
        }

        /**
         * Gets the time the item was last modified.
         *
         * @return the time the item was last modified.
         */
        public Date getModifiedAt() {
            return this.modifiedAt;
        }

        /**
         * Gets the description of the item.
         *
         * @return the description of the item.
         */
        public String getDescription() {
            return this.description;
        }

        /**
         * Sets the description of the item.
         *
         * @param description the new description of the item.
         */
        public void setDescription(String description) {
            this.description = description;
            this.addPendingChange("description", description);
        }

        /**
         * Gets the size of the item in bytes.
         *
         * @return the size of the item in bytes.
         */
        public long getSize() {
            return this.size;
        }

        /**
         * Gets the path of folders to the item, starting at the root.
         *
         * @return the path of folders to the item.
         */
        public List<BoxFolder.Info> getPathCollection() {
            return this.pathCollection;
        }

        /**
         * Gets info about the user who created the item.
         *
         * @return info about the user who created the item.
         */
        public BoxUser.Info getCreatedBy() {
            return this.createdBy;
        }

        /**
         * Gets info about the user who last modified the item.
         *
         * @return info about the user who last modified the item.
         */
        public BoxUser.Info getModifiedBy() {
            return this.modifiedBy;
        }

        /**
         * Gets the time that the item was trashed.
         *
         * @return the time that the item was trashed.
         */
        public Date getTrashedAt() {
            return this.trashedAt;
        }

        /**
         * Gets the time that the item was purged from the trash.
         *
         * @return the time that the item was purged from the trash.
         */
        public Date getPurgedAt() {
            return this.purgedAt;
        }

        /**
         * Gets the time that the item was created according to the uploader.
         *
         * @return the time that the item was created according to the uploader.
         */
        public Date getContentCreatedAt() {
            return this.contentCreatedAt;
        }

        /**
         * Gets the time that the item was last modified according to the uploader.
         *
         * @return the time that the item was last modified according to the uploader.
         */
        public Date getContentModifiedAt() {
            return this.contentModifiedAt;
        }

        /**
         * Gets the expires at time for this item.
         *
         * @return the time that the item will expire at.
         */
        public Date getExpiresAt() {
            return this.expiresAt;
        }

        /**
         * Gets info about the user who owns the item.
         *
         * @return info about the user who owns the item.
         */
        public BoxUser.Info getOwnedBy() {
            return this.ownedBy;
        }

        /**
         * Gets the shared link for the item.
         *
         * @return the shared link for the item.
         */
        public BoxSharedLink getSharedLink() {
            return this.sharedLink;
        }

        /**
         * Sets a shared link for the item.
         *
         * @param sharedLink the shared link for the item.
         */
        public void setSharedLink(BoxSharedLink sharedLink) {
            this.removeChildObject("shared_link");
            this.sharedLink = sharedLink;
            this.addChildObject("shared_link", sharedLink);
        }

        /**
         * Removes the shared link for the item.
         */
        public void removeSharedLink() {
            this.addChildObject("shared_link", null);
        }

        /**
         * Gets a unique ID for use with the {@link EventStream}.
         *
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
         * Sets the tags for an item.
         *
         * @param tags The new tags for the item.
         */
        public void setTags(List<String> tags) {
            this.tags = tags;
            JsonArray tagsJSON = new JsonArray();
            for (String tag : tags) {
                tagsJSON.add(tag);
            }
            this.addPendingChange("tags", tagsJSON);
        }

        /**
         * Gets info about the parent folder of the item.
         *
         * @return info about the parent folder of the item.
         */
        public BoxFolder.Info getParent() {
            return this.parent;
        }

        /**
         * Gets the status of the item.
         *
         * @return the status of the item.
         */
        public String getItemStatus() {
            return this.itemStatus;
        }

        /**
         * Gets info about the collections that this item belongs to.
         *
         * @return info about the collections that this item belongs to.
         */
        public Iterable<BoxCollection.Info> getCollections() {
            return this.collections;
        }

        /***
         * Gets URL that can be used to download the file.
         * @return
         */
        public String getDownloadUrl() {
            return this.downloadUrl;
        }

        /**
         * Sets the collections that this item belongs to.
         *
         * @param collections the new list of collections that this item should belong to.
         */
        public void setCollections(Iterable<BoxCollection> collections) {
            if (this.collections == null) {
                this.collections = new HashSet<>();
            } else {
                this.collections.clear();
            }

            JsonArray jsonArray = new JsonArray();
            for (BoxCollection collection : collections) {
                JsonObject jsonObject = new JsonObject();
                jsonObject.add("id", collection.getID());
                jsonArray.add(jsonObject);
                this.collections.add(collection.new Info());
            }
            this.addPendingChange("collections", jsonArray);
        }

        @Override
        protected void parseJSONMember(JsonObject.Member member) {
            super.parseJSONMember(member);
            JsonValue value = member.getValue();
            String memberName = member.getName();

            try {
                switch (memberName) {
                    case "sequence_id":
                        this.sequenceID = value.asString();
                        break;
                    case "type":
                        this.type = value.asString();
                        break;
                    case "etag":
                        this.etag = value.asString();
                        break;
                    case "name":
                        this.name = value.asString();
                        break;
                    case "created_at":
                        this.createdAt = BoxDateFormat.parse(value.asString());
                        break;
                    case "modified_at":
                        this.modifiedAt = BoxDateFormat.parse(value.asString());
                        break;
                    case "description":
                        this.description = value.asString();
                        break;
                    case "size":
                        this.size = Double.valueOf(value.toString()).longValue();
                        break;
                    case "trashed_at":
                        this.trashedAt = BoxDateFormat.parse(value.asString());
                        break;
                    case "purged_at":
                        this.purgedAt = BoxDateFormat.parse(value.asString());
                        break;
                    case "content_created_at":
                        this.contentCreatedAt = BoxDateFormat.parse(value.asString());
                        break;
                    case "content_modified_at":
                        this.contentModifiedAt = BoxDateFormat.parse(value.asString());
                        break;
                    case "expires_at":
                        this.expiresAt = BoxDateFormat.parse(value.asString());
                        break;
                    case "path_collection":
                        this.pathCollection = this.parsePathCollection(value.asObject());
                        break;
                    case "created_by":
                        this.createdBy = this.parseUserInfo(value.asObject());
                        break;
                    case "modified_by":
                        this.modifiedBy = this.parseUserInfo(value.asObject());
                        break;
                    case "owned_by":
                        this.ownedBy = this.parseUserInfo(value.asObject());
                        break;
                    case "shared_link":
                        if (this.sharedLink == null) {
                            this.setSharedLink(new BoxSharedLink(value.asObject()));
                        } else {
                            this.sharedLink.update(value.asObject());
                        }
                        break;
                    case "tags":
                        this.tags = this.parseTags(value.asArray());
                        break;
                    case "parent":
                        JsonObject parentObject = value.asObject();
                        if (this.parent == null) {
                            String id = parentObject.get("id").asString();
                            BoxFolder parentFolder = new BoxFolder(getAPI(), id);
                            this.parent = parentFolder.new Info(parentObject);
                        } else {
                            this.parent.update(parentObject);
                        }
                        break;
                    case "item_status":
                        this.itemStatus = value.asString();
                        break;
                    case "collections":
                        if (this.collections == null) {
                            this.collections = new HashSet<>();
                        } else {
                            this.collections.clear();
                        }

                        BoxAPIConnection api = getAPI();
                        JsonArray jsonArray = value.asArray();
                        for (JsonValue arrayValue : jsonArray) {
                            JsonObject jsonObject = arrayValue.asObject();
                            String id = jsonObject.get("id").asString();
                            BoxCollection collection = new BoxCollection(api, id);
                            BoxCollection.Info collectionInfo = collection.new Info(jsonObject);
                            this.collections.add(collectionInfo);
                        }
                        break;
                    case "download_url":
                        this.downloadUrl = value.asString();
                        break;
                    default:
                        break;
                }
            } catch (Exception e) {
                throw new BoxDeserializationException(memberName, value.toString(), e);
            }
        }

        private List<BoxFolder.Info> parsePathCollection(JsonObject jsonObject) {
            int count = jsonObject.get("total_count").asInt();
            List<BoxFolder.Info> pathCollection = new ArrayList<>(count);
            JsonArray entries = jsonObject.get("entries").asArray();
            for (JsonValue value : entries) {
                JsonObject entry = value.asObject();
                String id = entry.get("id").asString();
                BoxFolder folder = new BoxFolder(getAPI(), id);
                pathCollection.add(folder.new Info(entry));
            }

            return pathCollection;
        }

        private BoxUser.Info parseUserInfo(JsonObject jsonObject) {
            String userID = jsonObject.get("id").asString();
            BoxUser user = new BoxUser(getAPI(), userID);
            return user.new Info(jsonObject);
        }

        private List<String> parseTags(JsonArray jsonArray) {
            List<String> tags = new ArrayList<>(jsonArray.size());
            for (JsonValue value : jsonArray) {
                tags.add(value.asString());
            }

            return tags;
        }
    }
}
