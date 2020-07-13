package com.box.sdk;

import com.eclipsesource.json.JsonObject;

/**
 * Represents a Box item to be included when creating a zip file.
 */
public class BoxZipItem {
    private String type;
    private String id;

    /**
     * Constructs a base BoxZipItem object.
     *
     * @param type item type, "file" or "folder".
     * @param id   id of the the item.
     */
    public BoxZipItem(String type, String id) {
        super();
        this.type = type;
        this.id = id;
    }

    /**
     * Gets the item type.
     *
     * @return the type of the zip item.
     */
    public String getType() {
        return this.type;
    }

    /**
     * Gets the item ID.
     *
     * @return the ID fo the zip item.
     */
    public String getID() {
        return this.id;
    }

    /**
     * Gets a JSON object reprsenting this class.
     *
     * @return the JSON object reprsenting this class.
     */
    public JsonObject getJSONObject() {
        JsonObject jsonObj = new JsonObject()
                    .add("type", this.type)
                    .add("id", this.id);
        return jsonObj;
    }

}
