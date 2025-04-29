package com.box.sdk;

import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;

/**
 * Represents an email address that can be used to upload files to a folder on Box.
 */
public class BoxUploadEmail extends BoxJSONObject {
    private Access access;
    private String email;

    /**
     * Constructs a BoxUploadEmail with default settings.
     */
    public BoxUploadEmail() {
    }

    /**
     * Constructs a BoxUploadEmail from a JSON string.
     *
     * @param json the JSON encoded upload email.
     */
    public BoxUploadEmail(String json) {
        super(json);
    }

    BoxUploadEmail(JsonObject jsonObject) {
        super(jsonObject);
    }

    /**
     * Gets the access level of this upload email.
     *
     * @return the access level of this upload email.
     */
    public Access getAccess() {
        return this.access;
    }

    /**
     * Sets the access level of this upload email.
     *
     * @param access the new access level of this upload email.
     */
    public void setAccess(Access access) {
        this.access = access;
        this.addPendingChange("access", access.toJSONValue());
    }

    /**
     * Gets the email address of this upload email.
     *
     * @return the email address of this upload email.
     */
    public String getEmail() {
        return this.email;
    }

    @Override
    void parseJSONMember(JsonObject.Member member) {
        JsonValue value = member.getValue();
        String memberName = member.getName();
        try {
            if (memberName.equals("access")) {
                this.access = Access.fromJSONValue(value.asString());
            } else if (memberName.equals("email")) {
                this.email = value.asString();
            }
        } catch (Exception e) {
            throw new BoxDeserializationException(memberName, value.toString(), e);
        }
    }

    /**
     * Enumerates the possible access levels that can be set on an upload email.
     */
    public enum Access {
        /**
         * Anyone can send an upload to this email address.
         */
        OPEN("open"),

        /**
         * Only collaborators can send an upload to this email address.
         */
        COLLABORATORS("collaborators");

        private final String jsonValue;

        Access(String jsonValue) {
            this.jsonValue = jsonValue;
        }

        static Access fromJSONValue(String jsonValue) {
            return Access.valueOf(jsonValue.toUpperCase(java.util.Locale.ROOT));
        }

        String toJSONValue() {
            return this.jsonValue;
        }
    }
}
