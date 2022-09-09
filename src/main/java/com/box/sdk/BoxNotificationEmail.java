package com.box.sdk;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;

/**
 * Represents a notification email object
 */
public class BoxNotificationEmail extends BoxJSONObject {
    private boolean isConfirmed;
    private String email;


    /**
     * Constructs a BoxNotificationEmail object.
     *
     * @param email The email address to send the notifications to.
     * @param isConfirmed Specifies if this email address has been confirmed.
     */
    public BoxNotificationEmail(String email, boolean isConfirmed) {
        this(new JsonObject()
            .add("email", email)
            .add("is_confirmed", isConfirmed)
        );
    }

    /**
     * Constructs a BoxNotificationEmail from a JSON string.
     *
     * @param json the json encoded email alias.
     */
    public BoxNotificationEmail(String json) {
        this(Json.parse(json).asObject());
    }

    /**
     * Constructs a BoxNotificationEmail using an already parsed JSON object.
     *
     * @param jsonObject the parsed JSON object.
     */
    BoxNotificationEmail(JsonObject jsonObject) {
        super(jsonObject);
        this.email = jsonObject.getString("email", null);
        this.isConfirmed = jsonObject.getBoolean("is_confirmed", false);
    }

    /**
     * Gets if this email address has been confirmed.
     *
     * @return true if this email address has been confirmed; otherwise false.
     */
    public boolean getIsConfirmed() {
        return this.isConfirmed;
    }

    /**
     * Gets the email address to send notifications to.
     *
     * @return The email address to send the notifications to.
     */
    public String getEmail() {
        return this.email;
    }

    @Override
    void parseJSONMember(JsonObject.Member member) {
        JsonValue value = member.getValue();
        String memberName = member.getName();
        try {
            if (memberName.equals("is_confirmed")) {
                this.isConfirmed = value.asBoolean();
            } else if (memberName.equals("email")) {
                this.email = value.asString();
            }
        } catch (Exception e) {
            throw new BoxDeserializationException(memberName, value.toString(), e);
        }
    }
}
