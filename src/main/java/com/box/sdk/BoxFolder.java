package com.box.sdk;

import java.io.InputStream;
import java.net.URL;
import java.util.Date;
import java.util.Iterator;
import java.util.logging.Logger;

import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;

public final class BoxFolder extends BoxItem implements Iterable<BoxItem> {
    private static final Logger LOGGER = Logger.getLogger(BoxFolder.class.getName());
    private static final String UPLOAD_FILE_URL_BASE = "https://upload.box.com/api/2.0/";
    private static final URLTemplate CREATE_FOLDER_URL = new URLTemplate("folders");
    private static final URLTemplate DELETE_FOLDER_URL = new URLTemplate("folders/%s?recursive=%b");
    private static final URLTemplate FOLDER_INFO_URL_TEMPLATE = new URLTemplate("folders/%s");
    private static final URLTemplate UPLOAD_FILE_URL = new URLTemplate("files/content");

    public BoxFolder(BoxAPIConnection api, String id) {
        super(api, id);
    }

    public static BoxFolder getRootFolder(BoxAPIConnection api) {
        return new BoxFolder(api, "0");
    }

    public BoxFolder.Info getInfo() {
        URL url = FOLDER_INFO_URL_TEMPLATE.build(this.getAPI().getBaseURL(), this.getID());
        BoxAPIRequest request = new BoxAPIRequest(this.getAPI(), url, "GET");
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

        BoxJSONRequest request = new BoxJSONRequest(this.getAPI(), CREATE_FOLDER_URL.build(this.getAPI().getBaseURL()),
            "POST");
        request.setJSON(newFolder.toString());
        BoxJSONResponse response = (BoxJSONResponse) request.send();
        JsonObject createdFolder = JsonObject.readFrom(response.getJSON());

        return new BoxFolder(this.getAPI(), createdFolder.get("id").asString());
    }

    public void delete(boolean recursive) {
        URL url = DELETE_FOLDER_URL.build(this.getAPI().getBaseURL(), this.getID(), recursive);
        BoxAPIRequest request = new BoxAPIRequest(this.getAPI(), url, "DELETE");
        BoxAPIResponse response = request.send();
        response.disconnect();
    }

    public BoxFile uploadFile(InputStream fileContent, String name, Date created, Date modified) {
        URL uploadURL = UPLOAD_FILE_URL.build(UPLOAD_FILE_URL_BASE);
        BoxMultipartRequest request = new BoxMultipartRequest(getAPI(), uploadURL);
        request.putField("parent_id", getID());
        request.setFile(fileContent, name);

        BoxJSONResponse response = (BoxJSONResponse) request.send();
        JsonObject collection = JsonObject.readFrom(response.getJSON());
        JsonArray entries = collection.get("entries").asArray();
        String uploadedFileID = entries.get(0).asObject().get("id").asString();

        return new BoxFile(getAPI(), uploadedFileID);
    }

    public Iterator<BoxItem> iterator() {
        return new BoxItemIterator(BoxFolder.this.getAPI(), BoxFolder.this.getID());
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
