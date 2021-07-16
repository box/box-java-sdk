package com.box.sdk;

import java.util.Date;

import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;

/**
 * Represents a prefill tag used in BoxSignRequest. When a document contains sign related tags in the content,
 * you can prefill them using this prefillTag by referencing the
 * 'id' of the tag as the externalId field of the prefill tag.
 */
public class BoxSignRequestPrefillTag extends BoxJSONObject {
    private String documentTagId;
    private String textValue;
    private Boolean checkboxValue;
    private Date dateValue;

    /**
     * Constructs a BoxSignRequestPrefillTag with text prefill value.
     *
     * @param documentTagId if of the tag.
     * @param textValue     text prefill value.
     */
    public BoxSignRequestPrefillTag(String documentTagId, String textValue) {
        this.documentTagId = documentTagId;
        this.textValue = textValue;
    }

    /**
     * Constructs a BoxSignRequestPrefillTag with checkbox prefill value.
     *
     * @param documentTagId if of the tag.
     * @param checkboxValue checkbox prefill value.
     */
    public BoxSignRequestPrefillTag(String documentTagId, Boolean checkboxValue) {
        this.documentTagId = documentTagId;
        this.checkboxValue = checkboxValue;
    }

    /**
     * Constructs a BoxSignRequestPrefillTag with date prefill value.
     *
     * @param documentTagId if of the tag.
     * @param dateValue     date prefill value.
     */
    public BoxSignRequestPrefillTag(String documentTagId, Date dateValue) {
        this.documentTagId = documentTagId;
        this.dateValue = dateValue;
    }

    /**
     * Constructs a BoxSignRequestPrefillTag from a JSON string.
     *
     * @param json the JSON encoded enterprise.
     */
    public BoxSignRequestPrefillTag(String json) {
        super(json);
    }

    /**
     * Constructs an BoxSignRequestPrefillTag object using an already parsed JSON object.
     *
     * @param jsonObject the parsed JSON object.
     */
    BoxSignRequestPrefillTag(JsonObject jsonObject) {
        super(jsonObject);
    }

    /**
     * Gets the reference id of a particular tag added to the content
     * of the files being used to create the sign request.
     *
     * @return document tag id.
     */
    public String getDocumentTagId() {
        return this.documentTagId;
    }

    /**
     * Gets the text prefill value.
     *
     * @return text prefill value.
     */
    public String getTextValue() {
        return this.textValue;
    }

    /**
     * Gets the checkbox prefill value.
     *
     * @return checkbox prefill value.
     */
    public Boolean getCheckboxValue() {
        return this.checkboxValue;
    }

    /**
     * Gets the date prefill value.
     *
     * @return date prefill value.
     */
    public Date getDateValue() {
        return this.dateValue;
    }

    /**
     * Gets a JSON object reprsenting this class.
     *
     * @return the JSON object reprsenting this class.
     */
    public JsonObject getJSONObject() {
        JsonObject prefillTagObj = new JsonObject();
        if (this.documentTagId != null) {
            prefillTagObj.add("document_tag_id", this.documentTagId);
        }
        if (this.textValue != null) {
            prefillTagObj.add("text_value", this.textValue);
        }
        if (this.checkboxValue != null) {
            prefillTagObj.add("checkbox_value", this.checkboxValue);
        }
        if (this.dateValue != null) {
            prefillTagObj.add("date_value", BoxDateFormat.format(this.dateValue));
        }

        return prefillTagObj;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    void parseJSONMember(JsonObject.Member member) {
        JsonValue value = member.getValue();
        String memberName = member.getName();
        try {
            if (memberName.equals("document_tag_id")) {
                this.documentTagId = value.asString();
            } else if (memberName.equals("text_value")) {
                this.textValue = value.asString();
            } else if (memberName.equals("checkbox_value")) {
                this.checkboxValue = value.asBoolean();
            } else if (memberName.equals("date_value")) {
                this.dateValue = BoxDateFormat.parse(value.asString());
            }
        } catch (Exception e) {
            throw new BoxDeserializationException(memberName, value.toString(), e);
        }
    }
}