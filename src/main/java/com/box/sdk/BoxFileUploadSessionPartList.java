package com.box.sdk;

import java.util.ArrayList;
import java.util.List;

import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;

/**
 * Contains the list of parts of a large file that are already uploaded.
 * It also contains a offset to represent the paging.
 */
public class BoxFileUploadSessionPartList extends BoxJSONObject {

    private List<BoxFileUploadSessionPart> entries;
    private int offset;
    private int limit;
    private int totalCount;

    /**
     * Constructs an BoxFileUploadSessionPart object using an already parsed JSON object.
     * @param  jsonObject the parsed JSON object.
     */
    BoxFileUploadSessionPartList(JsonObject jsonObject) {
        super(jsonObject);
    }

    /**
     * Returns the list of parts that are already uploaded.
     * @return the list of parts.
     */
    public List<BoxFileUploadSessionPart> getEntries() {
        return this.entries;
    }

    /**
     * Returns the paging offset for the list of parts.
     * @return the paging offset.
     */
    public int getOffset() {
        return this.offset;
    }

    /**
     * Returns the limit on number of entires in a response.
     * @return the limit
     */
    public int getLimit() {
        return this.limit;
    }

    /**
     * Returns the total count of entries.
     * @return the toal count of entries
     */
    public int getTotalCount() {
        return this.totalCount;
    }

    @Override
    protected void parseJSONMember(JsonObject.Member member) {
        String memberName = member.getName();
        JsonValue value = member.getValue();
        if (memberName.equals("entries")) {
            JsonArray array = (JsonArray) value;

            if (array.size() > 0) {
                this.entries = this.getParts(array);
            }
        } else if (memberName.equals("offset")) {
            this.offset = Double.valueOf(value.toString()).intValue();
        } else if (memberName.equals("limit")) {
            this.limit = Double.valueOf(value.toString()).intValue();
        } else if (memberName.equals("total_count")) {
            this.totalCount = Double.valueOf(value.toString()).intValue();
        }
    }

    /*
     * Creates List of parts from the JSON array
     */
    private List<BoxFileUploadSessionPart> getParts(JsonArray partsArray) {
        List<BoxFileUploadSessionPart> parts = new ArrayList<BoxFileUploadSessionPart>();
        for (JsonValue value: partsArray) {
            BoxFileUploadSessionPart part = new BoxFileUploadSessionPart((JsonObject) value);
            parts.add(part);
        }
        return parts;
    }
}
