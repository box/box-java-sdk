package com.box.sdk;

import com.eclipsesource.json.JsonObject;

/**
 * Represents a Box file to be included when creating a sign request.
 */
public class BoxSignRequestFile {
	private String fileId;
	private String fileVersionId;

	/**
	 * Constructs a BoxSignRequestFile with specific file version to be used during sign request creation.
	 *
	 * @param fileId id of the file.
	 * @param fileVersionId id of the file versionm.
	 * @return BoxSignRequestFile object.
	 */
	public BoxSignRequestFile(String fileId, String fileVersionId) {
		this.fileId = fileId;
		this.fileVersionId = fileVersionId;
	}

	/**
	 * Constructs a BoxSignRequestFile to be used during sign request creation.
	 *
	 * @param fileId id of the file.
	 * @return BoxSignRequestFile object.
	 */
	public BoxSignRequestFile(String fileId) {
		this.fileId = fileId;
	}

	/**
	 * Gets a JSON object reprsenting this class.
	 *
	 * @return the JSON object reprsenting this class.
	 */
	public JsonObject getJSONObject() {
		JsonObject fileJSON = new JsonObject()
				.add("id", fileId)
				.add("type", "file");

		if (fileVersionId != null) {
			JsonObject fileVersionJSON = new JsonObject();
			fileVersionJSON.add("id", fileVersionId);
			fileVersionJSON.add("type", "file_version");
			fileJSON.add("file_version", fileVersionJSON);
		}

		return fileJSON;
	}
}
