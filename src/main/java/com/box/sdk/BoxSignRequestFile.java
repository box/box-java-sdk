package com.box.sdk;

import com.eclipsesource.json.JsonObject;

/**
 * Represents a Box File to be included in a sign request.
 */
public class BoxSignRequestFile {
    private String fileId;
    private String fileVersionId;

    /**
     * Constructs a BoxSignRequestFile with specific file version to be used during sign request creation.
     *
     * @param fileId        id of the file.
     * @param fileVersionId id of the file versionm.
     */
    public BoxSignRequestFile(String fileId, String fileVersionId) {
        this.fileId = fileId;
        this.fileVersionId = fileVersionId;
    }

    /**
     * Constructs a BoxSignRequestFile to be used during sign request creation.
     *
     * @param fileId id of the file.
     */
    public BoxSignRequestFile(String fileId) {
        this.fileId = fileId;
    }

    /**
     * Gets the file id of the file.
     *
     * @return file id of the file.
     */
    public String getFileId() {
        return this.fileId;
    }

    /**
     * Sets the file id.
     *
     * @param fileId of the file.
     * @return this BoxSignRequestFile object for chaining.
     */
    public BoxSignRequestFile setFileId(String fileId) {
        this.fileId = fileId;
        return this;
    }

    /**
     * Gets the file version id of the file.
     *
     * @return file version id of the file.
     */
    public String getFileVersionId() {
        return this.fileVersionId;
    }

    /**
     * Sets the file version id.
     *
     * @param fileVersionId of the file.
     * @return this BoxSignRequestFile object for chaining.
     */
    public BoxSignRequestFile setFileVersionId(String fileVersionId) {
        this.fileVersionId = fileVersionId;
        return this;
    }

    /**
     * Gets a JSON object representing this class.
     *
     * @return the JSON object representing this class.
     */
    public JsonObject getJSONObject() {
        JsonObject fileJSON = new JsonObject()
                .add("id", this.fileId)
                .add("type", "file");

        if (this.fileVersionId != null) {
            JsonObject fileVersionJSON = new JsonObject();
            fileVersionJSON.add("id", this.fileVersionId);
            fileVersionJSON.add("type", "file_version");
            fileJSON.add("file_version", fileVersionJSON);
        }

        return fileJSON;
    }

    static BoxSignRequestFile fromFile(BoxFile.Info sourceFile) {
        BoxSignRequestFile file = new BoxSignRequestFile(sourceFile.getID());
        if (sourceFile.getVersion() != null) {
            file.setFileVersionId(sourceFile.getVersionNumber());
        }

        return file;
    }
}
