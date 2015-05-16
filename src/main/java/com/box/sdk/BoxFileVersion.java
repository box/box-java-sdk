package com.box.sdk;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.text.ParseException;
import java.util.Date;

import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;

/**
 * Represents a particular version of a file on Box.
 */
public class BoxFileVersion extends BoxResource {
    private static final URLTemplate CONTENT_URL_TEMPLATE = new URLTemplate("files/%s/content?version=%s");
    private static final URLTemplate VERSION_URL_TEMPLATE = new URLTemplate("files/%s/versions/%s");
    private static final int BUFFER_SIZE = 8192;

    private final String fileID;

    private String sha1;
    private String name;
    private long size;
    private Date createdAt;
    private Date modifiedAt;
    private BoxUser.Info modifiedBy;

    /**
     * Constructs a BoxFileVersion from a JSON string.
     * @param  api    the API connection to be used by the file.
     * @param  json   the JSON encoded file version.
     * @param  fileID the ID of the file.
     */
    public BoxFileVersion(BoxAPIConnection api, String json, String fileID) {
        this(api, JsonObject.readFrom(json), fileID);
    }

    BoxFileVersion(BoxAPIConnection api, JsonObject jsonObject, String fileID) {
        super(api, jsonObject.get("id").asString());

        this.fileID = fileID;
        for (JsonObject.Member member : jsonObject) {
            JsonValue value = member.getValue();
            if (value.isNull()) {
                continue;
            }

            try {
                String memberName = member.getName();
                if (memberName.equals("sha1")) {
                    this.sha1 = value.asString();
                } else if (memberName.equals("name")) {
                    this.name = value.asString();
                } else if (memberName.equals("size")) {
                    this.size = Double.valueOf(value.toString()).longValue();
                } else if (memberName.equals("created_at")) {
                    this.createdAt = BoxDateFormat.parse(value.asString());
                } else if (memberName.equals("modified_at")) {
                    this.modifiedAt = BoxDateFormat.parse(value.asString());
                } else if (memberName.equals("modified_by")) {
                    JsonObject userJSON = value.asObject();
                    String userID = userJSON.get("id").asString();
                    BoxUser user = new BoxUser(getAPI(), userID);
                    this.modifiedBy = user.new Info(userJSON);
                }
            } catch (ParseException e) {
                assert false : "A ParseException indicates a bug in the SDK.";
            }
        }
    }

    /**
     * Gets the SHA1 hash of this version of the file.
     * @return the SHA1 hash of this version of the file.
     */
    public String getSha1() {
        return this.sha1;
    }

    /**
     * Gets the name of this version of the file.
     * @return the name of this version of the file.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets the size of this version of the file.
     * @return the size of this version of the file.
     */
    public long getSize() {
        return this.size;
    }

    /**
     * Gets the time that this version of the file was created.
     * @return the time that this version of the file was created.
     */
    public Date getCreatedAt() {
        return this.createdAt;
    }

    /**
     * Gets the time that this version of the file was modified.
     * @return the time that this version of the file was modified.
     */
    public Date getModifiedAt() {
        return this.modifiedAt;
    }

    /**
     * Gets information about the user who last modified this version of the file.
     * @return info about the user who last modified this version of the file.
     */
    public BoxUser.Info getModifiedBy() {
        return this.modifiedBy;
    }

    /**
     * Deletes this version of the file.
     */
    public void delete() {
        URL url = VERSION_URL_TEMPLATE.build(this.getAPI().getBaseURL(), this.fileID, this.getID());
        BoxAPIRequest request = new BoxAPIRequest(this.getAPI(), url, "DELETE");
        BoxAPIResponse response = request.send();
        response.disconnect();
    }

    /**
     * Downloads this version of the file to a given OutputStream.
     * @param output the stream to where the file will be written.
     */
    public void download(OutputStream output) {
        this.download(output, null);
    }

    /**
     * Downloads this version of the file to a given OutputStream while reporting the progress to a ProgressListener.
     * @param output   the stream to where the file will be written.
     * @param listener a listener for monitoring the download's progress.
     */
    public void download(OutputStream output, ProgressListener listener) {
        URL url = CONTENT_URL_TEMPLATE.build(this.getAPI().getBaseURL(), this.fileID, this.getID());
        BoxAPIRequest request = new BoxAPIRequest(this.getAPI(), url, "GET");
        BoxAPIResponse response = request.send();
        InputStream input = response.getBody(listener);

        long totalRead = 0;
        byte[] buffer = new byte[BUFFER_SIZE];
        try {
            int n = input.read(buffer);
            totalRead += n;
            while (n != -1) {
                output.write(buffer, 0, n);
                n = input.read(buffer);
                totalRead += n;
            }
        } catch (IOException e) {
            throw new BoxAPIException("Couldn't connect to the Box API due to a network error.", e);
        }

        response.disconnect();
    }

    /**
     * Promotes this version of the file to be the latest version.
     */
    public void promote() {
        URL url = VERSION_URL_TEMPLATE.build(this.getAPI().getBaseURL(), this.fileID, "current");

        JsonObject jsonObject = new JsonObject();
        jsonObject.add("type", "file_version");
        jsonObject.add("id", this.getID());

        BoxJSONRequest request = new BoxJSONRequest(this.getAPI(), url, "POST");
        request.setBody(jsonObject.toString());
        BoxAPIResponse response = request.send();
        response.disconnect();
    }
}
