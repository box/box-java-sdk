package com.box.sdk;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;

public class BoxFile extends BoxItem {
    private static final URLTemplate FILE_URL_TEMPLATE = new URLTemplate("files/%s");
    private static final URLTemplate CONTENT_URL_TEMPLATE = new URLTemplate("files/%s/content");
    private static final URLTemplate VERSIONS_URL_TEMPLATE = new URLTemplate("files/%s/versions");
    private static final URLTemplate COPY_URL_TEMPLATE = new URLTemplate("files/%s/copy");
    private static final int BUFFER_SIZE = 8192;

    private final URL fileURL;
    private final URL contentURL;

    public BoxFile(BoxAPIConnection api, String id) {
        super(api, id);

        this.fileURL = FILE_URL_TEMPLATE.build(this.getAPI().getBaseURL(), this.getID());
        this.contentURL = CONTENT_URL_TEMPLATE.build(this.getAPI().getBaseURL(), this.getID());
    }

    public SharedLink createSharedLink(SharedLink.Access access, Date unshareDate, SharedLink.Permissions permissions) {
        SharedLink sharedLink = new SharedLink();
        sharedLink.setAccess(access);

        if (unshareDate != null) {
            sharedLink.setUnsharedDate(unshareDate);
        }

        sharedLink.setPermissions(permissions);

        Info info = new Info();
        info.setSharedLink(sharedLink);

        this.updateInfo(info);
        return info.getSharedLink();
    }

    public void download(OutputStream output) {
        this.download(output, null);
    }

    public void download(OutputStream output, ProgressListener listener) {
        BoxAPIRequest request = new BoxAPIRequest(this.getAPI(), this.contentURL, "GET");
        BoxAPIResponse response = request.send();
        InputStream input = response.getBody();
        if (listener != null) {
            input = new ProgressInputStream(input, listener, response.getContentLength());
        }

        byte[] buffer = new byte[BUFFER_SIZE];
        try {
            int n = input.read(buffer);
            while (n != -1) {
                output.write(buffer, 0, n);
                n = input.read(buffer);
            }
        } catch (IOException e) {
            throw new BoxAPIException("Couldn't connect to the Box API due to a network error.", e);
        }

        response.disconnect();
    }

    public BoxFile.Info copy(BoxFolder destination) {
        return this.copy(destination, null);
    }

    public BoxFile.Info copy(BoxFolder destination, String newName) {
        URL url = COPY_URL_TEMPLATE.build(this.getAPI().getBaseURL(), this.getID());

        JsonObject parent = new JsonObject();
        parent.add("id", destination.getID());

        JsonObject copyInfo = new JsonObject();
        copyInfo.add("parent", parent);
        if (newName != null) {
            copyInfo.add("name", newName);
        }

        BoxJSONRequest request = new BoxJSONRequest(this.getAPI(), url, "POST");
        request.setBody(copyInfo.toString());
        BoxJSONResponse response = (BoxJSONResponse) request.send();
        JsonObject responseJSON = JsonObject.readFrom(response.getJSON());
        BoxFile copiedFile = new BoxFile(this.getAPI(), responseJSON.get("id").asString());
        return copiedFile.new Info(responseJSON);
    }

    public void delete() {
        BoxAPIRequest request = new BoxAPIRequest(this.getAPI(), this.fileURL, "DELETE");
        BoxAPIResponse response = request.send();
        response.disconnect();
    }

    public BoxFile.Info getInfo() {
        BoxAPIRequest request = new BoxAPIRequest(this.getAPI(), this.fileURL, "GET");
        BoxJSONResponse response = (BoxJSONResponse) request.send();
        return new Info(response.getJSON());
    }

    public BoxFile.Info getInfo(String... fields) {
        String queryString = new QueryStringBuilder().addFieldsParam(fields).toString();
        URL url = FILE_URL_TEMPLATE.buildWithQuery(this.getAPI().getBaseURL(), queryString, this.getID());

        BoxAPIRequest request = new BoxAPIRequest(this.getAPI(), url, "GET");
        BoxJSONResponse response = (BoxJSONResponse) request.send();
        return new Info(response.getJSON());
    }

    public void updateInfo(BoxFile.Info info) {
        URL url = FILE_URL_TEMPLATE.build(this.getAPI().getBaseURL(), this.getID());
        BoxJSONRequest request = new BoxJSONRequest(this.getAPI(), url, "PUT");
        request.setBody(info.getPendingChanges());
        BoxJSONResponse response = (BoxJSONResponse) request.send();
        JsonObject jsonObject = JsonObject.readFrom(response.getJSON());
        info.update(jsonObject);
    }

    public Collection<BoxFileVersion> getVersions() {
        URL url = VERSIONS_URL_TEMPLATE.build(this.getAPI().getBaseURL(), this.getID());
        BoxAPIRequest request = new BoxAPIRequest(this.getAPI(), url, "GET");
        BoxJSONResponse response = (BoxJSONResponse) request.send();

        JsonObject jsonObject = JsonObject.readFrom(response.getJSON());
        JsonArray entries = jsonObject.get("entries").asArray();
        Collection<BoxFileVersion> versions = new ArrayList<BoxFileVersion>();
        for (JsonValue entry : entries) {
            versions.add(new BoxFileVersion(this.getAPI(), entry.asObject(), this.getID()));
        }

        return versions;
    }

    public void uploadVersion(InputStream fileContent) {
        this.uploadVersion(fileContent, null);
    }

    public void uploadVersion(InputStream fileContent, Date modified) {
        this.uploadVersion(fileContent, modified, 0, null);
    }

    public void uploadVersion(InputStream fileContent, Date modified, long fileSize, ProgressListener listener) {
        URL uploadURL = CONTENT_URL_TEMPLATE.build(this.getAPI().getBaseUploadURL(), this.getID());
        BoxMultipartRequest request = new BoxMultipartRequest(getAPI(), uploadURL);
        if (fileSize > 0) {
            request.setFile(fileContent, "", fileSize);
        } else {
            request.setFile(fileContent, "");
        }

        if (modified != null) {
            request.putField("content_modified_at", modified);
        }

        BoxAPIResponse response;
        if (listener == null) {
            response = request.send();
        } else {
            response = request.send(listener);
        }
        response.disconnect();
    }

    public class Info extends BoxItem.Info<BoxFile> {
        private String sha1;

        public Info() {
            super();
        }

        public Info(String json) {
            super(json);
        }

        protected Info(JsonObject jsonObject) {
            super(jsonObject);
        }

        @Override
        public BoxFile getResource() {
            return BoxFile.this;
        }

        public String getSha1() {
            return this.sha1;
        }

        @Override
        protected void parseJSONMember(JsonObject.Member member) {
            super.parseJSONMember(member);

            String memberName = member.getName();
            switch (memberName) {
                case "sha1":
                    this.sha1 = member.getValue().asString();
                    break;
                default:
                    break;
            }
        }
    }
}
