package com.box.sdk;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.text.ParseException;
import java.util.Date;

import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;

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

    BoxFileVersion(BoxAPIConnection api, JsonObject jsonObject, String fileID) {
        super(api, jsonObject.get("id").asString());

        this.fileID = fileID;
        for (JsonObject.Member member : jsonObject) {
            JsonValue value = member.getValue();
            if (value.isNull()) {
                continue;
            }

            try {
                switch (member.getName()) {
                    case "sha1":
                        this.sha1 = value.asString();
                        break;
                    case "name":
                        this.name = value.asString();
                        break;
                    case "size":
                        this.size = Double.valueOf(value.toString()).longValue();
                        break;
                    case "created_at":
                        this.createdAt = BoxDateParser.parse(value.asString());
                        break;
                    case "modified_at":
                        this.modifiedAt = BoxDateParser.parse(value.asString());
                        break;
                    case "modified_by":
                        JsonObject userJSON = value.asObject();
                        String userID = userJSON.get("id").asString();
                        BoxUser user = new BoxUser(getAPI(), userID);
                        this.modifiedBy = user.new Info(userJSON);
                        break;
                    default:
                        break;
                }
            } catch (ParseException e) {
                assert false : "A ParseException indicates a bug in the SDK.";
            }
        }
    }

    public String getSha1() {
        return this.sha1;
    }

    public String getName() {
        return this.name;
    }

    public long getSize() {
        return this.size;
    }

    public Date getCreatedAt() {
        return this.createdAt;
    }

    public Date getModifiedAt() {
        return this.modifiedAt;
    }

    public BoxUser.Info getModifiedBy() {
        return this.modifiedBy;
    }

    public void delete() {
        URL url = VERSION_URL_TEMPLATE.build(this.getAPI().getBaseURL(), this.fileID, this.getID());
        BoxAPIRequest request = new BoxAPIRequest(this.getAPI(), url, "DELETE");
        BoxAPIResponse response = request.send();
        response.disconnect();
    }

    public void download(OutputStream output) {
        this.download(output, null);
    }

    public void download(OutputStream output, ProgressListener listener) {
        URL url = CONTENT_URL_TEMPLATE.build(this.getAPI().getBaseURL(), this.fileID, this.getID());
        BoxAPIRequest request = new BoxAPIRequest(this.getAPI(), url, "GET");
        BoxAPIResponse response = request.send();
        InputStream input = response.getBody();

        long totalRead = 0;
        byte[] buffer = new byte[BUFFER_SIZE];
        try {
            int n = input.read(buffer);
            totalRead += n;
            while (n != -1) {
                output.write(buffer, 0, n);
                if (listener != null) {
                    listener.onProgressChanged(totalRead, response.getContentLength());
                }
                n = input.read(buffer);
                totalRead += n;
            }
        } catch (IOException e) {
            throw new BoxAPIException("Couldn't connect to the Box API due to a network error.", e);
        }

        response.disconnect();
    }

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
