package com.box.sdk;

import java.text.ParseException;
import java.util.Date;

import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;

public abstract class BoxCollaborator extends BoxResource {
    public BoxCollaborator(BoxAPIConnection api, String id) {
        super(api, id);
    }

    public abstract class Info<T extends BoxCollaborator> extends BoxResource.Info<T> {
        private String name;
        private Date createdAt;
        private Date modifiedAt;

        public Info() {
            super();
        }

        public Info(String json) {
            super(json);
        }

        protected Info(JsonObject jsonObject) {
            super(jsonObject);
        }

        public String name() {
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

        @Override
        protected void parseJSONMember(JsonObject.Member member) {
            super.parseJSONMember(member);

            try {
                JsonValue value = member.getValue();
                switch (member.getName()) {
                    case "name":
                        this.name = value.asString();
                        break;
                    case "created_at":
                        this.createdAt = BoxDateParser.parse(value.asString());
                        break;
                    case "modified_at":
                        this.modifiedAt = BoxDateParser.parse(value.asString());
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
