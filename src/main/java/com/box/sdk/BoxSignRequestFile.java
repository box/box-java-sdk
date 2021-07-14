package com.box.sdk;

import com.eclipsesource.json.JsonObject;

/**
 *
 */
public class BoxSignRequestFile {
	private String fileId;
	private String fileVersion;

	public BoxSignRequestFile(String fileId, String fileVersion) {
		this.fileId = fileId;
		this.fileVersion = fileVersion;
	}

	public BoxSignRequestFile(String fileId) {
		this.fileId = fileId;
	}

	/**
	 * Gets a JSON object reprsenting this class.
	 *
	 * @return the JSON object reprsenting this class.
	 */
	//TODO fileVersion
	public JsonObject getJSONObject() {
		JsonObject fileObj = new JsonObject()
				.add("id", fileId)
				.add("type", "file");
		return fileObj;
	}
}
