package com.box.sdk;

import java.text.ParseException;
import java.util.Date;

import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;

public abstract class BoxItem extends BoxResource {
    public BoxItem(OAuthSession session, String id) {
        super(session, id);
    }

    public class Info {
        private String sequenceID;
        private String etag;
        private String name;
        private Date createdAt;
        private Date modifiedAt;
        private String description;
        private long size;
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
                    default:
                        break;
                }
            } catch (ParseException e) {
                assert false : "A ParseException indicates a bug in the SDK.";
            }
        }
    }
}
