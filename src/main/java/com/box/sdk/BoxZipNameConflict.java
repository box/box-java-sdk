package com.box.sdk;

import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;

/**
 * Represents items that have naming conflicts when creating a zip file.
 */
public class BoxZipNameConflict extends BoxJSONObject {
    private String id;
    private String type;
    private String originalName;
    private String downloadName;

    /**
     * Constructs a BoxZipNameConflict with default settings.
     */
    public BoxZipNameConflict() { }

    /**
     * Constructs a BoxZipNameConflict from a JSON string.
     * @param  json the JSON encoded enterprise.
     */
    public BoxZipNameConflict(String json) {
        super(json);
    }

    BoxZipNameConflict(JsonObject jsonObject) {
        super(jsonObject);
    }

    /**
     * Gets the ID of the item that has the conflict.
     * @return the ID of the item that has the conflict.
     */
    public String getID() {
        return this.id;
    }

    /**
     * Gets the type of the item that has the conflict.
     * @return the type of the item that has the conflict.
     */
    public String getType() {
        return this.type;
    }

    /**
    * Gets the original name of the item that has the conflict.
    * @return the original name of the item that has the conflict.
    */
    public String getOriginalName() {
     return this.originalName;
 }

    /**
    * Gets the new name of the item when it downloads that resolves the conflict.
    * @return the new name of the item when it downloads that resolves the conflict.
    */
    public String getDownloadName() {
     return this.downloadName;
 }

    @Override
    void parseJSONMember(JsonObject.Member member) {
        JsonValue value = member.getValue();
        String memberName = member.getName();
        if (memberName.equals("id")) {
            this.id = value.asString();
        } else if (memberName.equals("type")) {
            this.type = value.asString();
        } else if (memberName.equals("original_name")) {
            this.originalName = value.asString();
        } else if (memberName.equals("download_name")) {
            this.downloadName = value.asString();
        }
    }
}
