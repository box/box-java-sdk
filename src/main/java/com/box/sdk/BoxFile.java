package com.box.sdk;

import java.net.URL;
import java.util.logging.Logger;

import com.eclipsesource.json.JsonObject;

public class BoxFile extends BoxItem {
    private static final URLTemplate FILE_INFO_URL_TEMPLATE = new URLTemplate("files/%s");
    private static final Logger LOGGER = Logger.getLogger(BoxFolder.class.getName());

    public BoxFile(BoxAPIConnection api, String id) {
        super(api, id);
    }

    public BoxFile.Info getInfo() {
        URL url = FILE_INFO_URL_TEMPLATE.build(this.getAPI().getBaseURL(), this.getID());
        BoxAPIRequest request = new BoxAPIRequest(this.getAPI(), url, "GET");
        BoxJSONResponse response = (BoxJSONResponse) request.send();
        JsonObject jsonObject = JsonObject.readFrom(response.getJSON());
        return new Info(jsonObject);
    }

    public class Info extends BoxItem.Info {
        private String sha1;

        public Info() { }

        public Info(JsonObject jsonObject) {
            super(jsonObject);
        }

        public String getSha1() {
            return this.sha1;
        }

        @Override
        protected void parseJsonMember(JsonObject.Member member) {
            super.parseJsonMember(member);

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
