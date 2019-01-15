package com.box.sdk;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.box.sdk.internal.utils.Parsers;
import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;

/**
 * Represents a folder on Box. This class can be used to iterate through a folder's contents, collaborate a folder with
 * another user or group, and perform other common folder operations (move, copy, delete, etc.).
 * <p>
 * <p>Unless otherwise noted, the methods in this class can throw an unchecked {@link BoxAPIException} (unchecked
 * meaning that the compiler won't force you to handle it) if an error occurs. If you wish to implement custom error
 * handling for errors related to the Box REST API, you should capture this exception explicitly.</p>
 */
@BoxResourceType("folder")
public class BoxFolder extends BoxItem implements Iterable<BoxItem.Info> {
    /**
     * An array of all possible folder fields that can be requested when calling {@link #getInfo()}.
     */
    public static final String[] ALL_FIELDS = {"type", "id", "sequence_id", "etag", "name", "created_at", "modified_at",
        "description", "size", "path_collection", "created_by", "modified_by", "trashed_at", "purged_at",
        "content_created_at", "content_modified_at", "owned_by", "shared_link", "folder_upload_email", "parent",
        "item_status", "item_collection", "sync_state", "has_collaborations", "permissions", "tags",
        "can_non_owners_invite", "collections", "watermark_info", "metadata"};

    /**
     * Create Folder URL Template.
     */
    public static final URLTemplate CREATE_FOLDER_URL = new URLTemplate("folders");
    /**
     * Create Web Link URL Template.
     */
    public static final URLTemplate CREATE_WEB_LINK_URL = new URLTemplate("web_links");
    /**
     * Copy Folder URL Template.
     */
    public static final URLTemplate COPY_FOLDER_URL = new URLTemplate("folders/%s/copy");
    /**
     * Delete Folder URL Template.
     */
    public static final URLTemplate DELETE_FOLDER_URL = new URLTemplate("folders/%s?recursive=%b");
    /**
     * Folder Info URL Template.
     */
    public static final URLTemplate FOLDER_INFO_URL_TEMPLATE = new URLTemplate("folders/%s");
    /**
     * Upload File URL Template.
     */
    public static final URLTemplate UPLOAD_FILE_URL = new URLTemplate("files/content");
    /**
     * Add Collaboration URL Template.
     */
    public static final URLTemplate ADD_COLLABORATION_URL = new URLTemplate("collaborations");
    /**
     * Get Collaborations URL Template.
     */
    public static final URLTemplate GET_COLLABORATIONS_URL = new URLTemplate("folders/%s/collaborations");
    /**
     * Get Items URL Template.
     */
    public static final URLTemplate GET_ITEMS_URL = new URLTemplate("folders/%s/items/");
    /**
     * Search URL Template.
     */
    public static final URLTemplate SEARCH_URL_TEMPLATE = new URLTemplate("search");
    /**
     * Metadata URL Template.
     */
    public static final URLTemplate METADATA_URL_TEMPLATE = new URLTemplate("folders/%s/metadata/%s/%s");
    /**
     * Upload Session URL Template.
     */
    public static final URLTemplate UPLOAD_SESSION_URL_TEMPLATE = new URLTemplate("files/upload_sessions");

    /**
     * Constructs a BoxFolder for a folder with a given ID.
     *
     * @param api the API connection to be used by the folder.
     * @param id  the ID of the folder.
     */
    public BoxFolder(BoxAPIConnection api, String id) {
        super(api, id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected URL getItemURL() {
        return FOLDER_INFO_URL_TEMPLATE.build(this.getAPI().getBaseURL(), this.getID());
    }

    /**
     * Gets the current user's root folder.
     *
     * @param api the API connection to be used by the folder.
     * @return the user's root folder.
     */
    public static BoxFolder getRootFolder(BoxAPIConnection api) {
        return new BoxFolder(api, "0");
    }

    /**
     * Adds a collaborator to this folder.
     *
     * @param collaborator the collaborator to add.
     * @param role         the role of the collaborator.
     * @return info about the new collaboration.
     */
    public BoxCollaboration.Info collaborate(BoxCollaborator collaborator, BoxCollaboration.Role role) {
        JsonObject accessibleByField = new JsonObject();
        accessibleByField.add("id", collaborator.getID());

        if (collaborator instanceof BoxUser) {
            accessibleByField.add("type", "user");
        } else if (collaborator instanceof BoxGroup) {
            accessibleByField.add("type", "group");
        } else {
            throw new IllegalArgumentException("The given collaborator is of an unknown type.");
        }

        return this.collaborate(accessibleByField, role, null, null);
    }

    /**
     * Adds a collaborator to this folder. An email will be sent to the collaborator if they don't already have a Box
     * account.
     *
     * @param email the email address of the collaborator to add.
     * @param role  the role of the collaborator.
     * @return info about the new collaboration.
     */
    public BoxCollaboration.Info collaborate(String email, BoxCollaboration.Role role) {
        JsonObject accessibleByField = new JsonObject();
        accessibleByField.add("login", email);
        accessibleByField.add("type", "user");

        return this.collaborate(accessibleByField, role, null, null);
    }

    /**
     * Adds a collaborator to this folder.
     *
     * @param collaborator the collaborator to add.
     * @param role         the role of the collaborator.
     * @param notify       the user/group should receive email notification of the collaboration or not.
     * @param canViewPath  the view path collaboration feature is enabled or not.
     *                     View path collaborations allow the invitee to see the entire ancestral path to the associated
     *                     folder. The user will not gain privileges in any ancestral folder.
     * @return info about the new collaboration.
     */
    public BoxCollaboration.Info collaborate(BoxCollaborator collaborator, BoxCollaboration.Role role,
                                             Boolean notify, Boolean canViewPath) {
        JsonObject accessibleByField = new JsonObject();
        accessibleByField.add("id", collaborator.getID());

        if (collaborator instanceof BoxUser) {
            accessibleByField.add("type", "user");
        } else if (collaborator instanceof BoxGroup) {
            accessibleByField.add("type", "group");
        } else {
            throw new IllegalArgumentException("The given collaborator is of an unknown type.");
        }

        return this.collaborate(accessibleByField, role, notify, canViewPath);
    }

    /**
     * Adds a collaborator to this folder. An email will be sent to the collaborator if they don't already have a Box
     * account.
     *
     * @param email       the email address of the collaborator to add.
     * @param role        the role of the collaborator.
     * @param notify      the user/group should receive email notification of the collaboration or not.
     * @param canViewPath the view path collaboration feature is enabled or not.
     *                    View path collaborations allow the invitee to see the entire ancestral path to the associated
     *                    folder. The user will not gain privileges in any ancestral folder.
     * @return info about the new collaboration.
     */
    public BoxCollaboration.Info collaborate(String email, BoxCollaboration.Role role,
                                             Boolean notify, Boolean canViewPath) {
        JsonObject accessibleByField = new JsonObject();
        accessibleByField.add("login", email);
        accessibleByField.add("type", "user");

        return this.collaborate(accessibleByField, role, notify, canViewPath);
    }

    private BoxCollaboration.Info collaborate(JsonObject accessibleByField, BoxCollaboration.Role role,
                                              Boolean notify, Boolean canViewPath) {

        JsonObject itemField = new JsonObject();
        itemField.add("id", this.getID());
        itemField.add("type", "folder");

        return BoxCollaboration.create(this.getAPI(), accessibleByField, itemField, role, notify, canViewPath);
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
     * Creates new SharedLink for a BoxFolder with a password.
     *
     * @param access        The access level of the shared link.
     * @param unshareDate   A specified date to unshare the Box folder.
     * @param permissions   The permissions to set on the shared link for the Box folder.
     * @param password      Password set on the shared link to give access to the Box folder.
     * @return information about the newly created shared link.
     */
    public BoxSharedLink createSharedLink(BoxSharedLink.Access access, Date unshareDate,
                                          BoxSharedLink.Permissions permissions, String password) {

        BoxSharedLink sharedLink = new BoxSharedLink(access, unshareDate, permissions, password);
        Info info = new Info();
        info.setSharedLink(sharedLink);

        this.updateInfo(info);
        return info.getSharedLink();
    }

    /**
     * Gets information about all of the collaborations for this folder.
     *
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
        URL url = FOLDER_INFO_URL_TEMPLATE.build(this.getAPI().getBaseURL(), this.getID());
        BoxAPIRequest request = new BoxAPIRequest(this.getAPI(), url, "GET");
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
     *
     * @param info the updated info.
     */
    public void updateInfo(BoxFolder.Info info) {
        URL url = FOLDER_INFO_URL_TEMPLATE.build(this.getAPI().getBaseURL(), this.getID());
        BoxJSONRequest request = new BoxJSONRequest(this.getAPI(), url, "PUT");
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
     *
     * @param name the new folder's name.
     * @return the created folder's info.
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
     *
     * @param recursive true to recursively delete this folder's contents; otherwise false.
     */
    public void delete(boolean recursive) {
        URL url = DELETE_FOLDER_URL.build(this.getAPI().getBaseURL(), this.getID(), recursive);
        BoxAPIRequest request = new BoxAPIRequest(this.getAPI(), url, "DELETE");
        BoxAPIResponse response = request.send();
        response.disconnect();
    }

    @Override
    public BoxItem.Info move(BoxFolder destination) {
        return this.move(destination, null);
    }

    @Override
    public BoxItem.Info move(BoxFolder destination, String newName) {
        URL url = FOLDER_INFO_URL_TEMPLATE.build(this.getAPI().getBaseURL(), this.getID());
        BoxJSONRequest request = new BoxJSONRequest(this.getAPI(), url, "PUT");

        JsonObject parent = new JsonObject();
        parent.add("id", destination.getID());

        JsonObject updateInfo = new JsonObject();
        updateInfo.add("parent", parent);
        if (newName != null) {
            updateInfo.add("name", newName);
        }

        request.setBody(updateInfo.toString());
        BoxJSONResponse response = (BoxJSONResponse) request.send();
        JsonObject responseJSON = JsonObject.readFrom(response.getJSON());
        BoxFolder movedFolder = new BoxFolder(this.getAPI(), responseJSON.get("id").asString());
        return movedFolder.new Info(responseJSON);
    }

    /**
     * Renames this folder.
     *
     * @param newName the new name of the folder.
     */
    public void rename(String newName) {
        URL url = FOLDER_INFO_URL_TEMPLATE.build(this.getAPI().getBaseURL(), this.getID());
        BoxJSONRequest request = new BoxJSONRequest(this.getAPI(), url, "PUT");

        JsonObject updateInfo = new JsonObject();
        updateInfo.add("name", newName);

        request.setBody(updateInfo.toString());
        BoxJSONResponse response = (BoxJSONResponse) request.send();
        response.getJSON();
    }

    /**
     * Checks if the file can be successfully uploaded by using the preflight check.
     *
     * @param name     the name to give the uploaded file.
     * @param fileSize the size of the file used for account capacity calculations.
     */
    public void canUpload(String name, long fileSize) {
        URL url = UPLOAD_FILE_URL.build(this.getAPI().getBaseURL());
        BoxJSONRequest request = new BoxJSONRequest(this.getAPI(), url, "OPTIONS");

        JsonObject parent = new JsonObject();
        parent.add("id", this.getID());

        JsonObject preflightInfo = new JsonObject();
        preflightInfo.add("parent", parent);
        preflightInfo.add("name", name);

        preflightInfo.add("size", fileSize);

        request.setBody(preflightInfo.toString());
        BoxAPIResponse response = request.send();
        response.disconnect();
    }

    /**
     * Uploads a new file to this folder.
     *
     * @param fileContent a stream containing the contents of the file to upload.
     * @param name        the name to give the uploaded file.
     * @return the uploaded file's info.
     */
    public BoxFile.Info uploadFile(InputStream fileContent, String name) {
        FileUploadParams uploadInfo = new FileUploadParams()
                .setContent(fileContent)
                .setName(name);
        return this.uploadFile(uploadInfo);
    }

    /**
     * Uploads a new file to this folder.
     *
     * @param callback the callback which allows file content to be written on output stream.
     * @param name     the name to give the uploaded file.
     * @return the uploaded file's info.
     */
    public BoxFile.Info uploadFile(UploadFileCallback callback, String name) {
        FileUploadParams uploadInfo = new FileUploadParams()
                .setUploadFileCallback(callback)
                .setName(name);
        return this.uploadFile(uploadInfo);
    }

    /**
     * Uploads a new file to this folder while reporting the progress to a ProgressListener.
     *
     * @param fileContent a stream containing the contents of the file to upload.
     * @param name        the name to give the uploaded file.
     * @param fileSize    the size of the file used for determining the progress of the upload.
     * @param listener    a listener for monitoring the upload's progress.
     * @return the uploaded file's info.
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
     *
     * @param uploadParams the custom upload parameters.
     * @return the uploaded file's info.
     */
    public BoxFile.Info uploadFile(FileUploadParams uploadParams) {
        URL uploadURL = UPLOAD_FILE_URL.build(this.getAPI().getBaseUploadURL());
        BoxMultipartRequest request = new BoxMultipartRequest(getAPI(), uploadURL);

        JsonObject fieldJSON = new JsonObject();
        JsonObject parentIdJSON = new JsonObject();
        parentIdJSON.add("id", getID());
        fieldJSON.add("name", uploadParams.getName());
        fieldJSON.add("parent", parentIdJSON);

        if (uploadParams.getCreated() != null) {
            fieldJSON.add("content_created_at", BoxDateFormat.format(uploadParams.getCreated()));
        }

        if (uploadParams.getModified() != null) {
            fieldJSON.add("content_modified_at", BoxDateFormat.format(uploadParams.getModified()));
        }

        if (uploadParams.getSHA1() != null && !uploadParams.getSHA1().isEmpty()) {
            request.setContentSHA1(uploadParams.getSHA1());
        }

        request.putField("attributes", fieldJSON.toString());

        if (uploadParams.getSize() > 0) {
            request.setFile(uploadParams.getContent(), uploadParams.getName(), uploadParams.getSize());
        } else if (uploadParams.getContent() != null) {
            request.setFile(uploadParams.getContent(), uploadParams.getName());
        } else {
            request.setUploadFileCallback(uploadParams.getUploadFileCallback(), uploadParams.getName());
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
     * Uploads a new weblink to this folder.
     *
     * @param linkURL the URL the weblink points to.
     * @return the uploaded weblink's info.
     */
    public BoxWebLink.Info createWebLink(URL linkURL) {
        return this.createWebLink(null, linkURL,
                null);
    }

    /**
     * Uploads a new weblink to this folder.
     *
     * @param name    the filename for the weblink.
     * @param linkURL the URL the weblink points to.
     * @return the uploaded weblink's info.
     */
    public BoxWebLink.Info createWebLink(String name, URL linkURL) {
        return this.createWebLink(name, linkURL,
                null);
    }

    /**
     * Uploads a new weblink to this folder.
     *
     * @param linkURL     the URL the weblink points to.
     * @param description the weblink's description.
     * @return the uploaded weblink's info.
     */
    public BoxWebLink.Info createWebLink(URL linkURL, String description) {
        return this.createWebLink(null, linkURL, description);
    }

    /**
     * Uploads a new weblink to this folder.
     *
     * @param name        the filename for the weblink.
     * @param linkURL     the URL the weblink points to.
     * @param description the weblink's description.
     * @return the uploaded weblink's info.
     */
    public BoxWebLink.Info createWebLink(String name, URL linkURL, String description) {
        JsonObject parent = new JsonObject();
        parent.add("id", this.getID());

        JsonObject newWebLink = new JsonObject();
        newWebLink.add("name", name);
        newWebLink.add("parent", parent);
        newWebLink.add("url", linkURL.toString());

        if (description != null) {
            newWebLink.add("description", description);
        }

        BoxJSONRequest request = new BoxJSONRequest(this.getAPI(),
                CREATE_WEB_LINK_URL.build(this.getAPI().getBaseURL()), "POST");
        request.setBody(newWebLink.toString());
        BoxJSONResponse response = (BoxJSONResponse) request.send();
        JsonObject responseJSON = JsonObject.readFrom(response.getJSON());

        BoxWebLink createdWebLink = new BoxWebLink(this.getAPI(), responseJSON.get("id").asString());
        return createdWebLink.new Info(responseJSON);
    }

    /**
     * Returns an iterable containing the items in this folder. Iterating over the iterable returned by this method is
     * equivalent to iterating over this BoxFolder directly.
     *
     * @return an iterable containing the items in this folder.
     */
    public Iterable<BoxItem.Info> getChildren() {
        return this;
    }

    /**
     * Returns an iterable containing the items in this folder and specifies which child fields to retrieve from the
     * API.
     *
     * @param fields the fields to retrieve.
     * @return an iterable containing the items in this folder.
     */
    public Iterable<BoxItem.Info> getChildren(final String... fields) {
        return new Iterable<BoxItem.Info>() {
            @Override
            public Iterator<BoxItem.Info> iterator() {
                String queryString = new QueryStringBuilder().appendParam("fields", fields).toString();
                URL url = GET_ITEMS_URL.buildWithQuery(getAPI().getBaseURL(), queryString, getID());
                return new BoxItemIterator(getAPI(), url);
            }
        };
    }

    /**
     * Retrieves a specific range of child items in this folder.
     *
     * @param offset the index of the first child item to retrieve.
     * @param limit  the maximum number of children to retrieve after the offset.
     * @param fields the fields to retrieve.
     * @return a partial collection containing the specified range of child items.
     */
    public PartialCollection<BoxItem.Info> getChildrenRange(long offset, long limit, String... fields) {
        QueryStringBuilder builder = new QueryStringBuilder()
                .appendParam("limit", limit)
                .appendParam("offset", offset);

        if (fields.length > 0) {
            builder.appendParam("fields", fields).toString();
        }

        URL url = GET_ITEMS_URL.buildWithQuery(getAPI().getBaseURL(), builder.toString(), getID());
        BoxAPIRequest request = new BoxAPIRequest(this.getAPI(), url, "GET");
        BoxJSONResponse response = (BoxJSONResponse) request.send();
        JsonObject responseJSON = JsonObject.readFrom(response.getJSON());

        String totalCountString = responseJSON.get("total_count").toString();
        long fullSize = Double.valueOf(totalCountString).longValue();
        PartialCollection<BoxItem.Info> children = new PartialCollection<BoxItem.Info>(offset, limit, fullSize);
        JsonArray jsonArray = responseJSON.get("entries").asArray();
        for (JsonValue value : jsonArray) {
            JsonObject jsonObject = value.asObject();
            BoxItem.Info parsedItemInfo = (BoxItem.Info) BoxResource.parseInfo(this.getAPI(), jsonObject);
            if (parsedItemInfo != null) {
                children.add(parsedItemInfo);
            }
        }
        return children;
    }

    /**
     * Returns an iterator over the items in this folder.
     *
     * @return an iterator over the items in this folder.
     */
    @Override
    public Iterator<BoxItem.Info> iterator() {
        URL url = GET_ITEMS_URL.build(this.getAPI().getBaseURL(), BoxFolder.this.getID());
        return new BoxItemIterator(BoxFolder.this.getAPI(), url);
    }

    /**
     * Adds new {@link BoxWebHook} to this {@link BoxFolder}.
     *
     * @param address  {@link BoxWebHook.Info#getAddress()}
     * @param triggers {@link BoxWebHook.Info#getTriggers()}
     * @return created {@link BoxWebHook.Info}
     */
    public BoxWebHook.Info addWebHook(URL address, BoxWebHook.Trigger... triggers) {
        return BoxWebHook.create(this, address, triggers);
    }

    /**
     * Used to retrieve the watermark for the folder.
     * If the folder does not have a watermark applied to it, a 404 Not Found will be returned by API.
     *
     * @param fields the fields to retrieve.
     * @return the watermark associated with the folder.
     */
    public BoxWatermark getWatermark(String... fields) {
        return this.getWatermark(FOLDER_INFO_URL_TEMPLATE, fields);
    }

    /**
     * Used to apply or update the watermark for the folder.
     *
     * @return the watermark associated with the folder.
     */
    public BoxWatermark applyWatermark() {
        return this.applyWatermark(FOLDER_INFO_URL_TEMPLATE, BoxWatermark.WATERMARK_DEFAULT_IMPRINT);
    }

    /**
     * Removes a watermark from the folder.
     * If the folder did not have a watermark applied to it, a 404 Not Found will be returned by API.
     */
    public void removeWatermark() {
        this.removeWatermark(FOLDER_INFO_URL_TEMPLATE);
    }

    /**
     * Used to retrieve all metadata associated with the folder.
     *
     * @param fields the optional fields to retrieve.
     * @return An iterable of metadata instances associated with the folder
     */
    public Iterable<Metadata> getAllMetadata(String... fields) {
        return Metadata.getAllMetadata(this, fields);
    }

    /**
     * This method is deprecated, please use the {@link BoxSearch} class instead.
     * Searches this folder and all descendant folders using a given queryPlease use BoxSearch Instead.
     *
     * @param query the search query.
     * @return an Iterable containing the search results.
     */
    @Deprecated
    public Iterable<BoxItem.Info> search(final String query) {
        return new Iterable<BoxItem.Info>() {
            @Override
            public Iterator<BoxItem.Info> iterator() {
                QueryStringBuilder builder = new QueryStringBuilder();
                builder.appendParam("query", query);
                builder.appendParam("ancestor_folder_ids", getID());

                URL url = SEARCH_URL_TEMPLATE.buildWithQuery(getAPI().getBaseURL(), builder.toString());
                return new BoxItemIterator(getAPI(), url);
            }
        };
    }

    @Override
    public BoxFolder.Info setCollections(BoxCollection... collections) {
        JsonArray jsonArray = new JsonArray();
        for (BoxCollection collection : collections) {
            JsonObject collectionJSON = new JsonObject();
            collectionJSON.add("id", collection.getID());
            jsonArray.add(collectionJSON);
        }
        JsonObject infoJSON = new JsonObject();
        infoJSON.add("collections", jsonArray);

        String queryString = new QueryStringBuilder().appendParam("fields", ALL_FIELDS).toString();
        URL url = FOLDER_INFO_URL_TEMPLATE.buildWithQuery(this.getAPI().getBaseURL(), queryString, this.getID());
        BoxJSONRequest request = new BoxJSONRequest(this.getAPI(), url, "PUT");
        request.setBody(infoJSON.toString());
        BoxJSONResponse response = (BoxJSONResponse) request.send();
        JsonObject jsonObject = JsonObject.readFrom(response.getJSON());
        return new Info(jsonObject);
    }

    /**
     * Creates global property metadata on this folder.
     *
     * @param metadata the new metadata values.
     * @return the metadata returned from the server.
     */
    public Metadata createMetadata(Metadata metadata) {
        return this.createMetadata(Metadata.DEFAULT_METADATA_TYPE, metadata);
    }

    /**
     * Creates metadata on this folder using a specified template.
     *
     * @param templateName the name of the metadata template.
     * @param metadata     the new metadata values.
     * @return the metadata returned from the server.
     */
    public Metadata createMetadata(String templateName, Metadata metadata) {
        String scope = Metadata.scopeBasedOnType(templateName);
        return this.createMetadata(templateName, scope, metadata);
    }

    /**
     * Creates metadata on this folder using a specified scope and template.
     *
     * @param templateName the name of the metadata template.
     * @param scope        the scope of the template (usually "global" or "enterprise").
     * @param metadata     the new metadata values.
     * @return the metadata returned from the server.
     */
    public Metadata createMetadata(String templateName, String scope, Metadata metadata) {
        URL url = METADATA_URL_TEMPLATE.build(this.getAPI().getBaseURL(), this.getID(), scope, templateName);
        BoxAPIRequest request = new BoxAPIRequest(this.getAPI(), url, "POST");
        request.addHeader("Content-Type", "application/json");
        request.setBody(metadata.toString());
        BoxJSONResponse response = (BoxJSONResponse) request.send();
        return new Metadata(JsonObject.readFrom(response.getJSON()));
    }

    /**
     * Gets the global properties metadata on this folder.
     *
     * @return the metadata returned from the server.
     */
    public Metadata getMetadata() {
        return this.getMetadata(Metadata.DEFAULT_METADATA_TYPE);
    }

    /**
     * Gets the metadata on this folder associated with a specified template.
     *
     * @param templateName the metadata template type name.
     * @return the metadata returned from the server.
     */
    public Metadata getMetadata(String templateName) {
        String scope = Metadata.scopeBasedOnType(templateName);
        return this.getMetadata(templateName, scope);
    }

    /**
     * Gets the metadata on this folder associated with a specified scope and template.
     *
     * @param templateName the metadata template type name.
     * @param scope        the scope of the template (usually "global" or "enterprise").
     * @return the metadata returned from the server.
     */
    public Metadata getMetadata(String templateName, String scope) {
        URL url = METADATA_URL_TEMPLATE.build(this.getAPI().getBaseURL(), this.getID(), scope, templateName);
        BoxAPIRequest request = new BoxAPIRequest(this.getAPI(), url, "GET");
        BoxJSONResponse response = (BoxJSONResponse) request.send();
        return new Metadata(JsonObject.readFrom(response.getJSON()));
    }

    /**
     * Updates the global properties metadata on this folder.
     *
     * @param metadata the new metadata values.
     * @return the metadata returned from the server.
     */
    public Metadata updateMetadata(Metadata metadata) {
        URL url = METADATA_URL_TEMPLATE.build(this.getAPI().getBaseURL(), this.getID(), metadata.getScope(),
                metadata.getTemplateName());
        BoxAPIRequest request = new BoxAPIRequest(this.getAPI(), url, "PUT");
        request.addHeader("Content-Type", "application/json-patch+json");
        request.setBody(metadata.getPatch());
        BoxJSONResponse response = (BoxJSONResponse) request.send();
        return new Metadata(JsonObject.readFrom(response.getJSON()));
    }

    /**
     * Deletes the global properties metadata on this folder.
     */
    public void deleteMetadata() {
        this.deleteMetadata(Metadata.DEFAULT_METADATA_TYPE);
    }

    /**
     * Deletes the metadata on this folder associated with a specified template.
     *
     * @param templateName the metadata template type name.
     */
    public void deleteMetadata(String templateName) {
        String scope = Metadata.scopeBasedOnType(templateName);
        this.deleteMetadata(templateName, scope);
    }

    /**
     * Deletes the metadata on this folder associated with a specified scope and template.
     *
     * @param templateName the metadata template type name.
     * @param scope        the scope of the template (usually "global" or "enterprise").
     */
    public void deleteMetadata(String templateName, String scope) {
        URL url = METADATA_URL_TEMPLATE.build(this.getAPI().getBaseURL(), this.getID(), scope, templateName);
        BoxAPIRequest request = new BoxAPIRequest(this.getAPI(), url, "DELETE");
        BoxAPIResponse response = request.send();
        response.disconnect();
    }

    /**
     * Creates an upload session to create a new file in chunks.
     * This will first verify that the file can be created and then open a session for uploading pieces of the file.
     *
     * @param fileName the name of the file to be created
     * @param fileSize the size of the file that will be uploaded
     * @return the created upload session instance
     */
    public BoxFileUploadSession.Info createUploadSession(String fileName, long fileSize) {

        URL url = UPLOAD_SESSION_URL_TEMPLATE.build(this.getAPI().getBaseUploadURL());
        BoxJSONRequest request = new BoxJSONRequest(this.getAPI(), url, "POST");

        JsonObject body = new JsonObject();
        body.add("folder_id", this.getID());
        body.add("file_name", fileName);
        body.add("file_size", fileSize);
        request.setBody(body.toString());

        BoxJSONResponse response = (BoxJSONResponse) request.send();
        JsonObject jsonObject = JsonObject.readFrom(response.getJSON());

        String sessionId = jsonObject.get("id").asString();
        BoxFileUploadSession session = new BoxFileUploadSession(this.getAPI(), sessionId);

        return session.new Info(jsonObject);
    }

    /**
     * Creates a new file.
     *
     * @param inputStream the stream instance that contains the data.
     * @param fileName    the name of the file to be created.
     * @param fileSize    the size of the file that will be uploaded.
     * @return the created file instance.
     * @throws InterruptedException when a thread execution is interrupted.
     * @throws IOException          when reading a stream throws exception.
     */
    public BoxFile.Info uploadLargeFile(InputStream inputStream, String fileName, long fileSize)
            throws InterruptedException, IOException {
        URL url = UPLOAD_SESSION_URL_TEMPLATE.build(this.getAPI().getBaseUploadURL());
        return new LargeFileUpload().
                upload(this.getAPI(), this.getID(), inputStream, url, fileName, fileSize);
    }

    /**
     * Creates a new file using specified number of parallel http connections.
     *
     * @param inputStream          the stream instance that contains the data.
     * @param fileName             the name of the file to be created.
     * @param fileSize             the size of the file that will be uploaded.
     * @param nParallelConnections number of parallel http connections to use
     * @param timeOut              time to wait before killing the job
     * @param unit                 time unit for the time wait value
     * @return the created file instance.
     * @throws InterruptedException when a thread execution is interrupted.
     * @throws IOException          when reading a stream throws exception.
     */
    public BoxFile.Info uploadLargeFile(InputStream inputStream, String fileName, long fileSize,
                                        int nParallelConnections, long timeOut, TimeUnit unit)
            throws InterruptedException, IOException {
        URL url = UPLOAD_SESSION_URL_TEMPLATE.build(this.getAPI().getBaseUploadURL());
        return new LargeFileUpload(nParallelConnections, timeOut, unit).
                upload(this.getAPI(), this.getID(), inputStream, url, fileName, fileSize);
    }

    /**
     * Creates a new Metadata Cascade Policy on a folder.
     *
     * @param scope         the scope of the metadata cascade policy.
     * @param templateKey   the key of the template.
     * @return  information about the Metadata Cascade Policy.
     */
    public BoxMetadataCascadePolicy.Info addMetadataCascadePolicy(String scope, String templateKey) {

        return BoxMetadataCascadePolicy.create(this.getAPI(), this.getID(), scope, templateKey);
    }

    /**
     * Retrieves all Metadata Cascade Policies on a folder.
     *
     * @param fields            optional fields to retrieve for cascade policies.
     * @return  the Iterable of Box Metadata Cascade Policies in your enterprise.
     */
    public Iterable<BoxMetadataCascadePolicy.Info> getMetadataCascadePolicies(String... fields) {
        Iterable<BoxMetadataCascadePolicy.Info> cascadePoliciesInfo =
                BoxMetadataCascadePolicy.getAll(this.getAPI(), this.getID(), fields);

        return cascadePoliciesInfo;
    }

    /**
     * Retrieves all Metadata Cascade Policies on a folder.
     *
     * @param enterpriseID      the ID of the enterprise to retrieve cascade policies for.
     * @param limit             the number of entries of cascade policies to retrieve.
     * @param fields            optional fields to retrieve for cascade policies.
     * @return  the Iterable of Box Metadata Cascade Policies in your enterprise.
     */
    public Iterable<BoxMetadataCascadePolicy.Info> getMetadataCascadePolicies(String enterpriseID,
                                                                      int limit, String... fields) {
        Iterable<BoxMetadataCascadePolicy.Info> cascadePoliciesInfo =
                BoxMetadataCascadePolicy.getAll(this.getAPI(), this.getID(), enterpriseID, limit, fields);

        return cascadePoliciesInfo;
    }

    /**
     * Contains information about a BoxFolder.
     */
    public class Info extends BoxItem.Info {
        private BoxUploadEmail uploadEmail;
        private boolean hasCollaborations;
        private SyncState syncState;
        private EnumSet<Permission> permissions;
        private boolean canNonOwnersInvite;
        private boolean isWatermarked;
        private boolean isCollaborationRestrictedToEnterprise;
        private Map<String, Map<String, Metadata>> metadataMap;

        /**
         * Constructs an empty Info object.
         */
        public Info() {
            super();
        }

        /**
         * Constructs an Info object by parsing information from a JSON string.
         *
         * @param json the JSON string to parse.
         */
        public Info(String json) {
            super(json);
        }

        /**
         * Constructs an Info object using an already parsed JSON object.
         *
         * @param jsonObject the parsed JSON object.
         */
        public Info(JsonObject jsonObject) {
            super(jsonObject);
        }

        /**
         * Gets the upload email for the folder.
         *
         * @return the upload email for the folder.
         */
        public BoxUploadEmail getUploadEmail() {
            return this.uploadEmail;
        }

        /**
         * Sets the upload email for the folder.
         *
         * @param uploadEmail the upload email for the folder.
         */
        public void setUploadEmail(BoxUploadEmail uploadEmail) {
            if (this.uploadEmail == uploadEmail) {
                return;
            }

            this.removeChildObject("folder_upload_email");
            this.uploadEmail = uploadEmail;

            if (uploadEmail == null) {
                this.addPendingChange("folder_upload_email", (String) null);
            } else {
                this.addChildObject("folder_upload_email", uploadEmail);
            }
        }

        /**
         * Gets whether or not the folder has any collaborations.
         *
         * @return true if the folder has collaborations; otherwise false.
         */
        public boolean getHasCollaborations() {
            return this.hasCollaborations;
        }

        /**
         * Gets the sync state of the folder.
         *
         * @return the sync state of the folder.
         */
        public SyncState getSyncState() {
            return this.syncState;
        }

        /**
         * Sets the sync state of the folder.
         *
         * @param syncState the sync state of the folder.
         */
        public void setSyncState(SyncState syncState) {
            this.syncState = syncState;
            this.addPendingChange("sync_state", syncState.toJSONValue());
        }

        /**
         * Gets the permissions that the current user has on the folder.
         *
         * @return the permissions that the current user has on the folder.
         */
        public EnumSet<Permission> getPermissions() {
            return this.permissions;
        }

        /**
         * Gets whether or not the non-owners can invite collaborators to the folder.
         *
         * @return [description]
         */
        public boolean getCanNonOwnersInvite() {
            return this.canNonOwnersInvite;
        }

        /**
         * Gets whether future collaborations should be restricted to within the enterprise only.
         *
         * @return indicates whether collaboration is restricted to enterprise only.
         */
        public boolean getIsCollaborationRestrictedToEnterprise() {
            return this.isCollaborationRestrictedToEnterprise;
        }

        /**
         * Sets whether future collaborations should be restricted to within the enterprise only.
         *
         * @param isRestricted indicates whether there is collaboration restriction within enterprise.
         */
        public void setIsCollaborationRestrictedToEnterprise(boolean isRestricted) {
            this.isCollaborationRestrictedToEnterprise = isRestricted;
            this.addPendingChange("is_collaboration_restricted_to_enterprise", isRestricted);
        }

        /**
         * Gets flag indicating whether this file is Watermarked.
         *
         * @return whether the file is watermarked or not
         */
        public boolean getIsWatermarked() {
            return this.isWatermarked;
        }

        /**
         * Gets the metadata on this folder associated with a specified scope and template.
         * Makes an attempt to get metadata that was retrieved using getInfo(String ...) method. If no result is found
         * then makes an API call to get metadata
         *
         * @param templateName the metadata template type name.
         * @param scope        the scope of the template (usually "global" or "enterprise").
         * @return the metadata returned from the server.
         */
        public Metadata getMetadata(String templateName, String scope) {
            try {
                return this.metadataMap.get(scope).get(templateName);
            } catch (NullPointerException e) {
                return null;
            }
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
            if (memberName.equals("folder_upload_email")) {
                if (this.uploadEmail == null) {
                    this.uploadEmail = new BoxUploadEmail(value.asObject());
                } else {
                    this.uploadEmail.update(value.asObject());
                }

            } else if (memberName.equals("has_collaborations")) {
                this.hasCollaborations = value.asBoolean();

            } else if (memberName.equals("sync_state")) {
                this.syncState = SyncState.fromJSONValue(value.asString());

            } else if (memberName.equals("permissions")) {
                this.permissions = this.parsePermissions(value.asObject());

            } else if (memberName.equals("can_non_owners_invite")) {
                this.canNonOwnersInvite = value.asBoolean();
            } else if (memberName.equals("is_collaboration_restricted_to_enterprise")) {
                this.isCollaborationRestrictedToEnterprise = value.asBoolean();

            } else if (memberName.equals("watermark_info")) {
                JsonObject jsonObject = value.asObject();
                this.isWatermarked = jsonObject.get("is_watermarked").asBoolean();
            } else if (memberName.equals("metadata")) {
                JsonObject jsonObject = value.asObject();
                this.metadataMap = Parsers.parseAndPopulateMetadataMap(jsonObject);
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
                if (memberName.equals("can_download")) {
                    permissions.add(Permission.CAN_DOWNLOAD);
                } else if (memberName.equals("can_upload")) {
                    permissions.add(Permission.CAN_UPLOAD);
                } else if (memberName.equals("can_rename")) {
                    permissions.add(Permission.CAN_RENAME);
                } else if (memberName.equals("can_delete")) {
                    permissions.add(Permission.CAN_DELETE);
                } else if (memberName.equals("can_share")) {
                    permissions.add(Permission.CAN_SHARE);
                } else if (memberName.equals("can_invite_collaborator")) {
                    permissions.add(Permission.CAN_INVITE_COLLABORATOR);
                } else if (memberName.equals("can_set_share_access")) {
                    permissions.add(Permission.CAN_SET_SHARE_ACCESS);
                }
            }

            return permissions;
        }
    }

    /**
     * Enumerates the possible sync states that a folder can have.
     */
    public enum SyncState {
        /**
         * The folder is synced.
         */
        SYNCED("synced"),

        /**
         * The folder is not synced.
         */
        NOT_SYNCED("not_synced"),

        /**
         * The folder is partially synced.
         */
        PARTIALLY_SYNCED("partially_synced");

        private final String jsonValue;

        private SyncState(String jsonValue) {
            this.jsonValue = jsonValue;
        }

        static SyncState fromJSONValue(String jsonValue) {
            return SyncState.valueOf(jsonValue.toUpperCase());
        }

        String toJSONValue() {
            return this.jsonValue;
        }
    }

    /**
     * Enumerates the possible permissions that a user can have on a folder.
     */
    public enum Permission {
        /**
         * The user can download the folder.
         */
        CAN_DOWNLOAD("can_download"),

        /**
         * The user can upload to the folder.
         */
        CAN_UPLOAD("can_upload"),

        /**
         * The user can rename the folder.
         */
        CAN_RENAME("can_rename"),

        /**
         * The user can delete the folder.
         */
        CAN_DELETE("can_delete"),

        /**
         * The user can share the folder.
         */
        CAN_SHARE("can_share"),

        /**
         * The user can invite collaborators to the folder.
         */
        CAN_INVITE_COLLABORATOR("can_invite_collaborator"),

        /**
         * The user can set the access level for shared links to the folder.
         */
        CAN_SET_SHARE_ACCESS("can_set_share_access");

        private final String jsonValue;

        private Permission(String jsonValue) {
            this.jsonValue = jsonValue;
        }

        static Permission fromJSONValue(String jsonValue) {
            return Permission.valueOf(jsonValue.toUpperCase());
        }

        String toJSONValue() {
            return this.jsonValue;
        }
    }
}
