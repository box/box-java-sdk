package com.box.sdk;

import java.net.URL;
import java.util.Iterator;
import java.util.logging.Logger;

import com.eclipsesource.json.JsonObject;

public final class BoxFolder extends BoxItem implements Iterable<BoxItem> {
    private static final Logger LOGGER = Logger.getLogger(BoxFolder.class.getName());
    private static final URLTemplate CREATE_FOLDER_URL = new URLTemplate("folders");
    private static final URLTemplate DELETE_FOLDER_URL = new URLTemplate("folders/%s?recursive=%b");
    private static final URLTemplate FOLDER_INFO_URL_TEMPLATE = new URLTemplate("folders/%s");

    public BoxFolder(OAuthSession session, String id) {
        super(session, id);
    }

    public static BoxFolder getRootFolder(OAuthSession session) {
        return new BoxFolder(session, "0");
    }

    public BoxFolder.Info getInfo() {
        URL url = FOLDER_INFO_URL_TEMPLATE.build(this.getID());
        BoxAPIRequest request = new BoxAPIRequest(this.getSession(), url, "GET");
        BoxJSONResponse response = (BoxJSONResponse) request.send();
        JsonObject jsonObject = JsonObject.readFrom(response.getJSON());
        return new Info(jsonObject);
    }

    public BoxFolder createFolder(String name) {
        JsonObject parent = new JsonObject();
        parent.add("id", this.getID());

        JsonObject newFolder = new JsonObject();
        newFolder.add("name", name);
        newFolder.add("parent", parent);

        BoxJSONRequest request = new BoxJSONRequest(this.getSession(), CREATE_FOLDER_URL.build(), "POST");
        request.setJSON(newFolder.toString());
        BoxJSONResponse response = (BoxJSONResponse) request.send();
        JsonObject createdFolder = JsonObject.readFrom(response.getJSON());

        return new BoxFolder(this.getSession(), createdFolder.get("id").asString());
    }

    public void delete(boolean recursive) {
        URL url = DELETE_FOLDER_URL.build(this.getID(), recursive);
        BoxAPIRequest request = new BoxAPIRequest(this.getSession(), url, "DELETE");
        BoxAPIResponse response = request.send();
        response.disconnect();
    }

    public Iterator<BoxItem> iterator() {
        return new BoxItemIterator(BoxFolder.this.getSession(), BoxFolder.this.getID());
    }

    public class Info extends BoxItem.Info {
        public Info() { }

        public Info(JsonObject jsonObject) {
            super(jsonObject);
        }

        @Override
        protected void parseJsonMember(JsonObject.Member member) {
            super.parseJsonMember(member);

            String memberName = member.getName();
            switch (memberName) {
                default:
                    break;
            }
        }
    }
}
