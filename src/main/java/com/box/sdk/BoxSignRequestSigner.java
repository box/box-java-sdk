package com.box.sdk;

import com.eclipsesource.json.JsonObject;

/**
 *
 */
public class BoxSignRequestSigner {
	private String email;
	private String name;

	public BoxSignRequestSigner(String email, String name) {
		super();
		this.email = email;
		this.name = name;
	}

	/**
	 * Gets a JSON object reprsenting this class.
	 *
	 * @return the JSON object reprsenting this class.
	 */
	public JsonObject getJSONObject() {
		JsonObject jsonObj = new JsonObject()
				.add("email", this.email)
				.add("name", this.name);
		return jsonObj;
	}
}
