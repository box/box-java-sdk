package com.box.sdk;

import static com.box.sdk.BinaryBodyUtils.writeStream;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;
import java.io.OutputStream;
import java.net.URL;
import java.text.ParseException;
import java.util.Date;

/**
 * Represents a particular version of a file on Box.
 */
@BoxResourceType("file_version")
public class BoxFileVersion extends BoxResource {
    /**
     * Content URL Template.
     */
    public static final URLTemplate CONTENT_URL_TEMPLATE = new URLTemplate("files/%s/content?version=%s");
    /**
     * Version URL Template.
     */
    public static final URLTemplate VERSION_URL_TEMPLATE = new URLTemplate("files/%s/versions/%s");

    private String fileID;

    private String versionID;
    private String sha1;
    private String name;
    private long size;
    private String uploaderDisplayName;
    private Date createdAt;
    private Date modifiedAt;
    private BoxUser.Info modifiedBy;
    private Date trashedAt;
    private BoxUser.Info trashedBy;
    private Date restoredAt;
    private BoxUser.Info restoredBy;
    private Date purgedAt;
    private Long versionNumber;

    /**
     * Constructs a BoxFileVersion from a JSON string.
     *
     * @param api    the API connection to be used by the file.
     * @param json   the JSON encoded file version.
     * @param fileID the ID of the file.
     */
    public BoxFileVersion(BoxAPIConnection api, String json, String fileID) {
        this(api, Json.parse(json).asObject(), fileID);
    }

    BoxFileVersion(BoxAPIConnection api, JsonObject jsonObject, String fileID) {
        super(api, jsonObject.get("id").asString());

        this.fileID = fileID;
        this.parseJSON(jsonObject);
    }

    /**
     * Method used to update fields with values received from API.
     *
     * @param jsonObject JSON-encoded info about File Version object.
     */
    private void parseJSON(JsonObject jsonObject) {
        for (JsonObject.Member member : jsonObject) {
            JsonValue value = member.getValue();
            if (value.isNull()) {
                continue;
            }

            try {
                String memberName = member.getName();
                if (memberName.equals("id")) {
                    this.versionID = value.asString();
                } else if (memberName.equals("sha1")) {
                    this.sha1 = value.asString();
                } else if (memberName.equals("name")) {
                    this.name = value.asString();
                } else if (memberName.equals("size")) {
                    this.size = Double.valueOf(value.toString()).longValue();
                } else if (memberName.equals("uploader_display_name")) {
                    this.uploaderDisplayName = value.asString();
                } else if (memberName.equals("created_at")) {
                    this.createdAt = BoxDateFormat.parse(value.asString());
                } else if (memberName.equals("modified_at")) {
                    this.modifiedAt = BoxDateFormat.parse(value.asString());
                } else if (memberName.equals("trashed_at")) {
                    this.trashedAt = BoxDateFormat.parse(value.asString());
                } else if (memberName.equals("trashed_by")) {
                    JsonObject userJSON = value.asObject();
                    String userID = userJSON.get("id").asString();
                    BoxUser user = new BoxUser(getAPI(), userID);
                    this.trashedBy = user.new Info(userJSON);
                } else if (memberName.equals("modified_by")) {
                    JsonObject userJSON = value.asObject();
                    String userID = userJSON.get("id").asString();
                    BoxUser user = new BoxUser(getAPI(), userID);
                    this.modifiedBy = user.new Info(userJSON);
                } else if (memberName.equals("restored_at")) {
                    this.restoredAt = BoxDateFormat.parse(value.asString());
                } else if (memberName.equals("restored_by")) {
                    JsonObject userJSON = value.asObject();
                    String userID = userJSON.get("id").asString();
                    BoxUser user = new BoxUser(getAPI(), userID);
                    this.restoredBy = user.new Info(userJSON);
                } else if (memberName.equals("purged_at")) {
                    this.purgedAt = BoxDateFormat.parse(value.asString());
                } else if (memberName.equals("version_number")) {
                    this.versionNumber = Long.parseLong(value.asString());
                }
            } catch (ParseException e) {
                assert false : "A ParseException indicates a bug in the SDK.";
            }
        }
    }

    /**
     * @return the file id this file version belongs to.
     */
    public String getFileID() {
        return this.fileID;
    }

    /**
     * Used if no or wrong file id was set with constructor.
     *
     * @param fileID the file id this file version belongs to.
     */
    public void setFileID(String fileID) {
        this.fileID = fileID;
    }

    /**
     * Gets the version ID of this version of the file.
     *
     * @return the version ID of this version of the file.
     */
    public String getVersionID() {
        return this.versionID;
    }

    /**
     * Gets the SHA1 hash of this version of the file.
     *
     * @return the SHA1 hash of this version of the file.
     */
    public String getSha1() {
        return this.sha1;
    }

    /**
     * Gets the name of this version of the file.
     *
     * @return the name of this version of the file.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets the size of this version of the file.
     *
     * @return the size of this version of the file.
     */
    public long getSize() {
        return this.size;
    }

    /**
     * Gets the time that this version of the file was created.
     *
     * @return the time that this version of the file was created.
     */
    public Date getCreatedAt() {
        return this.createdAt;
    }

    /**
     * Gets the user's name at the time of upload.
     *
     * @return the time user's name at the time of upload.
     */
    public String getUploaderDisplayName() {
        return this.uploaderDisplayName;
    }

    /**
     * Gets the time that this version of the file was modified.
     *
     * @return the time that this version of the file was modified.
     */
    public Date getModifiedAt() {
        return this.modifiedAt;
    }

    /**
     * Gets the time that this version of the file was deleted.
     *
     * @return the time that this version of the file was deleted.
     */
    public Date getTrashedAt() {
        return this.trashedAt;
    }

    /**
     * Gets information about the user who trashed this version of the file.
     *
     * @return info about the user who trashed this version of the file.
     */
    public BoxUser.Info getTrashedBy() {
        return this.trashedBy;
    }

    /**
     * Gets information about the user who last modified this version of the file.
     *
     * @return info about the user who last modified this version of the file.
     */
    public BoxUser.Info getModifiedBy() {
        return this.modifiedBy;
    }

    /**
     * Gets the time that this version of the file was restored.
     *
     * @return the time that this version of the file was restored.
     */
    public Date getRestoredAt() {
        return this.restoredAt;
    }

    /**
     * Gets information about the user who restored this version of the file.
     *
     * @return info about the user who restored this version of the file.
     */
    public BoxUser.Info getRestoredBy() {
        return this.restoredBy;
    }

    /**
     * Gets the time that this version of the file was purged.
     *
     * @return the time that this version of the file was purged.
     */
    public Date getPurgedAt() {
        return this.purgedAt;
    }

    /**
     * Returns version number.
     *
     * @return version number
     */
    public Long getVersionNumber() {
        return versionNumber;
    }

    /**
     * Deletes this version of the file.
     */
    public void delete() {
        URL url = VERSION_URL_TEMPLATE.build(this.getAPI().getBaseURL(), this.fileID, this.getID());
        BoxAPIRequest request = new BoxAPIRequest(this.getAPI(), url, "DELETE");
        request.send().close();
    }

    /**
     * Downloads this version of the file to a given OutputStream.
     *
     * @param output the stream to where the file will be written.
     */
    public void download(OutputStream output) {
        this.download(output, null);
    }

    /**
     * Downloads this version of the file to a given OutputStream while reporting the progress to a ProgressListener.
     *
     * @param output   the stream to where the file will be written.
     * @param listener a listener for monitoring the download's progress.
     */
    public void download(OutputStream output, ProgressListener listener) {
        URL url = CONTENT_URL_TEMPLATE.build(this.getAPI().getBaseURL(), this.fileID, this.getID());
        BoxAPIRequest request = new BoxAPIRequest(this.getAPI(), url, "GET");
        writeStream(request.send(), output, listener);
    }

    /**
     * Promotes this version of the file to be the latest version.
     */
    public void promote() {
        URL url = VERSION_URL_TEMPLATE.buildAlpha(this.getAPI().getBaseURL(), this.fileID, "current");

        JsonObject jsonObject = new JsonObject();
        jsonObject.add("type", "file_version");
        jsonObject.add("id", this.getID());

        BoxJSONRequest request = new BoxJSONRequest(this.getAPI(), url, "POST");
        request.setBody(jsonObject.toString());
        try (BoxJSONResponse response = request.send()) {
            this.parseJSON(Json.parse(response.getJSON()).asObject());
        }
    }
}
