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

    public class Info {
        private String sequenceID;
        private String etag;
        private String name;
        private Date createdAt;
        private Date modifiedAt;
        private String description;
        private long size;
        private List<BoxFolder> pathCollection;
        private Date trashedAt;
        private Date purgedAt;
        private Date contentCreatedAt;
        private Date contentModifiedAt;

        public Info() { }

        public Info(JsonObject jsonObject) {
            for (JsonObject.Member member : jsonObject) {
                if (member.getValue().isNull()) {
                    continue;
                }

                this.parseJsonMember(member);
            }
        }

        public String getName() {
            return this.name;
        }

        public List<BoxFolder> getPathCollection() {
            return this.pathCollection;
        }

        public String getSequenceID() {
            return this.sequenceID;
        }

        protected void parseJsonMember(JsonObject.Member member) {
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
                        this.size = value.asLong();
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
    }
}
