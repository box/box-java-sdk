package com.box.sdk;

import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;

public class BoxUploadEmail extends BoxJSONObject {
    private Access access;
    private String email;

    public BoxUploadEmail() { }

    public BoxUploadEmail(String json) {
        super(json);
    }

    BoxUploadEmail(JsonObject jsonObject) {
        super(jsonObject);
    }

    public Access getAccess() {
        return this.access;
    }

    public void setAccess(Access access) {
        this.access = access;
        this.addPendingChange("access", access.toJSONValue());
    }

    public String getEmail() {
        return this.email;
    }

    void parseJSONMember(JsonObject.Member member) {
        JsonValue value = member.getValue();
        switch (member.getName()) {
            case "access":
                this.access = Access.fromJSONValue(value.asString());
                break;
            case "email":
                this.email = value.asString();
                break;
            default:
                break;
        }
    }

    public enum Access {
        OPEN("open"),
        COLLABORATORS("collaborators");

        private final String jsonValue;

        private Access(String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public static Access fromJSONValue(String jsonValue) {
            return Access.valueOf(jsonValue.toUpperCase());
        }

        public String toJSONValue() {
            return this.jsonValue;
        }
    }
}
