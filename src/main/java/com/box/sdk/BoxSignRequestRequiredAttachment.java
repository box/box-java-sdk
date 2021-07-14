package com.box.sdk;

import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;

/**
 *
 */
public class BoxSignRequestRequiredAttachment extends BoxJSONObject {
	private String name;


	BoxSignRequestRequiredAttachment(JsonObject jsonObject) {
		super(jsonObject);
	}

	public BoxSignRequestRequiredAttachment(String name) {
		this.name = name;
	}

	public JsonObject getJSONObject() {
		JsonObject requiredAttachmentObj = new JsonObject()
				.add("name", name);

		return requiredAttachmentObj;
	}

	@Override
	void parseJSONMember(JsonObject.Member member){
		JsonValue value = member.getValue();
		String memberName = member.getName();
		try{
			if (memberName.equals("name")) {
				this.name = value.asString();
			}
		}catch (Exception e) {
			throw new BoxDeserializationException(memberName, value.toString(), e);
		}
	}
}
