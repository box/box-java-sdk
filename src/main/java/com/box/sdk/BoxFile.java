package com.box.sdk;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.EnumSet;
import java.util.List;

import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;


/**
 * Represents an individual file on Box. This class can be used to download a file's contents, upload new versions, and
 * perform other common file operations (move, copy, delete, etc.).
 *
 * <p>Unless otherwise noted, the methods in this class can throw an unchecked {@link BoxAPIException} (unchecked
 * meaning that the compiler won't force you to handle it) if an error occurs. If you wish to implement custom error
 * handling for errors related to the Box REST API, you should capture this exception explicitly.</p>
 */
@BoxResourceType("file")
public class BoxFile extends BoxItem {

    /**
     * An array of all possible file fields that can be requested when calling {@link #getInfo()}.
     */
    public static final String[] ALL_FIELDS = {"type", "id", "sequence_id", "etag", "sha1", "name",
        "description", "size", "path_collection", "created_at", "modified_at", "trashed_at", "purged_at",
        "content_created_at", "content_modified_at", "created_by", "modified_by", "owned_by", "shared_link", "parent",
        "item_status", "version_number", "comment_count", "permissions", "tags", "lock", "extension", "is_package",
        "file_version"};

    /**
     * Used to specify what filetype to request for a file thumbnail.
     */
    public enum ThumbnailFileType {
        /**
         * PNG image format.
         */
        PNG,

        /**
         * JPG image format.
         */
        JPG
    }

    private static final URLTemplate FILE_URL_TEMPLATE = new URLTemplate("files/%s");
    private static final URLTemplate CONTENT_URL_TEMPLATE = new URLTemplate("files/%s/content");
    private static final URLTemplate VERSIONS_URL_TEMPLATE = new URLTemplate("files/%s/versions");
    private static final URLTemplate COPY_URL_TEMPLATE = new URLTemplate("files/%s/copy");
    private static final URLTemplate ADD_COMMENT_URL_TEMPLATE = new URLTemplate("comments");
    private static final URLTemplate GET_COMMENTS_URL_TEMPLATE = new URLTemplate("files/%s/comments");
    private static final URLTemplate METADATA_URL_TEMPLATE = new URLTemplate("files/%s/metadata/%s/%s");
    private static final URLTemplate ADD_TASK_URL_TEMPLATE = new URLTemplate("tasks");
    private static final URLTemplate GET_TASKS_URL_TEMPLATE = new URLTemplate("files/%s/tasks");
    private static final URLTemplate GET_THUMBNAIL_PNG_TEMPLATE = new URLTemplate("files/%s/thumbnail.png");
    private static final URLTemplate GET_THUMBNAIL_JPG_TEMPLATE = new URLTemplate("files/%s/thumbnail.jpg");
    private static final String DEFAULT_METADATA_TYPE = "properties";
    private static final String GLOBAL_METADATA_SCOPE = "global";
    private static final String ENTERPRISE_METADATA_SCOPE = "enterprise";
    private static final int BUFFER_SIZE = 8192;


    /**
     * Constructs a BoxFile for a file with a given ID.
     * @param  api the API connection to be used by the file.
     * @param  id  the ID of the file.
     */
    public BoxFile(BoxAPIConnection api, String id) {
        super(api, id);
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
     * Adds new {@link BoxWebHook} to this {@link BoxFile}.
     *
     * @param address
     *            {@link BoxWebHook.Info#getAddress()}
     * @param triggers
     *            {@link BoxWebHook.Info#getTriggers()}
     * @return created {@link BoxWebHook.Info}
     */
    public BoxWebHook.Info addWebHook(URL address, BoxWebHook.Trigger... triggers) {
        return BoxWebHook.create(this, address, triggers);
    }

    /**
     * Adds a comment to this file. The message can contain @mentions by using the string @[userid:username] anywhere
     * within the message, where userid and username are the ID and username of the person being mentioned.
     * @see    <a href="https://developers.box.com/docs/#comments-add-a-comment-to-an-item">the tagged_message field
     *         for including @mentions.</a>
     * @param  message the comment's message.
     * @return information about the newly added comment.
     */
    public BoxComment.Info addComment(String message) {
        JsonObject itemJSON = new JsonObject();
        itemJSON.add("type", "file");
        itemJSON.add("id", this.getID());

        JsonObject requestJSON = new JsonObject();
        requestJSON.add("item", itemJSON);
        if (BoxComment.messageContainsMention(message)) {
            requestJSON.add("tagged_message", message);
        } else {
            requestJSON.add("message", message);
        }

        URL url = ADD_COMMENT_URL_TEMPLATE.build(this.getAPI().getBaseURL());
        BoxJSONRequest request = new BoxJSONRequest(this.getAPI(), url, "POST");
        request.setBody(requestJSON.toString());
        BoxJSONResponse response = (BoxJSONResponse) request.send();
        JsonObject responseJSON = JsonObject.readFrom(response.getJSON());

        BoxComment addedComment = new BoxComment(this.getAPI(), responseJSON.get("id").asString());
        return addedComment.new Info(responseJSON);
    }

    /**
     * Adds a new task to this file. The task can have an optional message to include, and a due date.
     * @param action the action the task assignee will be prompted to do.
     * @param message an optional message to include with the task.
     * @param dueAt the day at which this task is due.
     * @return information about the newly added task.
     */
    public BoxTask.Info addTask(BoxTask.Action action, String message, Date dueAt) {
        JsonObject itemJSON = new JsonObject();
        itemJSON.add("type", "file");
        itemJSON.add("id", this.getID());

        JsonObject requestJSON = new JsonObject();
        requestJSON.add("item", itemJSON);
        requestJSON.add("action", action.toJSONString());

        if (message != null && !message.isEmpty()) {
            requestJSON.add("message", message);
        }

        if (dueAt != null) {
            requestJSON.add("due_at", BoxDateFormat.format(dueAt));
        }

        URL url = ADD_TASK_URL_TEMPLATE.build(this.getAPI().getBaseURL());
        BoxJSONRequest request = new BoxJSONRequest(this.getAPI(), url, "POST");
        request.setBody(requestJSON.toString());
        BoxJSONResponse response = (BoxJSONResponse) request.send();
        JsonObject responseJSON = JsonObject.readFrom(response.getJSON());

        BoxTask addedTask = new BoxTask(this.getAPI(), responseJSON.get("id").asString());
        return addedTask.new Info(responseJSON);
    }

    /**
     * Gets an expiring URL for downloading a file directly from Box. This can be user,
     * for example, for sending as a redirect to a browser to cause the browser
     * to download the file directly from Box.
     * @return the temporary download URL
     */
    public URL getDownloadURL() {
        URL url = CONTENT_URL_TEMPLATE.build(this.getAPI().getBaseURL(), this.getID());
        BoxAPIRequest request = new BoxAPIRequest(this.getAPI(), url, "GET");
        request.setFollowRedirects(false);

        BoxRedirectResponse response = (BoxRedirectResponse) request.send();

        return response.getRedirectURL();
    }

    /**
     * Downloads the contents of this file to a given OutputStream.
     * @param output the stream to where the file will be written.
     */
    public void download(OutputStream output) {
        this.download(output, null);
    }

    /**
     * Downloads the contents of this file to a given OutputStream while reporting the progress to a ProgressListener.
     * @param output   the stream to where the file will be written.
     * @param listener a listener for monitoring the download's progress.
     */
    public void download(OutputStream output, ProgressListener listener) {
        URL url = CONTENT_URL_TEMPLATE.build(this.getAPI().getBaseURL(), this.getID());
        BoxAPIRequest request = new BoxAPIRequest(this.getAPI(), url, "GET");
        BoxAPIResponse response = request.send();
        InputStream input = response.getBody(listener);

        byte[] buffer = new byte[BUFFER_SIZE];
        try {
            int n = input.read(buffer);
            while (n != -1) {
                output.write(buffer, 0, n);
                n = input.read(buffer);
            }
        } catch (IOException e) {
            throw new BoxAPIException("Couldn't connect to the Box API due to a network error.", e);
        } finally {
            response.disconnect();
        }
    }

    /**
     * Downloads a part of this file's contents, starting at specified byte offset.
     * @param output the stream to where the file will be written.
     * @param offset the byte offset at which to start the download.
     */
    public void downloadRange(OutputStream output, long offset) {
        this.downloadRange(output, offset, -1);
    }

    /**
     * Downloads a part of this file's contents, starting at rangeStart and stopping at rangeEnd.
     * @param output     the stream to where the file will be written.
     * @param rangeStart the byte offset at which to start the download.
     * @param rangeEnd   the byte offset at which to stop the download.
     */
    public void downloadRange(OutputStream output, long rangeStart, long rangeEnd) {
        this.downloadRange(output, rangeStart, rangeEnd, null);
    }

    /**
     * Downloads a part of this file's contents, starting at rangeStart and stopping at rangeEnd, while reporting the
     * progress to a ProgressListener.
     * @param output     the stream to where the file will be written.
     * @param rangeStart the byte offset at which to start the download.
     * @param rangeEnd   the byte offset at which to stop the download.
     * @param listener   a listener for monitoring the download's progress.
     */
    public void downloadRange(OutputStream output, long rangeStart, long rangeEnd, ProgressListener listener) {
        URL url = CONTENT_URL_TEMPLATE.build(this.getAPI().getBaseURL(), this.getID());
        BoxAPIRequest request = new BoxAPIRequest(this.getAPI(), url, "GET");
        if (rangeEnd > 0) {
            request.addHeader("Range", String.format("bytes=%s-%s", Long.toString(rangeStart),
                Long.toString(rangeEnd)));
        } else {
            request.addHeader("Range", String.format("bytes=%s-", Long.toString(rangeStart)));
        }

        BoxAPIResponse response = request.send();
        InputStream input = response.getBody(listener);

        byte[] buffer = new byte[BUFFER_SIZE];
        try {
            int n = input.read(buffer);
            while (n != -1) {
                output.write(buffer, 0, n);
                n = input.read(buffer);
            }
        } catch (IOException e) {
            throw new BoxAPIException("Couldn't connect to the Box API due to a network error.", e);
        } finally {
            response.disconnect();
        }
    }

    @Override
    public BoxFile.Info copy(BoxFolder destination) {
        return this.copy(destination, null);
    }

    @Override
    public BoxFile.Info copy(BoxFolder destination, String newName) {
        URL url = COPY_URL_TEMPLATE.build(this.getAPI().getBaseURL(), this.getID());

        JsonObject parent = new JsonObject();
        parent.add("id", destination.getID());

        JsonObject copyInfo = new JsonObject();
        copyInfo.add("parent", parent);
        if (newName != null) {
            copyInfo.add("name", newName);
        }

        BoxJSONRequest request = new BoxJSONRequest(this.getAPI(), url, "POST");
        request.setBody(copyInfo.toString());
        BoxJSONResponse response = (BoxJSONResponse) request.send();
        JsonObject responseJSON = JsonObject.readFrom(response.getJSON());
        BoxFile copiedFile = new BoxFile(this.getAPI(), responseJSON.get("id").asString());
        return copiedFile.new Info(responseJSON);
    }

    /**
     * Deletes this file by moving it to the trash.
     */
    public void delete() {
        URL url = FILE_URL_TEMPLATE.build(this.getAPI().getBaseURL(), this.getID());
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
        URL url = FILE_URL_TEMPLATE.build(this.getAPI().getBaseURL(), this.getID());
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
        BoxFile movedFile = new BoxFile(this.getAPI(), responseJSON.get("id").asString());
        return movedFile.new Info(responseJSON);
    }

    /**
     * Renames this file.
     * @param newName the new name of the file.
     */
    public void rename(String newName) {
        URL url = FILE_URL_TEMPLATE.build(this.getAPI().getBaseURL(), this.getID());
        BoxJSONRequest request = new BoxJSONRequest(this.getAPI(), url, "PUT");

        JsonObject updateInfo = new JsonObject();
        updateInfo.add("name", newName);

        request.setBody(updateInfo.toString());
        BoxAPIResponse response = request.send();
        response.disconnect();
    }

    @Override
    public BoxFile.Info getInfo() {
        URL url = FILE_URL_TEMPLATE.build(this.getAPI().getBaseURL(), this.getID());
        BoxAPIRequest request = new BoxAPIRequest(this.getAPI(), url, "GET");
        BoxJSONResponse response = (BoxJSONResponse) request.send();
        return new Info(response.getJSON());
    }

    @Override
    public BoxFile.Info getInfo(String... fields) {
        String queryString = new QueryStringBuilder().appendParam("fields", fields).toString();
        URL url = FILE_URL_TEMPLATE.buildWithQuery(this.getAPI().getBaseURL(), queryString, this.getID());

        BoxAPIRequest request = new BoxAPIRequest(this.getAPI(), url, "GET");
        BoxJSONResponse response = (BoxJSONResponse) request.send();
        return new Info(response.getJSON());
    }

    /**
     * Updates the information about this file with any info fields that have been modified locally.
     *
     * <p>The only fields that will be updated are the ones that have been modified locally. For example, the following
     * code won't update any information (or even send a network request) since none of the info's fields were
     * changed:</p>
     *
     * <pre>BoxFile file = new File(api, id);
     *BoxFile.Info info = file.getInfo();
     *file.updateInfo(info);</pre>
     *
     * @param info the updated info.
     */
    public void updateInfo(BoxFile.Info info) {
        URL url = FILE_URL_TEMPLATE.build(this.getAPI().getBaseURL(), this.getID());
        BoxJSONRequest request = new BoxJSONRequest(this.getAPI(), url, "PUT");
        request.setBody(info.getPendingChanges());
        BoxJSONResponse response = (BoxJSONResponse) request.send();
        JsonObject jsonObject = JsonObject.readFrom(response.getJSON());
        info.update(jsonObject);
    }

    /**
     * Gets any previous versions of this file. Note that only users with premium accounts will be able to retrieve
     * previous versions of their files.
     * @return a list of previous file versions.
     */
    public Collection<BoxFileVersion> getVersions() {
        URL url = VERSIONS_URL_TEMPLATE.build(this.getAPI().getBaseURL(), this.getID());
        BoxAPIRequest request = new BoxAPIRequest(this.getAPI(), url, "GET");
        BoxJSONResponse response = (BoxJSONResponse) request.send();

        JsonObject jsonObject = JsonObject.readFrom(response.getJSON());
        JsonArray entries = jsonObject.get("entries").asArray();
        Collection<BoxFileVersion> versions = new ArrayList<BoxFileVersion>();
        for (JsonValue entry : entries) {
            versions.add(new BoxFileVersion(this.getAPI(), entry.asObject(), this.getID()));
        }

        return versions;
    }

    /**
     * Checks if the file can be successfully uploaded by using the preflight check.
     * @param  name        the name to give the uploaded file or null to use existing name.
     * @param  fileSize    the size of the file used for account capacity calculations.
     * @param  parentID    the ID of the parent folder that the new version is being uploaded to.
     */
    public void canUploadVersion(String name, long fileSize, String parentID) {
        URL url = CONTENT_URL_TEMPLATE.build(this.getAPI().getBaseURL(), this.getID());
        BoxJSONRequest request = new BoxJSONRequest(this.getAPI(), url, "OPTIONS");

        JsonObject parent = new JsonObject();
        parent.add("id", parentID);

        JsonObject preflightInfo = new JsonObject();
        preflightInfo.add("parent", parent);
        if (name != null) {
            preflightInfo.add("name", name);
        }

        preflightInfo.add("size", fileSize);

        request.setBody(preflightInfo.toString());
        BoxAPIResponse response = request.send();
        response.disconnect();
    }

    /**
     * Uploads a new version of this file, replacing the current version. Note that only users with premium accounts
     * will be able to view and recover previous versions of the file.
     * @param fileContent a stream containing the new file contents.
     */
    public void uploadVersion(InputStream fileContent) {
        this.uploadVersion(fileContent, null);
    }

    /**
     * Uploads a new version of this file, replacing the current version. Note that only users with premium accounts
     * will be able to view and recover previous versions of the file.
     * @param fileContent a stream containing the new file contents.
     * @param modified    the date that the new version was modified.
     */
    public void uploadVersion(InputStream fileContent, Date modified) {
        this.uploadVersion(fileContent, modified, 0, null);
    }

    /**
     * Uploads a new version of this file, replacing the current version, while reporting the progress to a
     * ProgressListener. Note that only users with premium accounts will be able to view and recover previous versions
     * of the file.
     * @param fileContent a stream containing the new file contents.
     * @param modified    the date that the new version was modified.
     * @param fileSize    the size of the file used for determining the progress of the upload.
     * @param listener    a listener for monitoring the upload's progress.
     */
    public void uploadVersion(InputStream fileContent, Date modified, long fileSize, ProgressListener listener) {
        URL uploadURL = CONTENT_URL_TEMPLATE.build(this.getAPI().getBaseUploadURL(), this.getID());
        BoxMultipartRequest request = new BoxMultipartRequest(getAPI(), uploadURL);
        if (fileSize > 0) {
            request.setFile(fileContent, "", fileSize);
        } else {
            request.setFile(fileContent, "");
        }

        if (modified != null) {
            request.putField("content_modified_at", modified);
        }

        BoxAPIResponse response;
        if (listener == null) {
            response = request.send();
        } else {
            response = request.send(listener);
        }
        response.disconnect();
    }

    /**
     * Gets an expiring URL for creating an embedded preview session. The URL will expire after 60 seconds and the
     * preview session will expire after 60 minutes.
     * @return the expiring preview link
     */
    public URL getPreviewLink() {
        BoxFile.Info info = this.getInfo("expiring_embed_link");

        return info.getPreviewLink();
    }


    /**
     * Retrieves a thumbnail, or smaller image representation, of this file. Sizes of 32x32, 64x64, 128x128,
     * and 256x256 can be returned in the .png format and sizes of 32x32, 94x94, 160x160, and 320x320 can be returned
     * in the .jpg format.
     * @param fileType      either PNG of JPG
     * @param minWidth      minimum width
     * @param minHeight     minimum height
     * @param maxWidth      maximum width
     * @param maxHeight     maximum height
     * @return the byte array of the thumbnail image
     */
    public byte[] getThumbnail(ThumbnailFileType fileType, int minWidth, int minHeight, int maxWidth, int maxHeight) {
        QueryStringBuilder builder = new QueryStringBuilder();
        builder.appendParam("min_width", minWidth);
        builder.appendParam("min_height", minHeight);
        builder.appendParam("max_width", maxWidth);
        builder.appendParam("max_height", maxHeight);

        URLTemplate template;
        if (fileType == ThumbnailFileType.PNG) {
            template = GET_THUMBNAIL_PNG_TEMPLATE;
        } else if (fileType == ThumbnailFileType.JPG) {
            template = GET_THUMBNAIL_JPG_TEMPLATE;
        } else {
            throw new BoxAPIException("Unsupported thumbnail file type");
        }
        URL url = template.buildWithQuery(this.getAPI().getBaseURL(), builder.toString(), this.getID());

        BoxAPIRequest request = new BoxAPIRequest(this.getAPI(), url, "GET");
        BoxAPIResponse response = request.send();

        ByteArrayOutputStream thumbOut = new ByteArrayOutputStream();
        InputStream body = response.getBody();
        byte[] buffer = new byte[BUFFER_SIZE];
        try {
            int n = body.read(buffer);
            while (n != -1) {
                thumbOut.write(buffer, 0, n);
                n = body.read(buffer);
            }
        } catch (IOException e) {
            throw new BoxAPIException("Error reading thumbnail bytes from response body", e);
        } finally {
            response.disconnect();
        }

        return thumbOut.toByteArray();
    }

    /**
     * Gets a list of any comments on this file.
     * @return a list of comments on this file.
     */
    public List<BoxComment.Info> getComments() {
        URL url = GET_COMMENTS_URL_TEMPLATE.build(this.getAPI().getBaseURL(), this.getID());
        BoxAPIRequest request = new BoxAPIRequest(this.getAPI(), url, "GET");
        BoxJSONResponse response = (BoxJSONResponse) request.send();
        JsonObject responseJSON = JsonObject.readFrom(response.getJSON());

        int totalCount = responseJSON.get("total_count").asInt();
        List<BoxComment.Info> comments = new ArrayList<BoxComment.Info>(totalCount);
        JsonArray entries = responseJSON.get("entries").asArray();
        for (JsonValue value : entries) {
            JsonObject commentJSON = value.asObject();
            BoxComment comment = new BoxComment(this.getAPI(), commentJSON.get("id").asString());
            BoxComment.Info info = comment.new Info(commentJSON);
            comments.add(info);
        }

        return comments;
    }

    /**
     * Gets a list of any tasks on this file.
     * @return a list of tasks on this file.
     */
    public List<BoxTask.Info> getTasks() {
        URL url = GET_TASKS_URL_TEMPLATE.build(this.getAPI().getBaseURL(), this.getID());
        BoxAPIRequest request = new BoxAPIRequest(this.getAPI(), url, "GET");
        BoxJSONResponse response = (BoxJSONResponse) request.send();
        JsonObject responseJSON = JsonObject.readFrom(response.getJSON());

        int totalCount = responseJSON.get("total_count").asInt();
        List<BoxTask.Info> tasks = new ArrayList<BoxTask.Info>(totalCount);
        JsonArray entries = responseJSON.get("entries").asArray();
        for (JsonValue value : entries) {
            JsonObject taskJSON = value.asObject();
            BoxTask task = new BoxTask(this.getAPI(), taskJSON.get("id").asString());
            BoxTask.Info info = task.new Info(taskJSON);
            tasks.add(info);
        }

        return tasks;
    }

    /**
     * Creates metadata on this file in the global properties template.
     * @param metadata The new metadata values.
     * @return the metadata returned from the server.
     */
    public Metadata createMetadata(Metadata metadata) {
        return this.createMetadata(DEFAULT_METADATA_TYPE, metadata);
    }

    /**
     * Creates metadata on this file in the specified template type.
     * @param typeName the metadata template type name.
     * @param metadata the new metadata values.
     * @return the metadata returned from the server.
     */
    public Metadata createMetadata(String typeName, Metadata metadata) {
        String scope = this.scopeBasedOnType(typeName);
        return this.createMetadata(typeName, scope, metadata);
    }

    /**
     * Creates metadata on this file in the specified template type.
     * @param typeName the metadata template type name.
     * @param scope the metadata scope (global or enterprise).
     * @param metadata the new metadata values.
     * @return the metadata returned from the server.
     */
    public Metadata createMetadata(String typeName, String scope, Metadata metadata) {
        URL url = METADATA_URL_TEMPLATE.build(this.getAPI().getBaseURL(), this.getID(), scope, typeName);
        BoxAPIRequest request = new BoxAPIRequest(this.getAPI(), url, "POST");
        request.addHeader("Content-Type", "application/json");
        request.setBody(metadata.toString());
        BoxJSONResponse response = (BoxJSONResponse) request.send();
        return new Metadata(JsonObject.readFrom(response.getJSON()));
    }

    /**
     * Locks a file.
     * @param expiresAt expiration date of the lock.
     * @return the lock returned from the server.
     */
    public BoxLock lock(Date expiresAt) {
        return this.lock(expiresAt, false);
    }

    /**
     * Locks a file.
     * @param expiresAt expiration date of the lock.
     * @param isDownloadPrevented is downloading of file prevented when locked.
     * @return the lock returned from the server.
     */
    public BoxLock lock(Date expiresAt, boolean isDownloadPrevented) {
        String queryString = new QueryStringBuilder().appendParam("fields", "lock").toString();
        URL url = FILE_URL_TEMPLATE.buildWithQuery(this.getAPI().getBaseURL(), queryString, this.getID());
        BoxAPIRequest request = new BoxAPIRequest(this.getAPI(), url, "PUT");

        JsonObject lockConfig = new JsonObject();
        lockConfig.add("type", "lock");
        lockConfig.add("expires_at", BoxDateFormat.format(expiresAt));
        lockConfig.add("is_download_prevented", isDownloadPrevented);

        JsonObject requestJSON = new JsonObject();
        requestJSON.add("lock", lockConfig);
        request.setBody(requestJSON.toString());

        BoxJSONResponse response = (BoxJSONResponse) request.send();

        JsonObject responseJSON = JsonObject.readFrom(response.getJSON());
        JsonValue lockValue = responseJSON.get("lock");
        JsonObject lockJSON = JsonObject.readFrom(lockValue.toString());

        return new BoxLock(lockJSON, this.getAPI());
    }

    /**
     * Unlocks a file.
     */
    public void unlock() {
        String queryString = new QueryStringBuilder().appendParam("fields", "lock").toString();
        URL url = FILE_URL_TEMPLATE.buildWithQuery(this.getAPI().getBaseURL(), queryString, this.getID());
        BoxAPIRequest request = new BoxAPIRequest(this.getAPI(), url, "PUT");

        JsonObject lockObject = new JsonObject();
        lockObject.add("lock", JsonObject.NULL);

        request.setBody(lockObject.toString());
        request.send();
    }

    /**
     * Gets the file properties metadata.
     * @return the metadata returned from the server.
     */
    public Metadata getMetadata() {
        return this.getMetadata(DEFAULT_METADATA_TYPE);
    }

    /**
     * Gets the file metadata of specified template type.
     * @param typeName the metadata template type name.
     * @return the metadata returned from the server.
     */
    public Metadata getMetadata(String typeName) {
        String scope = this.scopeBasedOnType(typeName);
        return this.getMetadata(typeName, scope);
    }

    /**
     * Gets the file metadata of specified template type.
     * @param typeName the metadata template type name.
     * @param scope the metadata scope (global or enterprise).
     * @return the metadata returned from the server.
     */
    public Metadata getMetadata(String typeName, String scope) {
        URL url = METADATA_URL_TEMPLATE.build(this.getAPI().getBaseURL(), this.getID(), scope, typeName);
        BoxAPIRequest request = new BoxAPIRequest(this.getAPI(), url, "GET");
        BoxJSONResponse response = (BoxJSONResponse) request.send();
        return new Metadata(JsonObject.readFrom(response.getJSON()));
    }

    /**
     * Updates the file metadata.
     * @param metadata the new metadata values.
     * @return the metadata returned from the server.
     */
    public Metadata updateMetadata(Metadata metadata) {
        String scope;
        if (metadata.getScope().equals(GLOBAL_METADATA_SCOPE)) {
            scope = GLOBAL_METADATA_SCOPE;
        } else {
            scope = ENTERPRISE_METADATA_SCOPE;
        }

        URL url = METADATA_URL_TEMPLATE.build(this.getAPI().getBaseURL(), this.getID(),
                                                scope, metadata.getTemplateName());
        BoxAPIRequest request = new BoxAPIRequest(this.getAPI(), url, "PUT");
        request.addHeader("Content-Type", "application/json-patch+json");
        request.setBody(metadata.getPatch());
        BoxJSONResponse response = (BoxJSONResponse) request.send();
        return new Metadata(JsonObject.readFrom(response.getJSON()));
    }

    /**
     * Deletes the file properties metadata.
     */
    public void deleteMetadata() {
        this.deleteMetadata(DEFAULT_METADATA_TYPE);
    }

    /**
     * Deletes the file metadata of specified template type.
     * @param typeName the metadata template type name.
     */
    public void deleteMetadata(String typeName) {
        String scope = this.scopeBasedOnType(typeName);
        this.deleteMetadata(typeName, scope);
    }

    /**
     * Deletes the file metadata of specified template type.
     * @param typeName the metadata template type name.
     * @param scope the metadata scope (global or enterprise).
     */
    public void deleteMetadata(String typeName, String scope) {
        URL url = METADATA_URL_TEMPLATE.build(this.getAPI().getBaseURL(), this.getID(), scope, typeName);
        BoxAPIRequest request = new BoxAPIRequest(this.getAPI(), url, "DELETE");
        request.send();
    }

    private String scopeBasedOnType(String typeName) {
        String scope;
        if (typeName.equals(DEFAULT_METADATA_TYPE)) {
            scope = GLOBAL_METADATA_SCOPE;
        } else {
            scope = ENTERPRISE_METADATA_SCOPE;
        }
        return scope;
    }

    /**
     * Contains information about a BoxFile.
     */
    public class Info extends BoxItem.Info {
        private String sha1;
        private String versionNumber;
        private long commentCount;
        private EnumSet<Permission> permissions;
        private String extension;
        private boolean isPackage;
        private BoxFileVersion version;
        private URL previewLink;
        private BoxLock lock;

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
        Info(JsonObject jsonObject) {
            super(jsonObject);
        }

        @Override
        public BoxFile getResource() {
            return BoxFile.this;
        }

        /**
         * Gets the SHA1 hash of the file.
         * @return the SHA1 hash of the file.
         */
        public String getSha1() {
            return this.sha1;
        }

        /**
         * Gets the lock of the file.
         * @return the lock of the file.
         */
        public BoxLock getLock() {
            return this.lock;
        }

        /**
         * Gets the current version number of the file.
         * @return the current version number of the file.
         */
        public String getVersionNumber() {
            return this.versionNumber;
        }

        /**
         * Gets the number of comments on the file.
         * @return the number of comments on the file.
         */
        public long getCommentCount() {
            return this.commentCount;
        }

        /**
         * Gets the permissions that the current user has on the file.
         * @return the permissions that the current user has on the file.
         */
        public EnumSet<Permission> getPermissions() {
            return this.permissions;
        }

        /**
         * Gets the extension suffix of the file, excluding the dot.
         * @return the extension of the file.
         */
        public String getExtension() {
            return this.extension;
        }

        /**
         * Gets whether or not the file is an OSX package.
         * @return true if the file is an OSX package; otherwise false.
         */
        public boolean getIsPackage() {
            return this.isPackage;
        }

        /**
         * Gets the current version details of the file.
         * @return the current version details of the file.
         */
        public BoxFileVersion getVersion() {
            return this.version;
        }

        /**
         * Gets the current expiring preview link.
         * @return the expiring preview link
         */
        public URL getPreviewLink() {
            return this.previewLink;
        }

        @Override
        protected void parseJSONMember(JsonObject.Member member) {
            super.parseJSONMember(member);

            String memberName = member.getName();
            JsonValue value = member.getValue();
            if (memberName.equals("sha1")) {
                this.sha1 = value.asString();
            } else if (memberName.equals("version_number")) {
                this.versionNumber = value.asString();
            } else if (memberName.equals("comment_count")) {
                this.commentCount = value.asLong();
            } else if (memberName.equals("permissions")) {
                this.permissions = this.parsePermissions(value.asObject());
            } else if (memberName.equals("extension")) {
                this.extension = value.asString();
            } else if (memberName.equals("is_package")) {
                this.isPackage = value.asBoolean();
            } else if (memberName.equals("file_version")) {
                this.version = this.parseFileVersion(value.asObject());
            } else if (memberName.equals("expiring_embed_link")) {
                try {
                    String urlString = member.getValue().asObject().get("url").asString();
                    this.previewLink = new URL(urlString);
                } catch (MalformedURLException e) {
                    throw new BoxAPIException("Couldn't parse expiring_embed_link/url for file", e);
                }
            } else if (memberName.equals("lock")) {
                if (value.isNull()) {
                    this.lock = null;
                } else {
                    this.lock = new BoxLock(value.asObject(), BoxFile.this.getAPI());
                }
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
                } else if (memberName.equals("can_set_share_access")) {
                    permissions.add(Permission.CAN_SET_SHARE_ACCESS);
                } else if (memberName.equals("can_preview")) {
                    permissions.add(Permission.CAN_PREVIEW);
                } else if (memberName.equals("can_comment")) {
                    permissions.add(Permission.CAN_COMMENT);
                }
            }

            return permissions;
        }

        private BoxFileVersion parseFileVersion(JsonObject jsonObject) {
            return new BoxFileVersion(BoxFile.this.getAPI(), jsonObject, BoxFile.this.getID());
        }
    }

    /**
     * Enumerates the possible permissions that a user can have on a file.
     */
    public enum Permission {
        /**
         * The user can download the file.
         */
        CAN_DOWNLOAD ("can_download"),

        /**
         * The user can upload new versions of the file.
         */
        CAN_UPLOAD ("can_upload"),

        /**
         * The user can rename the file.
         */
        CAN_RENAME ("can_rename"),

        /**
         * The user can delete the file.
         */
        CAN_DELETE ("can_delete"),

        /**
         * The user can share the file.
         */
        CAN_SHARE ("can_share"),

        /**
         * The user can set the access level for shared links to the file.
         */
        CAN_SET_SHARE_ACCESS ("can_set_share_access"),

        /**
         * The user can preview the file.
         */
        CAN_PREVIEW ("can_preview"),

        /**
         * The user can comment on the file.
         */
        CAN_COMMENT ("can_comment");

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
