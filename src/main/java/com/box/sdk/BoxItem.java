package com.box.sdk;

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
     * Constructs a BoxItem for an item with a given ID.
     * @param  api the API connection to be used by the item.
     * @param  id  the ID of the item.
     */
    public BoxItem(BoxAPIConnection api, String id) {
        super(api, id);
    }

    /**
     * Contains additional information about a BoxItem.
     *
     * @param <T> the type of the item associated with this info.
     */
    public abstract class Info<T extends BoxItem> extends BoxResource.Info<T> {
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
        private SharedLink sharedLink;
        private List<String> tags;
        private BoxFolder.Info parent;

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
        protected Info(JsonObject jsonObject) {
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
            return this.createdAt;
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
        public SharedLink getSharedLink() {
            return this.sharedLink;
        }

        /**
         * Sets a shared link for the item.
         * @param sharedLink the shared link for the item.
         */
        public void setSharedLink(SharedLink sharedLink) {
            this.sharedLink = sharedLink;
            this.addPendingChange("shared_link", sharedLink);
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

        @Override
        protected void parseJSONMember(JsonObject.Member member) {
            super.parseJSONMember(member);

            try {
                JsonValue value = member.getValue();
                switch (member.getName()) {
                    case "sequence_id":
                        this.sequenceID = value.asString();
                        break;
                    case "etag":
                        this.etag = value.asString();
                        break;
                    case "name":
                        this.name = value.asString();
                        break;
                    case "created_at":
                        this.createdAt = BoxDateParser.parse(value.asString());
                        break;
                    case "modified_at":
                        this.modifiedAt = BoxDateParser.parse(value.asString());
                        break;
                    case "description":
                        this.description = value.asString();
                        break;
                    case "size":
                        this.size = Double.valueOf(value.toString()).longValue();
                        break;
                    case "trashed_at":
                        this.trashedAt = BoxDateParser.parse(value.asString());
                        break;
                    case "purged_at":
                        this.purgedAt = BoxDateParser.parse(value.asString());
                        break;
                    case "content_created_at":
                        this.contentCreatedAt = BoxDateParser.parse(value.asString());
                        break;
                    case "content_modified_at":
                        this.contentModifiedAt = BoxDateParser.parse(value.asString());
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
                        this.sharedLink = new SharedLink(value.asObject());
                        break;
                    case "tags":
                        this.tags = this.parseTags(value.asArray());
                        break;
                    case "parent":
                        JsonObject jsonObject = value.asObject();
                        String id = jsonObject.get("id").asString();
                        BoxFolder parentFolder = new BoxFolder(getAPI(), id);
                        this.parent = parentFolder.new Info(jsonObject);
                        break;
                    default:
                        break;
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
