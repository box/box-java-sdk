package com.box.sdk;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;

/**
 * Represents a folder on Box. This class can be used to iterate through a folder's contents, collaborate a folder with
 * another user or group, and perform other common folder operations (move, copy, delete, etc.).
 */
public final class BoxFolder extends BoxItem implements Iterable<BoxItem.Info> {
    private static final String UPLOAD_FILE_URL_BASE = "https://upload.box.com/api/2.0/";
    private static final URLTemplate CREATE_FOLDER_URL = new URLTemplate("folders");
    private static final URLTemplate COPY_FOLDER_URL = new URLTemplate("folders/%s/copy");
    private static final URLTemplate DELETE_FOLDER_URL = new URLTemplate("folders/%s?recursive=%b");
    private static final URLTemplate FOLDER_INFO_URL_TEMPLATE = new URLTemplate("folders/%s");
    private static final URLTemplate UPLOAD_FILE_URL = new URLTemplate("files/content");
    private static final URLTemplate ADD_COLLABORATION_URL = new URLTemplate("collaborations");
    private static final URLTemplate GET_COLLABORATIONS_URL = new URLTemplate("folders/%s/collaborations");

    private final URL folderURL;

    /**
     * Constructs a BoxFolder for a folder with a given ID.
     * @param  api the API connection to be used by the folder.
     * @param  id  the ID of the folder.
     */
    public BoxFolder(BoxAPIConnection api, String id) {
        super(api, id);

        this.folderURL = FOLDER_INFO_URL_TEMPLATE.build(this.getAPI().getBaseURL(), this.getID());
    }

    /**
     * Gets the current user's root folder.
     * @param  api the API connection to be used by the folder.
     * @return     the user's root folder.
     */
    public static BoxFolder getRootFolder(BoxAPIConnection api) {
        return new BoxFolder(api, "0");
    }

    /**
     * Adds a collaborator to this folder.
     * @param  collaborator the collaborator to add.
     * @param  role         the role of the collaborator.
     * @return              info about the new collaboration.
     */
    public BoxCollaboration.Info collaborate(BoxCollaborator collaborator, BoxCollaboration.Role role) {
        JsonObject accessibleByField = new JsonObject();
        accessibleByField.add("id", collaborator.getID());

        if (collaborator instanceof BoxUser) {
            accessibleByField.add("type", "user");
        } else {
            throw new IllegalArgumentException("The given collaborator is of an unknown type.");
        }

        return this.collaborate(accessibleByField, role);
    }

    /**
     * Adds a collaborator to this folder. An email will be sent to the collaborator if they don't already have a Box
     * account.
     * @param  email the email address of the collaborator to add.
     * @param  role  the role of the collaborator.
     * @return       info about the new collaboration.
     */
    public BoxCollaboration.Info collaborate(String email, BoxCollaboration.Role role) {
        JsonObject accessibleByField = new JsonObject();
        accessibleByField.add("login", email);
        accessibleByField.add("type", "user");

        return this.collaborate(accessibleByField, role);
    }

    private BoxCollaboration.Info collaborate(JsonObject accessibleByField, BoxCollaboration.Role role) {
        BoxAPIConnection api = this.getAPI();
        URL url = ADD_COLLABORATION_URL.build(api.getBaseURL());

        JsonObject itemField = new JsonObject();
        itemField.add("id", this.getID());
        itemField.add("type", "folder");

        JsonObject requestJSON = new JsonObject();
        requestJSON.add("item", itemField);
        requestJSON.add("accessible_by", accessibleByField);
        requestJSON.add("role", role.toJSONString());

        BoxJSONRequest request = new BoxJSONRequest(api, url, "POST");
        request.setBody(requestJSON.toString());
        BoxJSONResponse response = (BoxJSONResponse) request.send();
        JsonObject responseJSON = JsonObject.readFrom(response.getJSON());

        BoxCollaboration newCollaboration = new BoxCollaboration(api, responseJSON.get("id").asString());
        BoxCollaboration.Info info = newCollaboration.new Info(responseJSON);
        return info;
    }

    @Override
    public BoxSharedLink createSharedLink(BoxSharedLink.Access access, Date unshareDate,
        BoxSharedLink.Permissions permissions) {

        BoxSharedLink sharedLink = new BoxSharedLink(access, unshareDate, permissions);
        Info info = new Info();
        info.setSharedLink(sharedLink);

        this.updateInfo(info);
        return info.getSharedLink();
    }

    /**
     * Gets information about all of the collaborations for this folder.
     * @return a collection of information about the collaborations for this folder.
     */
    public Collection<BoxCollaboration.Info> getCollaborations() {
        BoxAPIConnection api = this.getAPI();
        URL url = GET_COLLABORATIONS_URL.build(api.getBaseURL(), this.getID());

        BoxAPIRequest request = new BoxAPIRequest(api, url, "GET");
        BoxJSONResponse response = (BoxJSONResponse) request.send();
        JsonObject responseJSON = JsonObject.readFrom(response.getJSON());

        int entriesCount = responseJSON.get("total_count").asInt();
        Collection<BoxCollaboration.Info> collaborations = new ArrayList<BoxCollaboration.Info>(entriesCount);
        JsonArray entries = responseJSON.get("entries").asArray();
        for (JsonValue entry : entries) {
            JsonObject entryObject = entry.asObject();
            BoxCollaboration collaboration = new BoxCollaboration(api, entryObject.get("id").asString());
            BoxCollaboration.Info info = collaboration.new Info(entryObject);
            collaborations.add(info);
        }

        return collaborations;
    }

    @Override
    public BoxFolder.Info getInfo() {
        BoxAPIRequest request = new BoxAPIRequest(this.getAPI(), this.folderURL, "GET");
        BoxJSONResponse response = (BoxJSONResponse) request.send();
        return new Info(response.getJSON());
    }

    @Override
    public BoxFolder.Info getInfo(String... fields) {
        String queryString = new QueryStringBuilder().addFieldsParam(fields).toString();
        URL url = FOLDER_INFO_URL_TEMPLATE.buildWithQuery(this.getAPI().getBaseURL(), queryString, this.getID());

        BoxAPIRequest request = new BoxAPIRequest(this.getAPI(), url, "GET");
        BoxJSONResponse response = (BoxJSONResponse) request.send();
        return new Info(response.getJSON());
    }

    /**
     * Updates the information about this folder with any info fields that have been modified locally.
     * @param info the updated info.
     */
    public void updateInfo(BoxFolder.Info info) {
        BoxJSONRequest request = new BoxJSONRequest(this.getAPI(), this.folderURL, "PUT");
        request.setBody(info.getPendingChanges());
        BoxJSONResponse response = (BoxJSONResponse) request.send();
        JsonObject jsonObject = JsonObject.readFrom(response.getJSON());
        info.update(jsonObject);
    }

    @Override
    public BoxFolder.Info copy(BoxFolder destination) {
        return this.copy(destination, null);
    }

    @Override
    public BoxFolder.Info copy(BoxFolder destination, String newName) {
        URL url = COPY_FOLDER_URL.build(this.getAPI().getBaseURL(), this.getID());
        BoxJSONRequest request = new BoxJSONRequest(this.getAPI(), url, "POST");

        JsonObject parent = new JsonObject();
        parent.add("id", destination.getID());

        JsonObject copyInfo = new JsonObject();
        copyInfo.add("parent", parent);
        if (newName != null) {
            copyInfo.add("name", newName);
        }

        request.setBody(copyInfo.toString());
        BoxJSONResponse response = (BoxJSONResponse) request.send();
        JsonObject responseJSON = JsonObject.readFrom(response.getJSON());
        BoxFolder copiedFolder = new BoxFolder(this.getAPI(), responseJSON.get("id").asString());
        return copiedFolder.new Info(responseJSON);
    }

    /**
     * Creates a new child folder inside this folder.
     * @param  name the new folder's name.
     * @return      the created folder.
     */
    public BoxFolder createFolder(String name) {
        JsonObject parent = new JsonObject();
        parent.add("id", this.getID());

        JsonObject newFolder = new JsonObject();
        newFolder.add("name", name);
        newFolder.add("parent", parent);

        BoxJSONRequest request = new BoxJSONRequest(this.getAPI(), CREATE_FOLDER_URL.build(this.getAPI().getBaseURL()),
            "POST");
        request.setBody(newFolder.toString());
        BoxJSONResponse response = (BoxJSONResponse) request.send();
        JsonObject createdFolder = JsonObject.readFrom(response.getJSON());

        return new BoxFolder(this.getAPI(), createdFolder.get("id").asString());
    }

    /**
     * Deletes this folder, optionally recursively deleting all of its contents.
     * @param recursive true to recursively delete this folder's contents; otherwise false.
     */
    public void delete(boolean recursive) {
        URL url = DELETE_FOLDER_URL.build(this.getAPI().getBaseURL(), this.getID(), recursive);
        BoxAPIRequest request = new BoxAPIRequest(this.getAPI(), url, "DELETE");
        BoxAPIResponse response = request.send();
        response.disconnect();
    }

    /**
     * Moves this folder to another folder.
     * @param destination the destination folder.
     */
    public void move(BoxFolder destination) {
        BoxJSONRequest request = new BoxJSONRequest(this.getAPI(), this.folderURL, "PUT");

        JsonObject parent = new JsonObject();
        parent.add("id", destination.getID());

        JsonObject updateInfo = new JsonObject();
        updateInfo.add("parent", parent);

        request.setBody(updateInfo.toString());
        BoxAPIResponse response = request.send();
        response.disconnect();
    }

    /**
     * Renames this folder.
     * @param newName the new name of the folder.
     */
    public void rename(String newName) {
        BoxJSONRequest request = new BoxJSONRequest(this.getAPI(), this.folderURL, "PUT");

        JsonObject updateInfo = new JsonObject();
        updateInfo.add("name", newName);

        request.setBody(updateInfo.toString());
        BoxAPIResponse response = request.send();
        response.disconnect();
    }

    /**
     * Uploads a new file to this folder.
     * @param  fileContent a stream containing the contents of the file to upload.
     * @param  name        the name to give the uploaded file.
     * @return             the uploaded file.
     */
    public BoxFile uploadFile(InputStream fileContent, String name) {
        FileUploadParams uploadInfo = new FileUploadParams()
            .setContent(fileContent)
            .setName(name);
        return this.uploadFile(uploadInfo);
    }

    /**
     * Uploads a new file to this folder while reporting the progress to a ProgressListener.
     * @param  fileContent a stream containing the contents of the file to upload.
     * @param  name        the name to give the uploaded file.
     * @param  fileSize    the size of the file used for determining the progress of the upload.
     * @param  listener    a listener for monitoring the upload's progress.
     * @return             the uploaded file.
     */
    public BoxFile uploadFile(InputStream fileContent, String name, long fileSize, ProgressListener listener) {
        FileUploadParams uploadInfo = new FileUploadParams()
            .setContent(fileContent)
            .setName(name)
            .setSize(fileSize)
            .setProgressListener(listener);
        return this.uploadFile(uploadInfo);
    }

    /**
     * Uploads a new file to this folder with custom upload parameters.
     * @param  uploadParams the custom upload parameters.
     * @return              the uploaded file.
     */
    public BoxFile uploadFile(FileUploadParams uploadParams) {
        URL uploadURL = UPLOAD_FILE_URL.build(UPLOAD_FILE_URL_BASE);
        BoxMultipartRequest request = new BoxMultipartRequest(getAPI(), uploadURL);
        request.putField("parent_id", getID());

        if (uploadParams.getSize() > 0) {
            request.setFile(uploadParams.getContent(), uploadParams.getName(), uploadParams.getSize());
        } else {
            request.setFile(uploadParams.getContent(), uploadParams.getName());
        }

        if (uploadParams.getCreated() != null) {
            request.putField("content_created_at", uploadParams.getCreated());
        }

        if (uploadParams.getModified() != null) {
            request.putField("content_modified_at", uploadParams.getModified());
        }

        BoxJSONResponse response;
        if (uploadParams.getProgressListener() == null) {
            response = (BoxJSONResponse) request.send();
        } else {
            response = (BoxJSONResponse) request.send(uploadParams.getProgressListener());
        }
        JsonObject collection = JsonObject.readFrom(response.getJSON());
        JsonArray entries = collection.get("entries").asArray();
        String uploadedFileID = entries.get(0).asObject().get("id").asString();

        return new BoxFile(getAPI(), uploadedFileID);
    }

    /**
     * Returns an iterator over the items in this folder.
     * @return an iterator over the items in this folder.
     */
    public Iterator<BoxItem.Info> iterator() {
        return new BoxItemIterator(BoxFolder.this.getAPI(), BoxFolder.this.getID());
    }

    /**
     * Contains additional information about a BoxFolder.
     */
    public class Info extends BoxItem.Info {
        /**
         * Constructs an empty Info object.
         */
        public Info() {
            super();
        }

        /**
         * Constructs an Info object by parsing information from a JSON string.
         * @param  json the JSON string to parse.
         */
        public Info(String json) {
            super(json);
        }

        /**
         * Constructs an Info object using an already parsed JSON object.
         * @param  jsonObject the parsed JSON object.
         */
        protected Info(JsonObject jsonObject) {
            super(jsonObject);
        }

        @Override
        public BoxFolder getResource() {
            return BoxFolder.this;
        }

        @Override
        protected void parseJSONMember(JsonObject.Member member) {
            super.parseJSONMember(member);

            String memberName = member.getName();
            switch (memberName) {
                default:
                    break;
            }
        }
    }
}
