package com.box.sdk;

import java.net.URL;
import java.util.Iterator;

import com.eclipsesource.json.JsonObject;

public class BoxTrash implements Iterable<BoxItem.Info> {
    private static final long LIMIT = 1000;
    private static final URLTemplate GET_ITEMS_URL = new URLTemplate("folders/trash/items/");
    private static final URLTemplate FOLDER_INFO_URL_TEMPLATE = new URLTemplate("folders/%s/trash");
    private static final URLTemplate FILE_INFO_URL_TEMPLATE = new URLTemplate("files/%s/trash");
    private static final URLTemplate RESTORE_FILE_URL_TEMPLATE = new URLTemplate("files/%s");
    private static final URLTemplate RESTORE_FOLDER_URL_TEMPLATE = new URLTemplate("folders/%s");

    private final BoxAPIConnection api;

    public BoxTrash(BoxAPIConnection api) {
        this.api = api;
    }

    public void deleteFolder(String folderID) {
        URL url = FOLDER_INFO_URL_TEMPLATE.build(this.api.getBaseURL(), folderID);
        BoxAPIRequest request = new BoxAPIRequest(this.api, url, "DELETE");
        BoxAPIResponse response = request.send();
        response.disconnect();
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

    public BoxFolder.Info restoreFolder(String folderID) {
        URL url = RESTORE_FOLDER_URL_TEMPLATE.build(this.api.getBaseURL(), folderID);
        BoxAPIRequest request = new BoxAPIRequest(this.api, url, "POST");
        BoxJSONResponse response = (BoxJSONResponse) request.send();
        JsonObject responseJSON = JsonObject.readFrom(response.getJSON());

        BoxFolder restoredFolder = new BoxFolder(this.api, responseJSON.get("id").asString());
        return restoredFolder.new Info(responseJSON);
    }

    public BoxFolder.Info restoreFolder(String folderID, String newName, String newParentID) {
        JsonObject requestJSON = new JsonObject();

        if (newName != null) {
            requestJSON.add("name", newName);
        }

        if (newParentID != null) {
            JsonObject parent = new JsonObject();
            parent.add("id", newParentID);
            requestJSON.add("parent", parent);
        }

        URL url = RESTORE_FOLDER_URL_TEMPLATE.build(this.api.getBaseURL(), folderID);
        BoxJSONRequest request = new BoxJSONRequest(this.api, url, "POST");
        request.setBody(requestJSON.toString());
        BoxJSONResponse response = (BoxJSONResponse) request.send();
        JsonObject responseJSON = JsonObject.readFrom(response.getJSON());

        BoxFolder restoredFolder = new BoxFolder(this.api, responseJSON.get("id").asString());
        return restoredFolder.new Info(responseJSON);
    }

    public void deleteFile(String fileID) {
        URL url = FILE_INFO_URL_TEMPLATE.build(this.api.getBaseURL(), fileID);
        BoxAPIRequest request = new BoxAPIRequest(this.api, url, "DELETE");
        BoxAPIResponse response = request.send();
        response.disconnect();
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

    public BoxFile.Info restoreFile(String fileID) {
        URL url = RESTORE_FILE_URL_TEMPLATE.build(this.api.getBaseURL(), fileID);
        BoxAPIRequest request = new BoxAPIRequest(this.api, url, "POST");
        BoxJSONResponse response = (BoxJSONResponse) request.send();
        JsonObject responseJSON = JsonObject.readFrom(response.getJSON());

        BoxFile restoredFile = new BoxFile(this.api, responseJSON.get("id").asString());
        return restoredFile.new Info(responseJSON);
    }

    public BoxFile.Info restoreFile(String fileID, String newName, String newParentID) {
        JsonObject requestJSON = new JsonObject();

        if (newName != null) {
            requestJSON.add("name", newName);
        }

        if (newParentID != null) {
            JsonObject parent = new JsonObject();
            parent.add("id", newParentID);
            requestJSON.add("parent", parent);
        }

        URL url = RESTORE_FILE_URL_TEMPLATE.build(this.api.getBaseURL(), fileID);
        BoxJSONRequest request = new BoxJSONRequest(this.api, url, "POST");
        request.setBody(requestJSON.toString());
        BoxJSONResponse response = (BoxJSONResponse) request.send();
        JsonObject responseJSON = JsonObject.readFrom(response.getJSON());

        BoxFile restoredFile = new BoxFile(this.api, responseJSON.get("id").asString());
        return restoredFile.new Info(responseJSON);
    }

    public Iterator<BoxItem.Info> iterator() {
        URL url = GET_ITEMS_URL.build(this.api.getBaseURL());
        return new BoxItemIterator(this.api, url);
    }
}
