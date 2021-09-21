package com.box.sdk;

import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;

/**
 * Represents the classification information for a File or Folder on Box.
 */
public class BoxClassification extends BoxJSONObject {
    private String color;
    private String definition;
    private String name;

    /**
     * Constructs an BoxClassification object using an already parsed JSON object.
     *
     * @param jsonObject the parsed JSON object.
     */
    BoxClassification(JsonObject jsonObject) {
        super(jsonObject);
    }

    /**
     * Gets the color that is used to display the classification label in a user-interface.
     *
     * @return the color of this classification.
     */
    public String getColor() {
        return this.color;
    }

    /**
     * Gets the meaning of this classification.
     *
     * @return the meaning of this classification.
     */
    public String getDefinition() {
        return this.definition;
    }

    /**
     * Gets the name of this classification.
     *
     * @return the name of this classification.
     */
    public String getName() {
        return this.name;
    }

    @Override
    protected void parseJSONMember(JsonObject.Member member) {
        super.parseJSONMember(member);

        String memberName = member.getName();
        JsonValue value = member.getValue();

        try {
            if (memberName.equals("color")) {
                this.color = value.asString();
            } else if (memberName.equals("definition")) {
                this.definition = value.asString();
            } else if (memberName.equals("name")) {
                this.name = value.asString();
            }
        } catch (Exception e) {
            throw new BoxDeserializationException(memberName, value.toString(), e);
        }
    }
}
