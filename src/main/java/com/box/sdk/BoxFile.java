package com.box.sdk;

import static com.box.sdk.BinaryBodyUtils.writeStream;
import static com.box.sdk.http.ContentType.APPLICATION_JSON;
import static com.box.sdk.http.ContentType.APPLICATION_JSON_PATCH;
import static com.eclipsesource.json.Json.NULL;

import com.box.sdk.http.HttpMethod;
import com.box.sdk.internal.utils.Parsers;
import com.box.sdk.sharedlink.BoxSharedLinkRequest;
import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.TimeUnit;


/**
 * Represents an individual file on Box. This class can be used to download a file's contents, upload new versions, and
 * perform other common file operations (move, copy, delete, etc.).
 *
 * <p>Unless otherwise noted, the methods in this class can throw an unchecked {@link BoxAPIException} (unchecked
 * meaning that the compiler won't force you to handle it) if an error occurs. If you wish to implement custom error
 * handling for errors related to the Box REST API, you should capture this exception explicitly.
 */
@BoxResourceType("file")
public class BoxFile extends BoxItem {

    /**
     * An array of all possible file fields that can be requested when calling {@link #getInfo(String...)}.
     */
    public static final String[] ALL_FIELDS = {"type", "id", "sequence_id", "etag", "sha1", "name",
        "description", "size", "path_collection", "created_at", "modified_at",
        "trashed_at", "purged_at", "content_created_at", "content_modified_at",
        "created_by", "modified_by", "owned_by", "shared_link", "parent",
        "item_status", "version_number", "comment_count", "permissions", "tags",
        "lock", "extension", "is_package", "file_version", "collections",
        "watermark_info", "metadata", "representations",
        "is_external_only", "expiring_embed_link", "allowed_invitee_roles",
        "has_collaborations", "disposition_at", "is_accessible_via_shared_link"};

    /**
     * An array of all possible version fields that can be requested when calling {@link #getVersions(String...)}.
     */
    public static final String[] ALL_VERSION_FIELDS = {"id", "sha1", "name", "size", "uploader_display_name",
        "created_at", "modified_at", "modified_by", "trashed_at", "trashed_by", "restored_at", "restored_by",
        "purged_at", "file_version", "version_number"};
    /**
     * File URL Template.
     */
    public static final URLTemplate FILE_URL_TEMPLATE = new URLTemplate("files/%s");
    /**
     * Content URL Template.
     */
    public static final URLTemplate CONTENT_URL_TEMPLATE = new URLTemplate("files/%s/content");
    /**
     * Versions URL Template.
     */
    public static final URLTemplate VERSIONS_URL_TEMPLATE = new URLTemplate("files/%s/versions");
    /**
     * Copy URL Template.
     */
    public static final URLTemplate COPY_URL_TEMPLATE = new URLTemplate("files/%s/copy");
    /**
     * Add Comment URL Template.
     */
    public static final URLTemplate ADD_COMMENT_URL_TEMPLATE = new URLTemplate("comments");
    /**
     * Get Comments URL Template.
     */
    public static final URLTemplate GET_COMMENTS_URL_TEMPLATE = new URLTemplate("files/%s/comments");
    /**
     * Metadata URL Template.
     */
    public static final URLTemplate METADATA_URL_TEMPLATE = new URLTemplate("files/%s/metadata/%s/%s");
    /**
     * Add Task URL Template.
     */
    public static final URLTemplate ADD_TASK_URL_TEMPLATE = new URLTemplate("tasks");
    /**
     * Get Tasks URL Template.
     */
    public static final URLTemplate GET_TASKS_URL_TEMPLATE = new URLTemplate("files/%s/tasks");
    /**
     * Get Thumbnail PNG Template.
     */
    public static final URLTemplate GET_THUMBNAIL_PNG_TEMPLATE = new URLTemplate("files/%s/thumbnail.png");
    /**
     * Get Thumbnail JPG Template.
     */
    public static final URLTemplate GET_THUMBNAIL_JPG_TEMPLATE = new URLTemplate("files/%s/thumbnail.jpg");
    /**
     * Upload Session URL Template.
     */
    public static final URLTemplate UPLOAD_SESSION_URL_TEMPLATE = new URLTemplate("files/%s/upload_sessions");
    /**
     * Upload Session Status URL Template.
     */
    public static final URLTemplate UPLOAD_SESSION_STATUS_URL_TEMPLATE = new URLTemplate(
        "files/upload_sessions/%s/status");
    /**
     * Abort Upload Session URL Template.
     */
    public static final URLTemplate ABORT_UPLOAD_SESSION_URL_TEMPLATE = new URLTemplate("files/upload_sessions/%s");
    /**
     * Add Collaborations URL Template.
     */
    public static final URLTemplate ADD_COLLABORATION_URL = new URLTemplate("collaborations");
    /**
     * Get All File Collaborations URL Template.
     */
    public static final URLTemplate GET_ALL_FILE_COLLABORATIONS_URL = new URLTemplate("files/%s/collaborations");
    /**
     * Describes file item type.
     */
    static final String TYPE = "file";
    private static final int GET_COLLABORATORS_PAGE_SIZE = 1000;

    /**
     * Constructs a BoxFile for a file with a given ID.
     *
     * @param api the API connection to be used by the file.
     * @param id  the ID of the file.
     */
    public BoxFile(BoxAPIConnection api, String id) {
        super(api, id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected URL getItemURL() {
        return FILE_URL_TEMPLATE.build(this.getAPI().getBaseURL(), this.getID());
    }

    /**
     * Creates a shared link.
     *
     * @param sharedLinkRequest Shared link to create
     * @return Created shared link.
     */
    public BoxSharedLink createSharedLink(BoxSharedLinkRequest sharedLinkRequest) {
        return createSharedLink(sharedLinkRequest.asSharedLink());
    }

    private BoxSharedLink createSharedLink(BoxSharedLink sharedLink) {
        Info info = new Info();
        info.setSharedLink(sharedLink);

        this.updateInfo(info);
        return info.getSharedLink();
    }

    /**
     * Adds new {@link BoxWebHook} to this {@link BoxFile}.
     *
     * @param address  {@link BoxWebHook.Info#getAddress()}
     * @param triggers {@link BoxWebHook.Info#getTriggers()}
     * @return created {@link BoxWebHook.Info}
     */
    public BoxWebHook.Info addWebHook(URL address, BoxWebHook.Trigger... triggers) {
        return BoxWebHook.create(this, address, triggers);
    }

    /**
     * Adds a comment to this file. The message can contain @mentions by using the string @[userid:username] anywhere
     * within the message, where userid and username are the ID and username of the person being mentioned.
     *
     * @param message the comment's message.
     * @return information about the newly added comment.
     * @see <a href="https://developers.box.com/docs/#comments-add-a-comment-to-an-item">the tagged_message field
     * for including @mentions.</a>
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
        try (BoxJSONResponse response = request.send()) {
            JsonObject responseJSON = Json.parse(response.getJSON()).asObject();

            BoxComment addedComment = new BoxComment(this.getAPI(), responseJSON.get("id").asString());
            return addedComment.new Info(responseJSON);
        }
    }

    /**
     * Adds a new task to this file. The task can have an optional message to include, and a due date.
     *
     * @param action  the action the task assignee will be prompted to do.
     * @param message an optional message to include with the task.
     * @param dueAt   the day at which this task is due.
     * @return information about the newly added task.
     */
    public BoxTask.Info addTask(BoxTask.Action action, String message, Date dueAt) {
        return this.addTask(action, message, dueAt, null);
    }

    /**
     * Adds a new task to this file. The task can have an optional message to include, due date,
     * and task completion rule.
     *
     * @param action         the action the task assignee will be prompted to do.
     * @param message        an optional message to include with the task.
     * @param dueAt          the day at which this task is due.
     * @param completionRule the rule for completing the task.
     * @return information about the newly added task.
     */
    public BoxTask.Info addTask(BoxTask.Action action, String message, Date dueAt,
                                BoxTask.CompletionRule completionRule) {
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

        if (completionRule != null) {
            requestJSON.add("completion_rule", completionRule.toJSONString());
        }

        URL url = ADD_TASK_URL_TEMPLATE.build(this.getAPI().getBaseURL());
        BoxJSONRequest request = new BoxJSONRequest(this.getAPI(), url, "POST");
        request.setBody(requestJSON.toString());
        try (BoxJSONResponse response = request.send()) {
            JsonObject responseJSON = Json.parse(response.getJSON()).asObject();

            BoxTask addedTask = new BoxTask(this.getAPI(), responseJSON.get("id").asString());
            return addedTask.new Info(responseJSON);
        }
    }

    /**
     * Gets an expiring URL for downloading a file directly from Box. This can be user,
     * for example, for sending as a redirect to a browser to cause the browser
     * to download the file directly from Box.
     *
     * @return the temporary download URL
     */
    public URL getDownloadURL() {
        URL url = getDownloadUrl();
        BoxAPIRequest request = new BoxAPIRequest(this.getAPI(), url, "GET");
        request.setFollowRedirects(false);

        try (BoxAPIResponse response = request.send()) {
            String location = response.getHeaderField("location");

            try {
                return new URL(location);
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Downloads the contents of this file to a given OutputStream.
     *
     * @param output the stream to where the file will be written.
     */
    public void download(OutputStream output) {
        this.download(output, null);
    }

    /**
     * Downloads the contents of this file to a given OutputStream while reporting the progress to a ProgressListener.
     *
     * @param output   the stream to where the file will be written.
     * @param listener a listener for monitoring the download's progress.
     */
    public void download(OutputStream output, ProgressListener listener) {
        URL url = getDownloadUrl();
        BoxAPIRequest request = new BoxAPIRequest(this.getAPI(), url, "GET");
        BoxAPIResponse response = request.send();
        writeStream(response, output, listener);
    }

    /**
     * Downloads a part of this file's contents, starting at specified byte offset.
     *
     * @param output the stream to where the file will be written.
     * @param offset the byte offset at which to start the download.
     */
    public void downloadRange(OutputStream output, long offset) {
        this.downloadRange(output, offset, -1);
    }

    /**
     * Downloads a part of this file's contents, starting at rangeStart and stopping at rangeEnd.
     *
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
     *
     * @param output     the stream to where the file will be written.
     * @param rangeStart the byte offset at which to start the download.
     * @param rangeEnd   the byte offset at which to stop the download.
     * @param listener   a listener for monitoring the download's progress.
     */
    public void downloadRange(OutputStream output, long rangeStart, long rangeEnd, ProgressListener listener) {
        URL url = getDownloadUrl();
        BoxAPIRequest request = new BoxAPIRequest(this.getAPI(), url, "GET");
        if (rangeEnd > 0) {
            request.addHeader("Range", String.format("bytes=%s-%s", rangeStart, rangeEnd));
        } else {
            request.addHeader("Range", String.format("bytes=%s-", rangeStart));
        }
        writeStream(request.send(), output, listener);
    }

    /**
     * Can be used to override the URL used for file download.
     * @return URL for file downalod
     */
    protected URL getDownloadUrl() {
        return CONTENT_URL_TEMPLATE.build(this.getAPI().getBaseURL(), this.getID());
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
        try (BoxJSONResponse response = request.send()) {
            JsonObject responseJSON = Json.parse(response.getJSON()).asObject();
            BoxFile copiedFile = new BoxFile(this.getAPI(), responseJSON.get("id").asString());
            return copiedFile.new Info(responseJSON);
        }
    }

    /**
     * Deletes this file by moving it to the trash.
     */
    public void delete() {
        URL url = FILE_URL_TEMPLATE.build(this.getAPI().getBaseURL(), this.getID());
        BoxAPIRequest request = new BoxAPIRequest(this.getAPI(), url, "DELETE");
        request.send().close();
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
        try (BoxJSONResponse response = request.send()) {
            JsonObject responseJSON = Json.parse(response.getJSON()).asObject();
            BoxFile movedFile = new BoxFile(this.getAPI(), responseJSON.get("id").asString());
            return movedFile.new Info(responseJSON);
        }
    }

    /**
     * Renames this file.
     *
     * @param newName the new name of the file.
     */
    public void rename(String newName) {
        URL url = FILE_URL_TEMPLATE.build(this.getAPI().getBaseURL(), this.getID());
        BoxJSONRequest request = new BoxJSONRequest(this.getAPI(), url, "PUT");

        JsonObject updateInfo = new JsonObject();
        updateInfo.add("name", newName);

        request.setBody(updateInfo.toString());
        try (BoxJSONResponse response = request.send()) {
            response.getJSON();
        }
    }

    @Override
    public BoxFile.Info getInfo(String... fields) {
        URL url = FILE_URL_TEMPLATE.build(this.getAPI().getBaseURL(), this.getID());
        if (fields.length > 0) {
            String queryString = new QueryStringBuilder().appendParam("fields", fields).toString();
            url = FILE_URL_TEMPLATE.buildWithQuery(this.getAPI().getBaseURL(), queryString, this.getID());
        }

        BoxJSONRequest request = new BoxJSONRequest(this.getAPI(), url, "GET");
        try (BoxJSONResponse response = request.send()) {
            return new Info(response.getJSON());
        }
    }

    /**
     * Gets information about this item including a specified set of representations.
     *
     * @param representationHints hints for representations to be retrieved
     * @param fields              the fields to retrieve.
     * @return info about this item containing only the specified fields, including representations.
     * @see <a href=https://developer.box.com/reference#section-x-rep-hints-header>X-Rep-Hints Header</a>
     */
    public BoxFile.Info getInfoWithRepresentations(String representationHints, String... fields) {
        if (representationHints.matches(Representation.X_REP_HINTS_PATTERN)) {
            //Since the user intends to get representations, add it to fields, even if user has missed it
            Set<String> fieldsSet = new HashSet<>(Arrays.asList(fields));
            fieldsSet.add("representations");
            String queryString = new QueryStringBuilder().appendParam("fields",
                fieldsSet.toArray(new String[0])).toString();
            URL url = FILE_URL_TEMPLATE.buildWithQuery(this.getAPI().getBaseURL(), queryString, this.getID());

            BoxJSONRequest request = new BoxJSONRequest(this.getAPI(), url, "GET");
            request.addHeader("X-Rep-Hints", representationHints);
            try (BoxJSONResponse response = request.send()) {
                return new Info(response.getJSON());
            }
        } else {
            throw new BoxAPIException(
                "Represention hints is not valid. Refer documention on how to construct X-Rep-Hints Header"
            );
        }
    }

    /**
     * Fetches the contents of a file representation and writes them to the provided output stream.
     *
     * @param representationHint the X-Rep-Hints query for the representation to fetch.
     * @param output             the output stream to write the contents to.
     * @see <a href=https://developer.box.com/reference#section-x-rep-hints-header>X-Rep-Hints Header</a>
     */
    public void getRepresentationContent(String representationHint, OutputStream output) {

        this.getRepresentationContent(representationHint, "", output);
    }

    /**
     * Fetches the contents of a file representation with asset path and writes them to the provided output stream.
     *
     * @param representationHint the X-Rep-Hints query for the representation to fetch.
     * @param assetPath          the path of the asset for representations containing multiple files.
     * @param output             the output stream to write the contents to.
     * @see <a href=https://developer.box.com/reference#section-x-rep-hints-header>X-Rep-Hints Header</a>
     */
    public void getRepresentationContent(String representationHint, String assetPath, OutputStream output) {

        List<Representation> reps = this.getInfoWithRepresentations(representationHint).getRepresentations();
        if (reps.size() < 1) {
            throw new BoxAPIException("No matching representations found for requested '" + representationHint + "' hint");
        }
        Representation representation = reps.get(0);
        String repState = representation.getStatus().getState();

        switch (repState) {
            case "viewable":
            case "success":
                this.makeRepresentationContentRequest(representation.getContent().getUrlTemplate(), assetPath, output);
                break;
            case "pending":
            case "none":

                String repContentURLString = null;
                while (repContentURLString == null) {
                    repContentURLString = this.pollRepInfo(representation.getInfo().getUrl());
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }

                this.makeRepresentationContentRequest(repContentURLString, assetPath, output);
                break;
            case "error":
                throw new BoxAPIException("Representation had error status");
            default:
                throw new BoxAPIException("Representation had unknown status");
        }

    }

    private String pollRepInfo(URL infoURL) {

        BoxJSONRequest infoRequest = new BoxJSONRequest(this.getAPI(), infoURL, HttpMethod.GET);
        try (BoxJSONResponse infoResponse = infoRequest.send()) {
            JsonObject response = infoResponse.getJsonObject();

            Representation rep = new Representation(response);

            String repState = rep.getStatus().getState();

            switch (repState) {
                case "viewable":
                case "success":
                    return rep.getContent().getUrlTemplate();
                case "pending":
                case "none":
                    return null;
                case "error":
                    throw new BoxAPIException("Representation had error status");
                default:
                    throw new BoxAPIException("Representation had unknown status");
            }
        }
    }

    private void makeRepresentationContentRequest(
        String representationURLTemplate, String assetPath, OutputStream output
    ) {
        try {
            URL repURL = new URL(representationURLTemplate.replace("{+asset_path}", assetPath));
            BoxAPIRequest repContentReq = new BoxAPIRequest(this.getAPI(), repURL, HttpMethod.GET);
            BoxAPIResponse response = repContentReq.send();
            writeStream(response, output);
        } catch (MalformedURLException ex) {

            throw new BoxAPIException("Could not generate representation content URL");
        }
    }

    /**
     * Updates the information about this file with any info fields that have been modified locally.
     *
     * <p>The only fields that will be updated are the ones that have been modified locally. For example, the following
     * code won't update any information (or even send a network request) since none of the info's fields were
     * changed:</p>
     *
     * <pre>BoxFile file = new File(api, id);
     * BoxFile.Info info = file.getInfo();
     * file.updateInfo(info);</pre>
     *
     * @param info the updated info.
     */
    public void updateInfo(BoxFile.Info info) {
        URL url = FILE_URL_TEMPLATE.build(this.getAPI().getBaseURL(), this.getID());
        BoxJSONRequest request = new BoxJSONRequest(this.getAPI(), url, "PUT");
        request.setBody(info.getPendingChanges());
        try (BoxJSONResponse response = request.send()) {
            JsonObject jsonObject = Json.parse(response.getJSON()).asObject();
            info.update(jsonObject);
        }
    }

    /**
     * Gets any previous versions of this file. Note that only users with premium accounts will be able to retrieve
     * previous versions of their files. `fields` parameter is optional, if specified only requested fields will
     * be returned:
     * <pre>
     * {@code
     * new BoxFile(api, file_id).getVersions()       // will return all default fields
     * new BoxFile(api, file_id).getVersions("name") // will return only specified fields
     * }
     * </pre>
     *
     * @param fields the fields to retrieve. If nothing provided default fields will be returned.
     *               You can find list of available fields at {@link BoxFile#ALL_VERSION_FIELDS}
     * @return a list of previous file versions.
     */
    public Collection<BoxFileVersion> getVersions(String... fields) {
        URL url = VERSIONS_URL_TEMPLATE.build(this.getAPI().getBaseURL(), this.getID());
        try {
            if (fields.length > 0) {
                QueryStringBuilder builder = new QueryStringBuilder(url.getQuery());
                builder.appendParam("fields", fields);
                url = builder.addToURL(url);
            }
        } catch (MalformedURLException e) {
            throw new BoxAPIException("Couldn't append a query string to the provided URL.", e);
        }
        BoxJSONRequest request = new BoxJSONRequest(this.getAPI(), url, "GET");
        try (BoxJSONResponse response = request.send()) {

            JsonObject jsonObject = Json.parse(response.getJSON()).asObject();
            JsonArray entries = jsonObject.get("entries").asArray();
            Collection<BoxFileVersion> versions = new ArrayList<>();
            for (JsonValue entry : entries) {
                versions.add(new BoxFileVersion(this.getAPI(), entry.asObject(), this.getID()));
            }

            return versions;
        }
    }

    /**
     * Checks if a new version of the file can be uploaded with the specified name.
     *
     * @param name the new name for the file.
     * @return whether or not the file version can be uploaded.
     */
    public boolean canUploadVersion(String name) {
        return this.canUploadVersion(name, 0);
    }

    /**
     * Checks if a new version of the file can be uploaded with the specified name and size.
     *
     * @param name     the new name for the file.
     * @param fileSize the size of the new version content in bytes.
     * @return whether the file version can be uploaded.
     */
    public boolean canUploadVersion(String name, long fileSize) {

        URL url = getDownloadUrl();
        BoxJSONRequest request = new BoxJSONRequest(this.getAPI(), url, "OPTIONS");

        JsonObject preflightInfo = new JsonObject();
        if (name != null) {
            preflightInfo.add("name", name);
        }

        preflightInfo.add("size", fileSize);

        request.setBody(preflightInfo.toString());
        try (BoxAPIResponse response = request.send()) {
            return response.getResponseCode() == 200;
        } catch (BoxAPIException ex) {
            if (ex.getResponseCode() >= 400 && ex.getResponseCode() < 500) {
                // This looks like an error response, meaning the upload would fail
                return false;
            } else {
                // This looks like a network error or server error, rethrow exception
                throw ex;
            }
        }
    }

    /**
     * Uploads a new version of this file, replacing the current version. Note that only users with premium accounts
     * will be able to view and recover previous versions of the file.
     *
     * @param fileContent a stream containing the new file contents.
     * @return the uploaded file version.
     */
    public BoxFile.Info uploadNewVersion(InputStream fileContent) {
        return this.uploadNewVersion(fileContent, null);
    }

    /**
     * Uploads a new version of this file, replacing the current version. Note that only users with premium accounts
     * will be able to view and recover previous versions of the file.
     *
     * @param fileContent     a stream containing the new file contents.
     * @param fileContentSHA1 a string containing the SHA1 hash of the new file contents.
     * @return the uploaded file version.
     */
    public BoxFile.Info uploadNewVersion(InputStream fileContent, String fileContentSHA1) {
        return this.uploadNewVersion(fileContent, fileContentSHA1, null);
    }

    /**
     * Uploads a new version of this file, replacing the current version. Note that only users with premium accounts
     * will be able to view and recover previous versions of the file.
     *
     * @param fileContent     a stream containing the new file contents.
     * @param fileContentSHA1 a string containing the SHA1 hash of the new file contents.
     * @param modified        the date that the new version was modified.
     * @return the uploaded file version.
     */
    public BoxFile.Info uploadNewVersion(InputStream fileContent, String fileContentSHA1, Date modified) {
        return this.uploadNewVersion(fileContent, fileContentSHA1, modified, 0, null);
    }

    /**
     * Uploads a new version of this file, replacing the current version. Note that only users with premium accounts
     * will be able to view and recover previous versions of the file.
     *
     * @param fileContent     a stream containing the new file contents.
     * @param fileContentSHA1 a string containing the SHA1 hash of the new file contents.
     * @param modified        the date that the new version was modified.
     * @param name            the new name for the file
     * @return the uploaded file version.
     */
    public BoxFile.Info uploadNewVersion(InputStream fileContent, String fileContentSHA1, Date modified, String name) {
        return this.uploadNewVersion(fileContent, fileContentSHA1, modified, name, 0, null);
    }

    /**
     * Uploads a new version of this file, replacing the current version, while reporting the progress to a
     * ProgressListener. Note that only users with premium accounts will be able to view and recover previous versions
     * of the file.
     *
     * @param fileContent a stream containing the new file contents.
     * @param modified    the date that the new version was modified.
     * @param fileSize    the size of the file used for determining the progress of the upload.
     * @param listener    a listener for monitoring the upload's progress.
     * @return the uploaded file version.
     */
    public BoxFile.Info uploadNewVersion(InputStream fileContent, Date modified, long fileSize,
                                         ProgressListener listener) {
        return this.uploadNewVersion(fileContent, null, modified, fileSize, listener);
    }

    /**
     * Uploads a new version of this file, replacing the current version, while reporting the progress to a
     * ProgressListener. Note that only users with premium accounts will be able to view and recover previous versions
     * of the file.
     *
     * @param fileContent     a stream containing the new file contents.
     * @param fileContentSHA1 the SHA1 hash of the file contents. will be sent along in the Content-MD5 header
     * @param modified        the date that the new version was modified.
     * @param fileSize        the size of the file used for determining the progress of the upload.
     * @param listener        a listener for monitoring the upload's progress.
     * @return the uploaded file version.
     */
    public BoxFile.Info uploadNewVersion(InputStream fileContent, String fileContentSHA1, Date modified, long fileSize,
                                         ProgressListener listener) {
        return this.uploadNewVersion(fileContent, fileContentSHA1, modified, null, fileSize, listener);
    }

    /**
     * Uploads a new version of this file, replacing the current version, while reporting the progress to a
     * ProgressListener. Note that only users with premium accounts will be able to view and recover previous versions
     * of the file.
     *
     * @param fileContent     a stream containing the new file contents.
     * @param fileContentSHA1 the SHA1 hash of the file contents. will be sent along in the Content-MD5 header
     * @param modified        the date that the new version was modified.
     * @param name            the new name for the file
     * @param fileSize        the size of the file used for determining the progress of the upload.
     * @param listener        a listener for monitoring the upload's progress.
     * @return the uploaded file version.
     */
    public BoxFile.Info uploadNewVersion(InputStream fileContent, String fileContentSHA1, Date modified, String name,
                                         long fileSize, ProgressListener listener) {
        URL uploadURL = CONTENT_URL_TEMPLATE.build(this.getAPI().getBaseUploadURL(), this.getID());
        BoxMultipartRequest request = new BoxMultipartRequest(getAPI(), uploadURL);

        if (fileSize > 0) {
            request.setFile(fileContent, "", fileSize);
        } else {
            request.setFile(fileContent, "");
        }

        if (fileContentSHA1 != null) {
            request.setContentSHA1(fileContentSHA1);
        }

        JsonObject attributesJSON = new JsonObject();
        if (modified != null) {
            attributesJSON.add("content_modified_at", BoxDateFormat.format(modified));
        }

        if (name != null) {
            attributesJSON.add("name", name);
        }

        request.putField("attributes", attributesJSON.toString());

        BoxJSONResponse response = null;
        try {
            if (listener == null) {
                // upload is multipart request but response is JSON
                response = (BoxJSONResponse) request.send();
            } else {
                // upload is multipart request but response is JSON
                response = (BoxJSONResponse) request.send(listener);
            }

            String fileJSON = response.getJsonObject().get("entries").asArray().get(0).toString();

            return new BoxFile.Info(fileJSON);
        } finally {
            Optional.ofNullable(response).ifPresent(BoxAPIResponse::close);
        }
    }

    /**
     * Gets an expiring URL for creating an embedded preview session. The URL will expire after 60 seconds and the
     * preview session will expire after 60 minutes.
     *
     * @return the expiring preview link
     */
    public URL getPreviewLink() {
        BoxFile.Info info = this.getInfo("expiring_embed_link");

        return info.getPreviewLink();
    }

    /**
     * Gets a list of any comments on this file.
     *
     * @return a list of comments on this file.
     */
    public List<BoxComment.Info> getComments() {
        URL url = GET_COMMENTS_URL_TEMPLATE.build(this.getAPI().getBaseURL(), this.getID());
        BoxJSONRequest request = new BoxJSONRequest(this.getAPI(), url, "GET");
        try (BoxJSONResponse response = request.send()) {
            JsonObject responseJSON = Json.parse(response.getJSON()).asObject();

            int totalCount = responseJSON.get("total_count").asInt();
            List<BoxComment.Info> comments = new ArrayList<>(totalCount);
            JsonArray entries = responseJSON.get("entries").asArray();
            for (JsonValue value : entries) {
                JsonObject commentJSON = value.asObject();
                BoxComment comment = new BoxComment(this.getAPI(), commentJSON.get("id").asString());
                BoxComment.Info info = comment.new Info(commentJSON);
                comments.add(info);
            }

            return comments;
        }
    }

    /**
     * Gets a list of any tasks on this file with requested fields.
     *
     * @param fields optional fields to retrieve for this task.
     * @return a list of tasks on this file.
     */
    public List<BoxTask.Info> getTasks(String... fields) {
        QueryStringBuilder builder = new QueryStringBuilder();
        if (fields.length > 0) {
            builder.appendParam("fields", fields);
        }
        URL url = GET_TASKS_URL_TEMPLATE.buildWithQuery(this.getAPI().getBaseURL(), builder.toString(), this.getID());
        BoxJSONRequest request = new BoxJSONRequest(this.getAPI(), url, "GET");
        try (BoxJSONResponse response = request.send()) {
            JsonObject responseJSON = Json.parse(response.getJSON()).asObject();

            int totalCount = responseJSON.get("total_count").asInt();
            List<BoxTask.Info> tasks = new ArrayList<>(totalCount);
            JsonArray entries = responseJSON.get("entries").asArray();
            for (JsonValue value : entries) {
                JsonObject taskJSON = value.asObject();
                BoxTask task = new BoxTask(this.getAPI(), taskJSON.get("id").asString());
                BoxTask.Info info = task.new Info(taskJSON);
                tasks.add(info);
            }

            return tasks;
        }
    }

    /**
     * Creates metadata on this file in the global properties template.
     *
     * @param metadata The new metadata values.
     * @return the metadata returned from the server.
     */
    public Metadata createMetadata(Metadata metadata) {
        return this.createMetadata(Metadata.DEFAULT_METADATA_TYPE, metadata);
    }

    /**
     * Creates metadata on this file in the specified template type.
     *
     * @param typeName the metadata template type name.
     * @param metadata the new metadata values.
     * @return the metadata returned from the server.
     */
    public Metadata createMetadata(String typeName, Metadata metadata) {
        String scope = Metadata.scopeBasedOnType(typeName);
        return this.createMetadata(typeName, scope, metadata);
    }

    /**
     * Creates metadata on this file in the specified template type.
     *
     * @param typeName the metadata template type name.
     * @param scope    the metadata scope (global or enterprise).
     * @param metadata the new metadata values.
     * @return the metadata returned from the server.
     */
    public Metadata createMetadata(String typeName, String scope, Metadata metadata) {
        URL url = METADATA_URL_TEMPLATE.buildAlpha(this.getAPI().getBaseURL(), this.getID(), scope, typeName);
        BoxJSONRequest request = new BoxJSONRequest(this.getAPI(), url, "POST");
        request.setBody(metadata.toString());
        try (BoxJSONResponse response = request.send()) {
            return new Metadata(Json.parse(response.getJSON()).asObject());
        }
    }

    /**
     * Sets the provided metadata on the file. If metadata has already been created on this file,
     * it overwrites metadata keys specified in the `metadata` param.
     *
     * @param templateName the name of the metadata template.
     * @param scope        the scope of the template (usually "global" or "enterprise").
     * @param metadata     the new metadata values.
     * @return the metadata returned from the server.
     */
    public Metadata setMetadata(String templateName, String scope, Metadata metadata) {
        try {
            return this.createMetadata(templateName, scope, metadata);
        } catch (BoxAPIException e) {
            if (e.getResponseCode() == 409) {
                if (metadata.getOperations().isEmpty()) {
                    return getMetadata();
                } else {
                    return updateExistingTemplate(templateName, scope, metadata);
                }
            } else {
                throw e;
            }
        }
    }

    private Metadata updateExistingTemplate(String templateName, String scope, Metadata metadata) {
        Metadata metadataToUpdate = new Metadata(scope, templateName);
        for (JsonValue value : metadata.getOperations()) {
            if (value.asObject().get("value").isNumber()) {
                metadataToUpdate.add(value.asObject().get("path").asString(),
                    value.asObject().get("value").asDouble());
            } else if (value.asObject().get("value").isString()) {
                metadataToUpdate.add(value.asObject().get("path").asString(),
                    value.asObject().get("value").asString());
            } else if (value.asObject().get("value").isArray()) {
                ArrayList<String> list = new ArrayList<>();
                for (JsonValue jsonValue : value.asObject().get("value").asArray()) {
                    list.add(jsonValue.asString());
                }
                metadataToUpdate.add(value.asObject().get("path").asString(), list);
            }
        }
        return this.updateMetadata(metadataToUpdate);
    }

    /**
     * Adds a metadata classification to the specified file.
     *
     * @param classificationType the metadata classification type.
     * @return the metadata classification type added to the file.
     */
    public String addClassification(String classificationType) {
        Metadata metadata = new Metadata().add(Metadata.CLASSIFICATION_KEY, classificationType);
        Metadata classification = this.createMetadata(Metadata.CLASSIFICATION_TEMPLATE_KEY,
            "enterprise", metadata);

        return classification.getString(Metadata.CLASSIFICATION_KEY);
    }

    /**
     * Updates a metadata classification on the specified file.
     *
     * @param classificationType the metadata classification type.
     * @return the new metadata classification type updated on the file.
     */
    public String updateClassification(String classificationType) {
        Metadata metadata = new Metadata("enterprise", Metadata.CLASSIFICATION_TEMPLATE_KEY);
        metadata.add("/Box__Security__Classification__Key", classificationType);
        Metadata classification = this.updateMetadata(metadata);

        return classification.getString(Metadata.CLASSIFICATION_KEY);
    }

    /**
     * Attempts to add classification to a file. If classification already exists then do update.
     *
     * @param classificationType the metadata classification type.
     * @return the metadata classification type on the file.
     */
    public String setClassification(String classificationType) {
        Metadata metadata = new Metadata().add(Metadata.CLASSIFICATION_KEY, classificationType);
        Metadata classification;

        try {
            classification = this.createMetadata(Metadata.CLASSIFICATION_TEMPLATE_KEY, "enterprise", metadata);
        } catch (BoxAPIException e) {
            if (e.getResponseCode() == 409) {
                metadata = new Metadata("enterprise", Metadata.CLASSIFICATION_TEMPLATE_KEY);
                metadata.replace(Metadata.CLASSIFICATION_KEY, classificationType);
                classification = this.updateMetadata(metadata);
            } else {
                throw e;
            }
        }

        return classification.getString(Metadata.CLASSIFICATION_KEY);
    }

    /**
     * Gets the classification type for the specified file.
     *
     * @return the metadata classification type on the file.
     */
    public String getClassification() {
        Metadata metadata;
        try {
            metadata = this.getMetadata(Metadata.CLASSIFICATION_TEMPLATE_KEY);

        } catch (BoxAPIException e) {
            JsonObject responseObject = Json.parse(e.getResponse()).asObject();
            String code = responseObject.get("code").asString();

            if (e.getResponseCode() == 404 && code.equals("instance_not_found")) {
                return null;
            } else {
                throw e;
            }
        }

        return metadata.getString(Metadata.CLASSIFICATION_KEY);
    }

    /**
     * Deletes the classification on the file.
     */
    public void deleteClassification() {
        this.deleteMetadata(Metadata.CLASSIFICATION_TEMPLATE_KEY, "enterprise");
    }

    /**
     * Locks a file.
     *
     * @return the lock returned from the server.
     */
    public BoxLock lock() {
        return this.lock(null, false);
    }

    /**
     * Locks a file.
     *
     * @param isDownloadPrevented is downloading of file prevented when locked.
     * @return the lock returned from the server.
     */
    public BoxLock lock(boolean isDownloadPrevented) {
        return this.lock(null, isDownloadPrevented);
    }

    /**
     * Locks a file.
     *
     * @param expiresAt expiration date of the lock.
     * @return the lock returned from the server.
     */
    public BoxLock lock(Date expiresAt) {
        return this.lock(expiresAt, false);
    }

    /**
     * Locks a file.
     *
     * @param expiresAt           expiration date of the lock.
     * @param isDownloadPrevented is downloading of file prevented when locked.
     * @return the lock returned from the server.
     */
    public BoxLock lock(Date expiresAt, boolean isDownloadPrevented) {
        String queryString = new QueryStringBuilder().appendParam("fields", "lock").toString();
        URL url = FILE_URL_TEMPLATE.buildWithQuery(this.getAPI().getBaseURL(), queryString, this.getID());
        BoxJSONRequest request = new BoxJSONRequest(this.getAPI(), url, "PUT");

        JsonObject lockConfig = new JsonObject();
        lockConfig.add("type", "lock");
        if (expiresAt != null) {
            lockConfig.add("expires_at", BoxDateFormat.format(expiresAt));
        }
        lockConfig.add("is_download_prevented", isDownloadPrevented);

        JsonObject requestJSON = new JsonObject();
        requestJSON.add("lock", lockConfig);
        request.setBody(requestJSON.toString());

        try (BoxJSONResponse response = request.send()) {

            JsonObject responseJSON = Json.parse(response.getJSON()).asObject();
            JsonValue lockValue = responseJSON.get("lock");
            JsonObject lockJSON = Json.parse(lockValue.toString()).asObject();

            return new BoxLock(lockJSON, this.getAPI());
        }
    }

    /**
     * Unlocks a file.
     */
    public void unlock() {
        String queryString = new QueryStringBuilder().appendParam("fields", "lock").toString();
        URL url = FILE_URL_TEMPLATE.buildWithQuery(this.getAPI().getBaseURL(), queryString, this.getID());
        BoxAPIRequest request = new BoxAPIRequest(this.getAPI(), url, "PUT");

        JsonObject lockObject = new JsonObject();
        lockObject.add("lock", NULL);

        request.setBody(lockObject.toString());
        request.send().close();
    }

    /**
     * Used to retrieve all metadata associated with the file.
     *
     * @param fields the optional fields to retrieve.
     * @return An iterable of metadata instances associated with the file.
     */
    public Iterable<Metadata> getAllMetadata(String... fields) {
        return Metadata.getAllMetadata(this, fields);
    }

    /**
     * Gets the file properties metadata.
     *
     * @return the metadata returned from the server.
     */
    public Metadata getMetadata() {
        return this.getMetadata(Metadata.DEFAULT_METADATA_TYPE);
    }

    /**
     * Gets the file metadata of specified template type.
     *
     * @param typeName the metadata template type name.
     * @return the metadata returned from the server.
     */
    public Metadata getMetadata(String typeName) {
        String scope = Metadata.scopeBasedOnType(typeName);
        return this.getMetadata(typeName, scope);
    }

    /**
     * Gets the file metadata of specified template type.
     *
     * @param typeName the metadata template type name.
     * @param scope    the metadata scope (global or enterprise).
     * @return the metadata returned from the server.
     */
    public Metadata getMetadata(String typeName, String scope) {
        URL url = METADATA_URL_TEMPLATE.buildAlpha(this.getAPI().getBaseURL(), this.getID(), scope, typeName);
        BoxJSONRequest request = new BoxJSONRequest(this.getAPI(), url, "GET");
        try (BoxJSONResponse response = request.send()) {
            return new Metadata(Json.parse(response.getJSON()).asObject());
        }
    }

    /**
     * Updates the file metadata.
     *
     * @param metadata the new metadata values.
     * @return the metadata returned from the server.
     */
    public Metadata updateMetadata(Metadata metadata) {
        String scope;
        if (metadata.getScope().equals(Metadata.GLOBAL_METADATA_SCOPE)) {
            scope = Metadata.GLOBAL_METADATA_SCOPE;
        } else if (metadata.getScope().startsWith(Metadata.ENTERPRISE_METADATA_SCOPE)) {
            scope = metadata.getScope();
        } else {
            scope = Metadata.ENTERPRISE_METADATA_SCOPE;
        }

        URL url = METADATA_URL_TEMPLATE.buildAlpha(this.getAPI().getBaseURL(), this.getID(),
            scope, metadata.getTemplateName());
        BoxJSONRequest request = new BoxJSONRequest(this.getAPI(), url, "PUT", APPLICATION_JSON_PATCH);
        request.setBody(metadata.getPatch());
        try (BoxJSONResponse response = request.send()) {
            return new Metadata(Json.parse(response.getJSON()).asObject());
        }
    }

    /**
     * Deletes the file properties metadata.
     */
    public void deleteMetadata() {
        this.deleteMetadata(Metadata.DEFAULT_METADATA_TYPE);
    }

    /**
     * Deletes the file metadata of specified template type.
     *
     * @param typeName the metadata template type name.
     */
    public void deleteMetadata(String typeName) {
        String scope = Metadata.scopeBasedOnType(typeName);
        this.deleteMetadata(typeName, scope);
    }

    /**
     * Deletes the file metadata of specified template type.
     *
     * @param typeName the metadata template type name.
     * @param scope    the metadata scope (global or enterprise).
     */
    public void deleteMetadata(String typeName, String scope) {
        URL url = METADATA_URL_TEMPLATE.buildAlpha(this.getAPI().getBaseURL(), this.getID(), scope, typeName);
        BoxAPIRequest request = new BoxAPIRequest(this.getAPI(), url, "DELETE");
        request.send().close();
    }

    /**
     * Used to retrieve the watermark for the file.
     * If the file does not have a watermark applied to it, a 404 Not Found will be returned by API.
     *
     * @param fields the fields to retrieve.
     * @return the watermark associated with the file.
     */
    public BoxWatermark getWatermark(String... fields) {
        return this.getWatermark(FILE_URL_TEMPLATE, fields);
    }

    /**
     * Used to apply or update the watermark for the file.
     *
     * @return the watermark associated with the file.
     */
    public BoxWatermark applyWatermark() {
        return this.applyWatermark(FILE_URL_TEMPLATE, BoxWatermark.WATERMARK_DEFAULT_IMPRINT);
    }

    /**
     * Removes a watermark from the file.
     * If the file did not have a watermark applied to it, a 404 Not Found will be returned by API.
     */
    public void removeWatermark() {
        this.removeWatermark(FILE_URL_TEMPLATE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BoxFile.Info setCollections(BoxCollection... collections) {
        JsonArray jsonArray = new JsonArray();
        for (BoxCollection collection : collections) {
            JsonObject collectionJSON = new JsonObject();
            collectionJSON.add("id", collection.getID());
            jsonArray.add(collectionJSON);
        }
        JsonObject infoJSON = new JsonObject();
        infoJSON.add("collections", jsonArray);

        String queryString = new QueryStringBuilder().appendParam("fields", ALL_FIELDS).toString();
        URL url = FILE_URL_TEMPLATE.buildWithQuery(this.getAPI().getBaseURL(), queryString, this.getID());
        BoxJSONRequest request = new BoxJSONRequest(this.getAPI(), url, "PUT");
        request.setBody(infoJSON.toString());
        try (BoxJSONResponse response = request.send()) {
            JsonObject jsonObject = Json.parse(response.getJSON()).asObject();
            return new Info(jsonObject);
        }
    }

    /**
     * Creates an upload session to create a new version of a file in chunks.
     * This will first verify that the version can be created and then open a session for uploading pieces of the file.
     *
     * @param fileSize the size of the file that will be uploaded.
     * @return the created upload session instance.
     */
    public BoxFileUploadSession.Info createUploadSession(long fileSize) {
        URL url = UPLOAD_SESSION_URL_TEMPLATE.build(this.getAPI().getBaseUploadURL(), this.getID());

        BoxJSONRequest request = new BoxJSONRequest(this.getAPI(), url, "POST");
        request.addHeader("Content-Type", APPLICATION_JSON);

        JsonObject body = new JsonObject();
        body.add("file_size", fileSize);
        request.setBody(body.toString());

        try (BoxJSONResponse response = request.send()) {
            JsonObject jsonObject = Json.parse(response.getJSON()).asObject();

            String sessionId = jsonObject.get("id").asString();
            BoxFileUploadSession session = new BoxFileUploadSession(this.getAPI(), sessionId);
            return session.new Info(jsonObject);
        }
    }

    /**
     * Creates a new version of a file.
     *
     * @param inputStream the stream instance that contains the data.
     * @param fileSize    the size of the file that will be uploaded.
     * @return the created file instance.
     * @throws InterruptedException when a thread execution is interrupted.
     * @throws IOException          when reading a stream throws exception.
     */
    public BoxFile.Info uploadLargeFile(InputStream inputStream, long fileSize)
        throws InterruptedException, IOException {
        URL url = UPLOAD_SESSION_URL_TEMPLATE.build(this.getAPI().getBaseUploadURL(), this.getID());
        return new LargeFileUpload().upload(this.getAPI(), inputStream, url, fileSize);
    }

    /**
     * Creates a new version of a file.  Also sets file attributes.
     *
     * @param inputStream    the stream instance that contains the data.
     * @param fileSize       the size of the file that will be uploaded.
     * @param fileAttributes file attributes to set
     * @return the created file instance.
     * @throws InterruptedException when a thread execution is interrupted.
     * @throws IOException          when reading a stream throws exception.
     */
    public BoxFile.Info uploadLargeFile(InputStream inputStream, long fileSize, Map<String, String> fileAttributes)
        throws InterruptedException, IOException {
        URL url = UPLOAD_SESSION_URL_TEMPLATE.build(this.getAPI().getBaseUploadURL(), this.getID());
        return new LargeFileUpload().upload(this.getAPI(), inputStream, url, fileSize, fileAttributes);
    }

    /**
     * Creates a new version of a file using specified number of parallel http connections.
     *
     * @param inputStream          the stream instance that contains the data.
     * @param fileSize             the size of the file that will be uploaded.
     * @param nParallelConnections number of parallel http connections to use
     * @param timeOut              time to wait before killing the job
     * @param unit                 time unit for the time wait value
     * @return the created file instance.
     * @throws InterruptedException when a thread execution is interrupted.
     * @throws IOException          when reading a stream throws exception.
     */
    public BoxFile.Info uploadLargeFile(InputStream inputStream, long fileSize,
                                        int nParallelConnections, long timeOut, TimeUnit unit)
        throws InterruptedException, IOException {
        URL url = UPLOAD_SESSION_URL_TEMPLATE.build(this.getAPI().getBaseUploadURL(), this.getID());
        return new LargeFileUpload(nParallelConnections, timeOut, unit)
            .upload(this.getAPI(), inputStream, url, fileSize);
    }

    /**
     * Creates a new version of a file using specified number of parallel http connections.  Also sets file attributes.
     *
     * @param inputStream          the stream instance that contains the data.
     * @param fileSize             the size of the file that will be uploaded.
     * @param nParallelConnections number of parallel http connections to use
     * @param timeOut              time to wait before killing the job
     * @param unit                 time unit for the time wait value
     * @param fileAttributes       file attributes to set
     * @return the created file instance.
     * @throws InterruptedException when a thread execution is interrupted.
     * @throws IOException          when reading a stream throws exception.
     */
    public BoxFile.Info uploadLargeFile(InputStream inputStream, long fileSize,
                                        int nParallelConnections, long timeOut, TimeUnit unit,
                                        Map<String, String> fileAttributes)
        throws InterruptedException, IOException {
        URL url = UPLOAD_SESSION_URL_TEMPLATE.build(this.getAPI().getBaseUploadURL(), this.getID());
        return new LargeFileUpload(nParallelConnections, timeOut, unit)
            .upload(this.getAPI(), inputStream, url, fileSize, fileAttributes);
    }

    private BoxCollaboration.Info collaborate(JsonObject accessibleByField, BoxCollaboration.Role role,
                                              Boolean notify, Boolean canViewPath, Date expiresAt,
                                              Boolean isAccessOnly) {

        JsonObject itemField = new JsonObject();
        itemField.add("id", this.getID());
        itemField.add("type", "file");

        return BoxCollaboration.create(this.getAPI(), accessibleByField, itemField, role, notify, canViewPath,
                expiresAt, isAccessOnly);
    }

    /**
     * Adds a collaborator to this file.
     *
     * @param collaborator the collaborator to add.
     * @param role         the role of the collaborator.
     * @param notify       determines if the user (or all the users in the group) will receive email notifications.
     * @param canViewPath  whether view path collaboration feature is enabled or not.
     * @param expiresAt    when the collaboration should expire.
     * @param isAccessOnly whether the collaboration is access only or not.
     * @return info about the new collaboration.
     */
    public BoxCollaboration.Info collaborate(BoxCollaborator collaborator, BoxCollaboration.Role role,
                                             Boolean notify, Boolean canViewPath,
                                             Date expiresAt, Boolean isAccessOnly) {
        JsonObject accessibleByField = new JsonObject();
        accessibleByField.add("id", collaborator.getID());

        if (collaborator instanceof BoxUser) {
            accessibleByField.add("type", "user");
        } else if (collaborator instanceof BoxGroup) {
            accessibleByField.add("type", "group");
        } else {
            throw new IllegalArgumentException("The given collaborator is of an unknown type.");
        }
        return this.collaborate(accessibleByField, role, notify, canViewPath, expiresAt, isAccessOnly);
    }

    /**
     * Adds a collaborator to this file.
     *
     * @param collaborator the collaborator to add.
     * @param role         the role of the collaborator.
     * @param notify       determines if the user (or all the users in the group) will receive email notifications.
     * @param canViewPath  whether view path collaboration feature is enabled or not.
     * @return info about the new collaboration.
     */
    public BoxCollaboration.Info collaborate(BoxCollaborator collaborator, BoxCollaboration.Role role,
                                             Boolean notify, Boolean canViewPath) {
        return this.collaborate(collaborator, role, notify, canViewPath, null, null);
    }

    /**
     * Adds a collaborator to this folder. An email will be sent to the collaborator if they don't already have a Box
     * account.
     *
     * @param email       the email address of the collaborator to add.
     * @param role        the role of the collaborator.
     * @param notify      determines if the user (or all the users in the group) will receive email notifications.
     * @param canViewPath whether view path collaboration feature is enabled or not.
     * @param expiresAt    when the collaboration should expire.
     * @param isAccessOnly whether the collaboration is access only or not.
     * @return info about the new collaboration.
     */
    public BoxCollaboration.Info collaborate(String email, BoxCollaboration.Role role,
                                             Boolean notify, Boolean canViewPath,
                                             Date expiresAt, Boolean isAccessOnly) {
        JsonObject accessibleByField = new JsonObject();
        accessibleByField.add("login", email);
        accessibleByField.add("type", "user");

        return this.collaborate(accessibleByField, role, notify, canViewPath, expiresAt, isAccessOnly);
    }

    /**
     * Adds a collaborator to this folder. An email will be sent to the collaborator if they don't already have a Box
     * account.
     *
     * @param email       the email address of the collaborator to add.
     * @param role        the role of the collaborator.
     * @param notify      determines if the user (or all the users in the group) will receive email notifications.
     * @param canViewPath whether view path collaboration feature is enabled or not.
     * @return info about the new collaboration.
     */
    public BoxCollaboration.Info collaborate(String email, BoxCollaboration.Role role,
                                             Boolean notify, Boolean canViewPath) {
        return this.collaborate(email, role, notify, canViewPath, null, null);
    }

    /**
     * Used to retrieve all collaborations associated with the item.
     *
     * @param fields the optional fields to retrieve.
     * @return An iterable of metadata instances associated with the item.
     */
    public BoxResourceIterable<BoxCollaboration.Info> getAllFileCollaborations(String... fields) {
        return BoxCollaboration.getAllFileCollaborations(this.getAPI(), this.getID(),
            GET_COLLABORATORS_PAGE_SIZE, fields);

    }

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

    /**
     * Enumerates the possible permissions that a user can have on a file.
     */
    public enum Permission {
        /**
         * The user can download the file.
         */
        CAN_DOWNLOAD("can_download"),

        /**
         * The user can upload new versions of the file.
         */
        CAN_UPLOAD("can_upload"),

        /**
         * The user can rename the file.
         */
        CAN_RENAME("can_rename"),

        /**
         * The user can delete the file.
         */
        CAN_DELETE("can_delete"),

        /**
         * The user can share the file.
         */
        CAN_SHARE("can_share"),

        /**
         * The user can set the access level for shared links to the file.
         */
        CAN_SET_SHARE_ACCESS("can_set_share_access"),

        /**
         * The user can preview the file.
         */
        CAN_PREVIEW("can_preview"),

        /**
         * The user can comment on the file.
         */
        CAN_COMMENT("can_comment"),

        /**
         * The user can place annotations on this file.
         */
        CAN_ANNOTATE("can_annotate"),

        /**
         * The current user can invite new users to collaborate on this item, and the user can update the role of a
         * user already collaborated on this item.
         */
        CAN_INVITE_COLLABORATOR("can_invite_collaborator"),

        /**
         * The user can view all annotations placed on this file.
         */
        CAN_VIEW_ANNOTATIONS_ALL("can_view_annotations_all"),

        /**
         * The user can view annotations placed by themselves on this file.
         */
        CAN_VIEW_ANNOTATIONS_SELF("can_view_annotations_self");

        private final String jsonValue;

        Permission(String jsonValue) {
            this.jsonValue = jsonValue;
        }

        static Permission fromJSONValue(String jsonValue) {
            return Permission.valueOf(jsonValue.toUpperCase());
        }

        String toJSONValue() {
            return this.jsonValue;
        }
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
        private boolean isWatermarked;
        private boolean isExternallyOwned;
        private Map<String, Map<String, Metadata>> metadataMap;
        private List<Representation> representations;
        private List<String> allowedInviteeRoles;
        private Boolean hasCollaborations;
        private String uploaderDisplayName;
        private BoxClassification classification;
        private Date dispositionAt;
        private boolean isAccessibleViaSharedLink;

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

        @Override
        public BoxFile getResource() {
            return BoxFile.this;
        }

        /**
         * Gets the SHA1 hash of the file.
         *
         * @return the SHA1 hash of the file.
         */
        public String getSha1() {
            return this.sha1;
        }

        /**
         * Gets the lock of the file.
         *
         * @return the lock of the file.
         */
        public BoxLock getLock() {
            return this.lock;
        }

        /**
         * Gets the current version number of the file.
         *
         * @return the current version number of the file.
         */
        public String getVersionNumber() {
            return this.versionNumber;
        }

        /**
         * Gets the number of comments on the file.
         *
         * @return the number of comments on the file.
         */
        public long getCommentCount() {
            return this.commentCount;
        }

        /**
         * Gets the permissions that the current user has on the file.
         *
         * @return the permissions that the current user has on the file.
         */
        public EnumSet<Permission> getPermissions() {
            return this.permissions;
        }

        /**
         * Gets the extension suffix of the file, excluding the dot.
         *
         * @return the extension of the file.
         */
        public String getExtension() {
            return this.extension;
        }

        /**
         * Gets whether or not the file is an OSX package.
         *
         * @return true if the file is an OSX package; otherwise false.
         */
        public boolean getIsPackage() {
            return this.isPackage;
        }

        /**
         * Gets the current version details of the file.
         *
         * @return the current version details of the file.
         */
        public BoxFileVersion getVersion() {
            return this.version;
        }

        /**
         * Gets the current expiring preview link.
         *
         * @return the expiring preview link
         */
        public URL getPreviewLink() {
            return this.previewLink;
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
         * Returns the allowed invitee roles for this file item.
         *
         * @return the list of roles allowed for invited collaborators.
         */
        public List<String> getAllowedInviteeRoles() {
            return this.allowedInviteeRoles;
        }

        /**
         * Returns the indicator for whether this file item has collaborations.
         *
         * @return indicator for whether this file item has collaborations.
         */
        public Boolean getHasCollaborations() {
            return this.hasCollaborations;
        }

        /**
         * Gets the metadata on this file associated with a specified scope and template.
         * Makes an attempt to get metadata that was retrieved using getInfo(String ...) method.
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

        /**
         * Returns the field for indicating whether a file is owned by a user outside the enterprise.
         *
         * @return indicator for whether or not the file is owned by a user outside the enterprise.
         */
        public boolean getIsExternallyOwned() {
            return this.isExternallyOwned;
        }

        /**
         * Get file's representations.
         *
         * @return list of representations
         */
        public List<Representation> getRepresentations() {
            return this.representations;
        }

        /**
         * Returns user's name at the time of upload.
         *
         * @return user's name at the time of upload
         */
        public String getUploaderDisplayName() {
            return this.uploaderDisplayName;
        }

        /**
         * Gets the metadata classification type of this file.
         *
         * @return the metadata classification type of this file.
         */
        public BoxClassification getClassification() {
            return this.classification;
        }

        /**
         * Returns the retention expiration timestamp for the given file.
         *
         * @return Date representing expiration timestamp
         */
        public Date getDispositionAt() {
            return dispositionAt;
        }

        /**
         * Modifies the retention expiration timestamp for the given file.
         * This date cannot be shortened once set on a file.
         *
         * @param dispositionAt Date representing expiration timestamp
         */
        public void setDispositionAt(Date dispositionAt) {
            this.dispositionAt = dispositionAt;
            this.addPendingChange("disposition_at", BoxDateFormat.format(dispositionAt));
        }

        /**
         * Returns the flag indicating whether the file is accessible via a shared link.
         *
         * @return boolean flag indicating whether the file is accessible via a shared link.
         */
        public boolean getIsAccessibleViaSharedLink() {
            return this.isAccessibleViaSharedLink;
        }

        @Override
        protected void parseJSONMember(JsonObject.Member member) {
            super.parseJSONMember(member);

            String memberName = member.getName();
            JsonValue value = member.getValue();
            try {
                switch (memberName) {
                    case "sha1":
                        this.sha1 = value.asString();
                        break;
                    case "version_number":
                        this.versionNumber = value.asString();
                        break;
                    case "comment_count":
                        this.commentCount = value.asLong();
                        break;
                    case "permissions":
                        this.permissions = this.parsePermissions(value.asObject());
                        break;
                    case "extension":
                        this.extension = value.asString();
                        break;
                    case "is_package":
                        this.isPackage = value.asBoolean();
                        break;
                    case "has_collaborations":
                        this.hasCollaborations = value.asBoolean();
                        break;
                    case "is_externally_owned":
                        this.isExternallyOwned = value.asBoolean();
                        break;
                    case "file_version":
                        this.version = this.parseFileVersion(value.asObject());
                        break;
                    case "allowed_invitee_roles":
                        this.allowedInviteeRoles = this.parseAllowedInviteeRoles(value.asArray());
                        break;
                    case "expiring_embed_link":
                        try {
                            String urlString = member.getValue().asObject().get("url").asString();
                            this.previewLink = new URL(urlString);
                        } catch (MalformedURLException e) {
                            throw new BoxAPIException("Couldn't parse expiring_embed_link/url for file", e);
                        }
                        break;
                    case "lock":
                        if (value.isNull()) {
                            this.lock = null;
                        } else {
                            this.lock = new BoxLock(value.asObject(), BoxFile.this.getAPI());
                        }
                        break;
                    case "watermark_info":
                        this.isWatermarked = value.asObject().get("is_watermarked").asBoolean();
                        break;
                    case "metadata":
                        this.metadataMap = Parsers.parseAndPopulateMetadataMap(value.asObject());
                        break;
                    case "representations":
                        this.representations = Parsers.parseRepresentations(value.asObject());
                        break;
                    case "uploader_display_name":
                        this.uploaderDisplayName = value.asString();
                        break;
                    case "classification":
                        if (value.isNull()) {
                            this.classification = null;
                        } else {
                            this.classification = new BoxClassification(value.asObject());
                        }
                        break;
                    case "disposition_at":
                        this.dispositionAt = BoxDateFormat.parse(value.asString());
                        break;
                    case "is_accessible_via_shared_link":
                        this.isAccessibleViaSharedLink = value.asBoolean();
                        break;
                    default:
                        break;
                }
            } catch (Exception e) {
                throw new BoxDeserializationException(memberName, value.toString(), e);
            }
        }

        @SuppressWarnings("checkstyle:MissingSwitchDefault")
        private EnumSet<Permission> parsePermissions(JsonObject jsonObject) {
            EnumSet<Permission> permissions = EnumSet.noneOf(Permission.class);
            for (JsonObject.Member member : jsonObject) {
                JsonValue value = member.getValue();
                if (value.isNull() || !value.asBoolean()) {
                    continue;
                }

                switch (member.getName()) {
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
                    case "can_set_share_access":
                        permissions.add(Permission.CAN_SET_SHARE_ACCESS);
                        break;
                    case "can_preview":
                        permissions.add(Permission.CAN_PREVIEW);
                        break;
                    case "can_comment":
                        permissions.add(Permission.CAN_COMMENT);
                        break;
                }
            }

            return permissions;
        }

        private BoxFileVersion parseFileVersion(JsonObject jsonObject) {
            return new BoxFileVersion(BoxFile.this.getAPI(), jsonObject, BoxFile.this.getID());
        }

        private List<String> parseAllowedInviteeRoles(JsonArray jsonArray) {
            List<String> roles = new ArrayList<>(jsonArray.size());
            for (JsonValue value : jsonArray) {
                roles.add(value.asString());
            }

            return roles;
        }
    }

}
