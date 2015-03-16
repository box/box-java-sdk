package com.box.sdk;

import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;

/**
 * Represents a Box user's email alias.
 */
public class EmailAlias extends BoxJSONObject {
    private String id;
    private boolean isConfirmed;
    private String email;

    /**
     * Constructs an empty EmailAlias.
     */
    public EmailAlias() { }

    /**
     * Constructs an EmailAlias from a JSON string.
     * @param  json the json encoded email alias.
     */
    public EmailAlias(String json) {
        super(json);
    }

    EmailAlias(JsonObject jsonObject) {
        super(jsonObject);
    }

    /**
     * Gets the ID of this email alias.
     * @return the ID of this email alias.
     */
    public String getID() {
        return this.id;
    }

    /**
     * Gets whether or not the user has confirmed this email alias.
     * @return true if the user has confirmed this email alias; otherwise false.
     */
    public boolean getIsConfirmed() {
        return this.isConfirmed;
    }

    /**
     * Gets the email address of this email alias.
     * @return the email address of this email alias.
     */
    public String getEmail() {
        return this.email;
    }

    @Override
    void parseJSONMember(JsonObject.Member member) {
        JsonValue value = member.getValue();
        String memberName = member.getName();
        if (memberName.equals("id")) {
            this.id = value.asString();
        } else if (memberName.equals("is_confirmed")) {
            this.isConfirmed = value.asBoolean();
        } else if (memberName.equals("email")) {
            this.email = value.asString();
        }
    }
}
