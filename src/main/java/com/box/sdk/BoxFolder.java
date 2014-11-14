package com.box.sdk;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.EnumSet;
import java.util.Iterator;

import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;

/**
 * Represents a folder on Box. This class can be used to iterate through a folder's contents, collaborate a folder with
 * another user or group, and perform other common folder operations (move, copy, delete, etc.).
 */
public final class BoxFolder extends BoxItem implements Iterable<BoxItem.Info> {
    public static final String[] ALL_FIELDS = {"type", "id", "sequence_id", "etag", "name", "created_at", "modified_at",
        "description", "size", "path_collection", "created_by", "modified_by", "trashed_at", "purged_at",
        "content_created_at", "content_modified_at", "owned_by", "shared_link", "folder_upload_email", "parent",
        "item_status", "item_collection", "sync_state", "has_collaborations", "permissions", "tags",
        "can_non_owners_invite"};

    private static final String UPLOAD_FILE_URL_BASE = "https://upload.box.com/api/2.0/";
    private static final URLTemplate CREATE_FOLDER_URL = new URLTemplate("folders");
    private static final URLTemplate COPY_FOLDER_URL = new URLTemplate("folders/%s/copy");
    private static final URLTemplate DELETE_FOLDER_URL = new URLTemplate("folders/%s?recursive=%b");
    private static final URLTemplate FOLDER_INFO_URL_TEMPLATE = new URLTemplate("folders/%s");
    private static final URLTemplate UPLOAD_FILE_URL = new URLTemplate("files/content");
    private static final URLTemplate ADD_COLLABORATION_URL = new URLTemplate("collaborations");
    private static final URLTemplate GET_COLLABORATIONS_URL = new URLTemplate("folders/%s/collaborations");
    private static final URLTemplate GET_ITEMS_URL = new URLTemplate("folders/%s/items/");
    private static final URLTemplate SEARCH_URL_TEMPLATE = new URLTemplate("search");

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
        String queryString = new QueryStringBuilder().appendParam("fields", fields).toString();
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
     * @return      the created folder's info.
     */
    public BoxFolder.Info createFolder(String name) {
        JsonObject parent = new JsonObject();
        parent.add("id", this.getID());

        JsonObject newFolder = new JsonObject();
        newFolder.add("name", name);
        newFolder.add("parent", parent);

        BoxJSONRequest request = new BoxJSONRequest(this.getAPI(), CREATE_FOLDER_URL.build(this.getAPI().getBaseURL()),
            "POST");
        request.setBody(newFolder.toString());
        BoxJSONResponse response = (BoxJSONResponse) request.send();
        JsonObject responseJSON = JsonObject.readFrom(response.getJSON());

        BoxFolder createdFolder = new BoxFolder(this.getAPI(), responseJSON.get("id").asString());
        return createdFolder.new Info(responseJSON);
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
     * @return             the uploaded file's info.
     */
    public BoxFile.Info uploadFile(InputStream fileContent, String name) {
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
     * @return             the uploaded file's info.
     */
    public BoxFile.Info uploadFile(InputStream fileContent, String name, long fileSize, ProgressListener listener) {
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
     * @return              the uploaded file's info.
     */
    public BoxFile.Info uploadFile(FileUploadParams uploadParams) {
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
        JsonObject fileInfoJSON = entries.get(0).asObject();
        String uploadedFileID = fileInfoJSON.get("id").asString();

        BoxFile uploadedFile = new BoxFile(getAPI(), uploadedFileID);
        return uploadedFile.new Info(fileInfoJSON);
    }

    /**
     * Returns an iterable containing the items in this folder. Iterating over the iterable returned by this method is
     * equivalent to iterating over this BoxFolder directly.
     * @return an iterable containing the items in this folder.
     */
    public Iterable<BoxItem.Info> getChildren() {
        return this;
    }

    /**
     * Returns an iterable containing the items in this folder and specifies which child fields to retrieve from the
     * API.
     * @param  fields the fields to retrieve.
     * @return        an iterable containing the items in this folder.
     */
    public Iterable<BoxItem.Info> getChildren(final String fields) {
        return new Iterable<BoxItem.Info>() {
            public Iterator<BoxItem.Info> iterator() {
                String queryString = new QueryStringBuilder().appendParam("fields", fields).toString();
                URL url = GET_ITEMS_URL.buildWithQuery(getAPI().getBaseURL(), queryString, getID());
                return new BoxItemIterator(getAPI(), url);
            }
        };
    }

    /**
     * Returns an iterator over the items in this folder.
     * @return an iterator over the items in this folder.
     */
    public Iterator<BoxItem.Info> iterator() {
        URL url = GET_ITEMS_URL.build(this.getAPI().getBaseURL(), BoxFolder.this.getID());
        return new BoxItemIterator(BoxFolder.this.getAPI(), url);
    }

    /**
     * Searches this folder and all descendant folders using a given query.
     * @param  query the search query.
     * @return an Iterable containing the search results.
     */
    public Iterable<BoxItem.Info> search(final String query) {
        return new Iterable<BoxItem.Info>() {
            public Iterator<BoxItem.Info> iterator() {
                QueryStringBuilder builder = new QueryStringBuilder();
                builder.appendParam("query", query);
                builder.appendParam("ancestor_folder_ids", getID());

                URL url = SEARCH_URL_TEMPLATE.buildWithQuery(getAPI().getBaseURL(), builder.toString());
                return new BoxItemIterator(getAPI(), url);
            }
        };
    }

    /**
     * Contains additional information about a BoxFolder.
     */
    public class Info extends BoxItem.Info {
        private BoxUploadEmail uploadEmail;
        private boolean hasCollaborations;
        private SyncState syncState;
        private EnumSet<Permission> permissions;
        private boolean canNonOwnersInvite;

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

        public BoxUploadEmail getUploadEmail() {
            return this.uploadEmail;
        }

        public void setUploadEmail(BoxUploadEmail uploadEmail) {
            if (this.uploadEmail == uploadEmail) {
                return;
            }

            this.removeChildObject("folder_upload_email");
            this.uploadEmail = uploadEmail;

            if (uploadEmail == null) {
                this.addPendingChange("folder_upload_email", null);
            } else {
                this.addChildObject("folder_upload_email", uploadEmail);
            }
        }

        public boolean getHasCollaborations() {
            return this.hasCollaborations;
        }

        public SyncState getSyncState() {
            return this.syncState;
        }

        public void setSyncState(SyncState syncState) {
            this.syncState = syncState;
            this.addPendingChange("sync_state", syncState.toJSONValue());
        }

        public EnumSet<Permission> getPermissions() {
            return this.permissions;
        }

        public boolean getCanNonOwnersInvite() {
            return this.canNonOwnersInvite;
        }

        @Override
        public BoxFolder getResource() {
            return BoxFolder.this;
        }

        @Override
        protected void parseJSONMember(JsonObject.Member member) {
            super.parseJSONMember(member);

            String memberName = member.getName();
            JsonValue value = member.getValue();
            switch (memberName) {
                case "folder_upload_email":
                    if (this.uploadEmail == null) {
                        this.uploadEmail = new BoxUploadEmail(value.asObject());
                    } else {
                        this.uploadEmail.update(value.asObject());
                    }
                    break;
                case "has_collaborations":
                    this.hasCollaborations = value.asBoolean();
                    break;
                case "sync_state":
                    this.syncState = SyncState.fromJSONValue(value.asString());
                    break;
                case "permissions":
                    this.permissions = this.parsePermissions(value.asObject());
                    break;
                case "can_non_owners_invite":
                    this.canNonOwnersInvite = value.asBoolean();
                    break;
                default:
                    break;
            }
        }

        private EnumSet<Permission> parsePermissions(JsonObject jsonObject) {
            EnumSet<Permission> permissions = EnumSet.noneOf(Permission.class);
            for (JsonObject.Member member : jsonObject) {
                JsonValue value = member.getValue();
                if (value.isNull() || !value.asBoolean()) {
                    continue;
                }

                String memberName = member.getName();
                switch (memberName) {
                    case "can_download":
                        permissions.add(Permission.CAN_DOWNLOAD);
                        break;
                    case "can_upload":
                        permissions.add(Permission.CAN_UPLOAD);
                        break;
                    case "can_rename":
                        permissions.add(Permission.CAN_RENAME);
                        break;
                    case "can_delete":
                        permissions.add(Permission.CAN_DELETE);
                        break;
                    case "can_share":
                        permissions.add(Permission.CAN_SHARE);
                        break;
                    case "can_invite_collaborator":
                        permissions.add(Permission.CAN_INVITE_COLLABORATOR);
                        break;
                    case "can_set_share_access":
                        permissions.add(Permission.CAN_SET_SHARE_ACCESS);
                        break;
                    default:
                        break;
                }
            }

            return permissions;
        }
    }

    public enum SyncState {
        SYNCED ("synced"),
        NOT_SYNCED ("not_synced"),
        PARTIALLY_SYNCED ("partially_synced");

        private final String jsonValue;

        private SyncState(String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public static SyncState fromJSONValue(String jsonValue) {
            return SyncState.valueOf(jsonValue.toUpperCase());
        }

        public String toJSONValue() {
            return this.jsonValue;
        }
    }

    public enum Permission {
        CAN_DOWNLOAD ("can_download"),
        CAN_UPLOAD ("can_upload"),
        CAN_RENAME ("can_rename"),
        CAN_DELETE ("can_delete"),
        CAN_SHARE ("can_share"),
        CAN_INVITE_COLLABORATOR ("can_invite_collaborator"),
        CAN_SET_SHARE_ACCESS ("can_set_share_access");

        private final String jsonValue;

        private Permission(String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public static Permission fromJSONValue(String jsonValue) {
            return Permission.valueOf(jsonValue.toUpperCase());
        }

        public String toJSONValue() {
            return this.jsonValue;
        }
    }
}
