package com.box.sdk;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;

public abstract class BoxItem extends BoxResource {
    public BoxItem(BoxAPIConnection api, String id) {
        super(api, id);
    }

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
        private List<String> tags;
        private BoxFolder.Info parent;

        public Info() {
            super();
        }

        public Info(String json) {
            super(json);
        }

        protected Info(JsonObject jsonObject) {
            super(jsonObject);
        }

        public String getEtag() {
            return this.etag;
        }

        public String getName() {
            return this.name;
        }

        public void setName(String name) {
            this.name = name;
            this.addPendingChange("name", name);
        }

        public Date getCreatedAt() {
            return this.createdAt;
        }

        public Date getModifiedAt() {
            return this.modifiedAt;
        }

        public String getDescription() {
            return this.description;
        }

        public void setDescription(String description) {
            this.description = description;
            this.addPendingChange("description", description);
        }

        public long getSize() {
            return this.size;
        }

        public List<BoxFolder> getPathCollection() {
            return this.pathCollection;
        }

        public BoxUser.Info getCreatedBy() {
            return this.createdBy;
        }

        public BoxUser.Info getModifiedBy() {
            return this.modifiedBy;
        }

        public Date getTrashedAt() {
            return this.trashedAt;
        }

        public Date getPurgedAt() {
            return this.purgedAt;
        }

        public Date getContentCreatedAt() {
            return this.createdAt;
        }

        public Date getContentModifiedAt() {
            return this.contentModifiedAt;
        }

        public BoxUser.Info getOwnedBy() {
            return this.ownedBy;
        }

        public String getSequenceID() {
            return this.sequenceID;
        }

        public List<String> getTags() {
            return this.tags;
        }

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
