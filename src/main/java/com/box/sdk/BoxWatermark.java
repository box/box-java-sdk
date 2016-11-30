package com.box.sdk;

import java.text.ParseException;
import java.util.Date;

import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;

/**
 * Represents a watermark.
 * Watermarks are used to protect sensitive information in the Box account.
 *
 * @see <a href="https://docs.box.com/reference#watermarking">Watermarking</a>
 */
public class BoxWatermark extends BoxJSONObject {

    /**
     * Default imprint for watermarks.
     */
    public static final String WATERMARK_DEFAULT_IMPRINT = "default";

    /**
     * Json key for watermark.
     * @see BoxWatermark#parseJSONMember(JsonObject.Member)
     */
    public static final String WATERMARK_JSON_KEY = "watermark";

    /**
     * Json key for created_at param.
     * @see BoxWatermark#parseJSONMember(JsonObject.Member)
     */
    public static final String CREATED_AT_JSON_KEY = "created_at";

    /**
     * Json key for modified_at param.
     * @see BoxWatermark#parseJSONMember(JsonObject.Member)
     */
    public static final String MODIFIED_AT_JSON_KEY = "modified_at";

    /**
     * Json key for watermark param.
     */
    public static final String WATERMARK_IMPRINT_JSON_KEY = "imprint";

    /**
     * @see #getCreatedAt()
     */
    private Date createdAt;

    /**
     * @see #getModifiedAt()
     */
    private Date modifiedAt;

    /**
     * Constructs an empty watermark object.
     */
    public BoxWatermark() {
        super();
    }

    /**
     * Constructs a watermark object by parsing information from a JSON string.
     * @param  json the JSON string to parse.
     */
    public BoxWatermark(String json) {
        super(json);
    }

    /**
     * Constructs a watermark object using an already parsed JSON object.
     * @param  jsonObject the parsed JSON object.
     */
    BoxWatermark(JsonObject jsonObject) {
        super(jsonObject);
    }

    /**
     * @return the time that the watermark was created.
     */
    public Date getCreatedAt() {
        return this.createdAt;
    }

    /**
     * @return the time that the watermark was last modified.
     */
    public Date getModifiedAt() {
        return this.modifiedAt;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    void parseJSONMember(JsonObject.Member member) {
        super.parseJSONMember(member);
        String memberName = member.getName();
        JsonValue value = member.getValue();
        if (memberName.equals(WATERMARK_JSON_KEY)) {
            try {
                this.createdAt = BoxDateFormat.parse(value.asObject().get(CREATED_AT_JSON_KEY).asString());
                this.modifiedAt = BoxDateFormat.parse(value.asObject().get(MODIFIED_AT_JSON_KEY).asString());
            } catch (ParseException e) {
                assert false : "A ParseException indicates a bug in the SDK.";
            }
        }
    }

}
