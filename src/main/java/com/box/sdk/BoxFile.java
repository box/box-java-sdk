package com.box.sdk;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

import com.eclipsesource.json.JsonObject;

public class BoxFile extends BoxItem {
    private static final URLTemplate FILE_URL_TEMPLATE = new URLTemplate("files/%s");
    private static final URLTemplate CONTENT_URL_TEMPLATE = new URLTemplate("files/%s/content");

    private final URL fileURL;
    private final URL contentURL;

    public BoxFile(BoxAPIConnection api, String id) {
        super(api, id);

        this.fileURL = FILE_URL_TEMPLATE.build(this.getAPI().getBaseURL(), this.getID());
        this.contentURL = CONTENT_URL_TEMPLATE.build(this.getAPI().getBaseURL(), this.getID());
    }

    public void download(OutputStream output) {
        BoxAPIRequest request = new BoxAPIRequest(this.getAPI(), this.contentURL, "GET");
        BoxAPIResponse response = request.send();
        InputStream input = response.getBody();

        try {
            int b = input.read();
            while (b != -1) {
                output.write(b);
                b = input.read();
            }
        } catch (IOException e) {
            throw new BoxAPIException("Couldn't connect to the Box API due to a network error.", e);
        }

        response.disconnect();
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

    public class Info extends BoxItem.Info<BoxFile> {
        private String sha1;

        public Info() {
            super();
        }

        public Info(String json) {
            super(json);
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
