package com.box.sdk;

import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;

import java.util.Date;

/**
 *
 */
public class BoxSignRequestPrefillTag extends BoxJSONObject {
	private String documentTagId;
	private String textValue;
	private Boolean checkboxValue;
	private Date dateValue;

	public BoxSignRequestPrefillTag(String documentTagId, String textValue) {
		this.documentTagId = documentTagId;
		this.textValue = textValue;
	}

	public BoxSignRequestPrefillTag(String documentTagId, Boolean checkboxValue) {
		this.documentTagId = documentTagId;
		this.checkboxValue = checkboxValue;
	}

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

	BoxSignRequestPrefillTag(JsonObject jsonObject) {
		super(jsonObject);
	}

	public JsonObject getJSONObject() {
		JsonObject prefillTagObj = new JsonObject();
		if(documentTagId != null){
			prefillTagObj.add("document_tag_id", documentTagId);
		}
		if(textValue != null){
			prefillTagObj.add("text_value", textValue);
		}
		if(checkboxValue != null){
			prefillTagObj.add("checkbox_value", checkboxValue);
		}
		if(dateValue != null){
			prefillTagObj.add("date_value", BoxDateFormat.format(dateValue));
		}

		return prefillTagObj;
	}

	@Override
	void parseJSONMember(JsonObject.Member member){
		JsonValue value = member.getValue();
		String memberName = member.getName();
		try{
			if (memberName.equals("document_tag_id")) {
				this.documentTagId = value.asString();
			} else if (memberName.equals("text_value")) {
				this.textValue = value.asString();
			} else if (memberName.equals("checkbox_value")) {
				this.checkboxValue = value.asBoolean();
			} else if (memberName.equals("date_value")) {
				this.dateValue = BoxDateFormat.parse(value.asString());
			}
		}catch (Exception e) {
			throw new BoxDeserializationException(memberName, value.toString(), e);
		}
	}
}
