package com.box.sdk;

import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;

/**
 * Represents a link to preview a file on Box.
 */
public class BoxExpiringEmbedLink extends BoxJSONObject {
    private String url;

    /**
     * Constructs a BoxExpiringEmbedLink with default settings.
     */
    public BoxExpiringEmbedLink() {
    }

    /**
     * Constructs a BoxExpiringEmbedLink from a JSON string.
     *
     * @param json the JSON encoded shared link.
     */
    public BoxExpiringEmbedLink(String json) {
        super(json);
    }

    BoxExpiringEmbedLink(JsonObject jsonObject) {
        super(jsonObject);
    }

    /**
     * Get the URL of this preview link.
     *
     * @return the URL of this preview link.
     */
    public String getURL() {
        return this.url;
    }

    @Override
    void parseJSONMember(JsonObject.Member member) {
        JsonValue value = member.getValue();
        String memberName = member.getName();
        if (memberName.equals("url")) {
            this.url = value.asString();
        }
    }
}
