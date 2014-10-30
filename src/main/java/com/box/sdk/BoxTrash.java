package com.box.sdk;

import java.net.URL;
import java.util.Iterator;

import com.eclipsesource.json.JsonObject;

public class BoxTrash implements Iterable<BoxItem.Info> {
    private static final long LIMIT = 1000;
    private static final URLTemplate GET_ITEMS_URL = new URLTemplate("folders/trash/items/");
    private static final URLTemplate FOLDER_INFO_URL_TEMPLATE = new URLTemplate("folders/%s/trash");
    private static final URLTemplate FILE_INFO_URL_TEMPLATE = new URLTemplate("files/%s/trash");

    private final BoxAPIConnection api;

    public BoxTrash(BoxAPIConnection api) {
        this.api = api;
    }

    public BoxFolder.Info getFolderInfo(String folderID) {
        URL url = FOLDER_INFO_URL_TEMPLATE.build(this.api.getBaseURL(), folderID);
        BoxAPIRequest request = new BoxAPIRequest(this.api, url, "GET");
        BoxJSONResponse response = (BoxJSONResponse) request.send();
        JsonObject jsonObject = JsonObject.readFrom(response.getJSON());

        BoxFolder folder = new BoxFolder(this.api, jsonObject.get("id").asString());
        return folder.new Info(response.getJSON());
    }

    public BoxFolder.Info getFolderInfo(String folderID, String... fields) {
        String queryString = new QueryStringBuilder().addFieldsParam(fields).toString();
        URL url = FOLDER_INFO_URL_TEMPLATE.buildWithQuery(this.api.getBaseURL(), queryString, folderID);
        BoxAPIRequest request = new BoxAPIRequest(this.api, url, "GET");
        BoxJSONResponse response = (BoxJSONResponse) request.send();
        JsonObject jsonObject = JsonObject.readFrom(response.getJSON());

        BoxFolder folder = new BoxFolder(this.api, jsonObject.get("id").asString());
        return folder.new Info(response.getJSON());
    }

    public BoxFile.Info getFileInfo(String fileID) {
        URL url = FILE_INFO_URL_TEMPLATE.build(this.api.getBaseURL(), fileID);
        BoxAPIRequest request = new BoxAPIRequest(this.api, url, "GET");
        BoxJSONResponse response = (BoxJSONResponse) request.send();
        JsonObject jsonObject = JsonObject.readFrom(response.getJSON());

        BoxFile file = new BoxFile(this.api, jsonObject.get("id").asString());
        return file.new Info(response.getJSON());
    }

    public BoxFile.Info getFileInfo(String fileID, String... fields) {
        String queryString = new QueryStringBuilder().addFieldsParam(fields).toString();
        URL url = FILE_INFO_URL_TEMPLATE.buildWithQuery(this.api.getBaseURL(), queryString, fileID);
        BoxAPIRequest request = new BoxAPIRequest(this.api, url, "GET");
        BoxJSONResponse response = (BoxJSONResponse) request.send();
        JsonObject jsonObject = JsonObject.readFrom(response.getJSON());

        BoxFile file = new BoxFile(this.api, jsonObject.get("id").asString());
        return file.new Info(response.getJSON());
    }

    public Iterator<BoxItem.Info> iterator() {
        URL url = GET_ITEMS_URL.build(this.api.getBaseURL());
        return new BoxItemIterator(this.api, url);
    }
}
