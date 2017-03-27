package com.box.sdk;

import java.util.ArrayList;
import java.util.List;

import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;

/**
 * Contains the list of parts of a large file that are already uploaded.
 * It also contains a marker to represent the paging.
 */
public class BoxFileUploadSessionPartList extends BoxJSONObject {

    private List<BoxFileUploadSessionPart> parts;
    private int marker;

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
    public List<BoxFileUploadSessionPart> getParts() {
        return this.parts;
    }

    /**
     * Returns the paging marker for the list of parts.
     * @return the paging marker.
     */
    public int getMarker() {
        return this.marker;
    }

    @Override
    protected void parseJSONMember(JsonObject.Member member) {
        String memberName = member.getName();
        JsonValue value = member.getValue();
        if (memberName.equals("parts")) {
            JsonArray array = (JsonArray) value;

            if (array.size() > 0) {
                this.parts = this.getParts(array);
            }
        } else if (memberName.equals("marker")) {
            this.marker = Double.valueOf(value.toString()).intValue();
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
