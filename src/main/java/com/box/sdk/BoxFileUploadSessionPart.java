package com.box.sdk;

import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;

/**
 * Represents a part of the file that is uploaded.
 */
public class BoxFileUploadSessionPart extends BoxJSONObject {

    private String partId;
    private long offset;
    private long size;
    private String sha1;

    /**
     * Constructs an BoxFileUploadSessionPart object using an already parsed JSON object.
     * @param  jsonObject the parsed JSON object.
     */
    BoxFileUploadSessionPart(JsonObject jsonObject) {
        super(jsonObject);
    }

    /**
     * Constructs an empty BoxFileUploadSessionPart object.
     */
    BoxFileUploadSessionPart() {
        super();
    }

    /**
     * Gets the sha1 digest of the part.
     * @return the sh1 digest
     */
    public String getSha1() {
        return this.sha1;
    }

    /**
     * Sets the sh1 digest of the part.
     * @param sha1 the sh1 digest of the part
     */
    public void setSha1(String sha1) {
        this.sha1 = sha1;
    }

    /**
     * Gets the part id.
     * @return the id of the part.
     */
    public String getPartId() {
        return this.partId;
    }

    /**
     * Gets the offset byte.
     * @return the offset of the part.
     */
    public long getOffset() {
        return this.offset;
    }

    /**
     * Gets the size of the part.
     * @return the size of the part.
     */
    public long getSize() {
        return this.size;
    }

    /**
     * Sets the part id.
     * @param partId the id of the part.
     */
    public void setPartId(String partId) {
        this.partId = partId;
    }

    /**
     * Sets the offset.
     * @param offset the offset byte of the part.
     */
    public void setOffset(long offset) {
        this.offset = offset;
    }

    /**
     * Sets the size of the part.
     * @param size the size of the part.
     */
    public void setSize(long size) {
        this.size = size;
    }

    @Override
    protected void parseJSONMember(JsonObject.Member member) {
        String memberName = member.getName();
        JsonValue value = member.getValue();
        if (memberName.equals("part_id")) {
            this.partId = value.asString();
        } else if (memberName.equals("offset")) {
            this.offset = Double.valueOf(value.toString()).longValue();
        } else if (memberName.equals("size")) {
            this.size = Double.valueOf(value.toString()).longValue();
        } else if (memberName.equals("sha1")) {
            this.sha1 = value.asString();
        }
    }
}
