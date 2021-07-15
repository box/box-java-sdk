package com.box.sdk;

import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;

/**
 * Represents attachments that signers are required to upload.
 */
public class BoxSignRequestRequiredAttachment extends BoxJSONObject {
	private String name;

	/**
	 * Constructs a BoxSignRequestRequiredAttachment object using an already parsed JSON object.
	 * @param jsonObject the parsed JSON object.
	 */
	BoxSignRequestRequiredAttachment(JsonObject jsonObject) {
		super(jsonObject);
	}

	/**
	 * Constructs a BoxSignRequestRequiredAttachment with attachment name.
	 *
	 * @param name of attachment (e.g passport).
	 * @return BoxSignRequestRequiredAttachment object.
	 */
	public BoxSignRequestRequiredAttachment(String name) {
		this.name = name;
	}

	/**
	 * Gets the name of the required attachment.
	 * @return name of the required attachment.
	 */
	public String getName() { return name; }

	/**
	 * Gets a JSON object reprsenting this class.
	 *
	 * @return the JSON object reprsenting this class.
	 */
	public JsonObject getJSONObject() {
		JsonObject requiredAttachmentObj = new JsonObject()
				.add("name", name);

		return requiredAttachmentObj;
	}

	/**
	 * {@inheritDoc}
	 */
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
