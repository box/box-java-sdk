package com.box.sdk;

import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;

/**
 * Represents an enterprise organization on Box.
 */
public class BoxEnterprise extends BoxJSONObject {
    private String id;
    private String name;

    /**
     * Constructs a BoxEnterprise with default settings.
     */
    public BoxEnterprise() {
    }

    /**
     * Constructs a BoxEnterprise from a JSON string.
     *
     * @param json the JSON encoded enterprise.
     */
    public BoxEnterprise(String json) {
        super(json);
    }

    BoxEnterprise(JsonObject jsonObject) {
        super(jsonObject);
    }

    /**
     * Gets the ID of this enterprise.
     *
     * @return the ID of this enterprise.
     */
    public String getID() {
        return this.id;
    }

    /**
     * Gets the name of this enterprise.
     *
     * @return the name of this enterprise.
     */
    public String getName() {
        return this.name;
    }

    @Override
    void parseJSONMember(JsonObject.Member member) {
        JsonValue value = member.getValue();
        String memberName = member.getName();
        if (memberName.equals("id")) {
            this.id = value.asString();
        } else if (memberName.equals("name")) {
            this.name = value.asString();
        }
    }
}
