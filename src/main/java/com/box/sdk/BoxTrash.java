package com.box.sdk;

import java.net.URL;
import java.util.Iterator;

import com.eclipsesource.json.JsonObject;

/**
 * Provides methods for deleting, recovering, and viewing a user's trashed files and folders.
 *
 * <p>Unless otherwise noted, the methods in this class can throw an unchecked {@link BoxAPIException} (unchecked
 * meaning that the compiler won't force you to handle it) if an error occurs. If you wish to implement custom error
 * handling for errors related to the Box REST API, you should capture this exception explicitly.</p>
 */
public class BoxTrash implements Iterable<BoxItem.Info> {

    /**
     * Get Item URL Template.
     */
    public static final URLTemplate GET_ITEMS_URL = new URLTemplate("folders/trash/items/");
    /**
     * Folder Info URL Template.
     */
    public static final URLTemplate FOLDER_INFO_URL_TEMPLATE = new URLTemplate("folders/%s/trash");
    /**
     * File Info URL Template.
     */
    public static final URLTemplate FILE_INFO_URL_TEMPLATE = new URLTemplate("files/%s/trash");
    /**
     * Restore File URL Template.
     */
    public static final URLTemplate RESTORE_FILE_URL_TEMPLATE = new URLTemplate("files/%s");
    /**
     * Restore Folder URL Template.
     */
    public static final URLTemplate RESTORE_FOLDER_URL_TEMPLATE = new URLTemplate("folders/%s");

    private static final long LIMIT = 1000;
    private final BoxAPIConnection api;

    /**
     * Constructs a BoxTrash using a given API connection.
     * @param  api the API connection to be used by the trash.
     */
    public BoxTrash(BoxAPIConnection api) {
        this.api = api;
    }

    /**
     * Permanently deletes a trashed folder.
     * @param folderID the ID of the trashed folder to permanently delete.
     */
    public void deleteFolder(String folderID) {
        URL url = FOLDER_INFO_URL_TEMPLATE.build(this.api.getBaseURL(), folderID);
        BoxAPIRequest request = new BoxAPIRequest(this.api, url, "DELETE");
        BoxAPIResponse response = request.send();
        response.disconnect();
    }

    /**
     * Gets information about a trashed folder.
     * @param  folderID the ID of the trashed folder.
     * @return          info about the trashed folder.
     */
    public BoxFolder.Info getFolderInfo(String folderID) {
        URL url = FOLDER_INFO_URL_TEMPLATE.build(this.api.getBaseURL(), folderID);
        BoxAPIRequest request = new BoxAPIRequest(this.api, url, "GET");
        BoxJSONResponse response = (BoxJSONResponse) request.send();
        JsonObject jsonObject = JsonObject.readFrom(response.getJSON());

        BoxFolder folder = new BoxFolder(this.api, jsonObject.get("id").asString());
        return folder.new Info(response.getJSON());
    }

    /**
     * Gets information about a trashed folder that's limited to a list of specified fields.
     * @param  folderID the ID of the trashed folder.
     * @param  fields   the fields to retrieve.
     * @return          info about the trashed folder containing only the specified fields.
     */
    public BoxFolder.Info getFolderInfo(String folderID, String... fields) {
        String queryString = new QueryStringBuilder().appendParam("fields", fields).toString();
        URL url = FOLDER_INFO_URL_TEMPLATE.buildWithQuery(this.api.getBaseURL(), queryString, folderID);
        BoxAPIRequest request = new BoxAPIRequest(this.api, url, "GET");
        BoxJSONResponse response = (BoxJSONResponse) request.send();
        JsonObject jsonObject = JsonObject.readFrom(response.getJSON());

        BoxFolder folder = new BoxFolder(this.api, jsonObject.get("id").asString());
        return folder.new Info(response.getJSON());
    }

    /**
     * Restores a trashed folder back to its original location.
     * @param  folderID the ID of the trashed folder.
     * @return          info about the restored folder.
     */
    public BoxFolder.Info restoreFolder(String folderID) {
        URL url = RESTORE_FOLDER_URL_TEMPLATE.build(this.api.getBaseURL(), folderID);
        BoxAPIRequest request = new BoxAPIRequest(this.api, url, "POST");
        JsonObject requestJSON = new JsonObject()
                .add("", "");
        request.setBody(requestJSON.toString());
        BoxJSONResponse response = (BoxJSONResponse) request.send();
        JsonObject responseJSON = JsonObject.readFrom(response.getJSON());

        BoxFolder restoredFolder = new BoxFolder(this.api, responseJSON.get("id").asString());
        return restoredFolder.new Info(responseJSON);
    }

    /**
     * Restores a trashed folder to a new location with a new name.
     * @param  folderID    the ID of the trashed folder.
     * @param  newName     an optional new name to give the folder. This can be null to use the folder's original name.
     * @param  newParentID an optional new parent ID for the folder. This can be null to use the folder's original
     *                     parent.
     * @return             info about the restored folder.
     */
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

    /**
     * Permanently deletes a trashed file.
     * @param fileID the ID of the trashed folder to permanently delete.
     */
    public void deleteFile(String fileID) {
        URL url = FILE_INFO_URL_TEMPLATE.build(this.api.getBaseURL(), fileID);
        BoxAPIRequest request = new BoxAPIRequest(this.api, url, "DELETE");
        BoxAPIResponse response = request.send();
        response.disconnect();
    }

    /**
     * Gets information about a trashed file.
     * @param  fileID the ID of the trashed file.
     * @return        info about the trashed file.
     */
    public BoxFile.Info getFileInfo(String fileID) {
        URL url = FILE_INFO_URL_TEMPLATE.build(this.api.getBaseURL(), fileID);
        BoxAPIRequest request = new BoxAPIRequest(this.api, url, "GET");
        BoxJSONResponse response = (BoxJSONResponse) request.send();
        JsonObject jsonObject = JsonObject.readFrom(response.getJSON());

        BoxFile file = new BoxFile(this.api, jsonObject.get("id").asString());
        return file.new Info(response.getJSON());
    }

    /**
     * Gets information about a trashed file that's limited to a list of specified fields.
     * @param  fileID the ID of the trashed file.
     * @param  fields the fields to retrieve.
     * @return        info about the trashed file containing only the specified fields.
     */
    public BoxFile.Info getFileInfo(String fileID, String... fields) {
        String queryString = new QueryStringBuilder().appendParam("fields", fields).toString();
        URL url = FILE_INFO_URL_TEMPLATE.buildWithQuery(this.api.getBaseURL(), queryString, fileID);
        BoxAPIRequest request = new BoxAPIRequest(this.api, url, "GET");
        BoxJSONResponse response = (BoxJSONResponse) request.send();
        JsonObject jsonObject = JsonObject.readFrom(response.getJSON());

        BoxFile file = new BoxFile(this.api, jsonObject.get("id").asString());
        return file.new Info(response.getJSON());
    }

    /**
     * Restores a trashed file back to its original location.
     * @param  fileID the ID of the trashed file.
     * @return        info about the restored file.
     */
    public BoxFile.Info restoreFile(String fileID) {
        URL url = RESTORE_FILE_URL_TEMPLATE.build(this.api.getBaseURL(), fileID);
        BoxAPIRequest request = new BoxAPIRequest(this.api, url, "POST");
        JsonObject requestJSON = new JsonObject()
                .add("", "");
        request.setBody(requestJSON.toString());
        BoxJSONResponse response = (BoxJSONResponse) request.send();
        JsonObject responseJSON = JsonObject.readFrom(response.getJSON());

        BoxFile restoredFile = new BoxFile(this.api, responseJSON.get("id").asString());
        return restoredFile.new Info(responseJSON);
    }

    /**
     * Restores a trashed file to a new location with a new name.
     * @param  fileID      the ID of the trashed file.
     * @param  newName     an optional new name to give the file. This can be null to use the file's original name.
     * @param  newParentID an optional new parent ID for the file. This can be null to use the file's original
     *                     parent.
     * @return             info about the restored file.
     */
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

    /**
     * Returns an iterator over the items in the trash.
     * @return an iterator over the items in the trash.
     */
    public Iterator<BoxItem.Info> iterator() {
        URL url = GET_ITEMS_URL.build(this.api.getBaseURL());
        return new BoxItemIterator(this.api, url);
    }
}
