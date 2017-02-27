package com.box.sdk;

import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;

/**
 *
 */
public class BoxFileUploadSessionPart extends BoxJSONObject {

    private String partId;
    private long offset;
    private long size;

    /**
     * Constructs an empty BoxFileUploadSessionPart object.
     */
    public BoxFileUploadSessionPart() {
        super();
    }

    /**
     * Constructs an BoxFileUploadSessionPart object by parsing information from a JSON string.
     * @param  json the JSON string to parse.
     */
    public BoxFileUploadSessionPart(String json) {
        super(json);
    }

    /**
     * Constructs an BoxFileUploadSessionPart object using an already parsed JSON object.
     * @param  jsonObject the parsed JSON object.
     */
    BoxFileUploadSessionPart(JsonObject jsonObject) {
        super(jsonObject);
    }

    public String getPartId() {
        return this.partId;
    }

    public long getOffset() {
        return this.offset;
    }

    public long getSize() {
        return this.size;
    }

    public void setPartId(String partId) {
        this.partId = partId;
    }

    public void setOffset(long offset) {
        this.offset = offset;
    }

    public void setSize(long size) {
        this.size = size;
    }

    protected void parseJSONMember(JsonObject.Member member) {
        String memberName = member.getName();
        JsonValue value = member.getValue();
        if (memberName.equals("part_id")) {
            this.partId = value.asString();
        } else if (memberName.equals("offset")) {
            this.offset = Double.valueOf(value.toString()).longValue();
        } else if (memberName.equals("size")) {
            this.size = Double.valueOf(value.toString()).longValue();
        }
    }
}
